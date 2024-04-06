package org.feather.skill.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.account.BalanceInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Orders;
import org.feather.skill.exception.BusinessException;
import org.feather.skill.feign.AddressClient;
import org.feather.skill.feign.NotSecuredBalanceClient;
import org.feather.skill.feign.NotSecuredGoodsClient;
import org.feather.skill.feign.SecuredGoodsClient;
import org.feather.skill.filter.AccessContext;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.feather.skill.mapper.OrdersMapper;
import org.feather.skill.order.LogisticsMessage;
import org.feather.skill.order.OrderInfo;
import org.feather.skill.page.BasePage;
import org.feather.skill.service.IOrdersService;
import org.feather.skill.vo.PageSimpleOrderDetail;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.impl
 * @className: OrderServiceImpl
 * @author: feather
 * @description:
 * @since: 2024-03-30 16:50
 * @version: 1.0
 */
@RequiredArgsConstructor
@EnableBinding(LogisticsMessage.class)
@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

    private final AddressClient addressClient;

    private final SecuredGoodsClient securedGoodsClient;

    private final NotSecuredGoodsClient notSecuredGoodsClient;

    private final NotSecuredBalanceClient notSecuredBalanceClient;

//    private final LogisticsSource logisticsSource;

    @Override
    public Page<Orders> findAllByUserId(Long userId, BasePage page) {
        return this.getBaseMapper().selectPage(new Page<>(page.getPageNo(), page.getPageSize())
                ,new LambdaQueryWrapper<Orders>().eq(Orders::getUserId,userId));
    }

    /**
     * description: 创建订单 这里会涉及到分布式事务
     * 创建订单会涉及到多个步骤和校验，当不满足情况时直接抛出异常
     * 1：校验请求是否合法
     * 2：创建订单
     * 3：扣减商品库存
     * 4：扣减用户余额
     * 5：发送订单物流消息：Spring Cloud+Stream +Kafka
     * @param orderInfo
     * @return {@link TableId}
     * @author: feather
     * @since: 2024-04-05 10:56
     **/
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public TableId createOrder(OrderInfo orderInfo) {
        //获取地址信息
        AddressInfo addressInfo = addressClient.getAddressInfoByTablesId(
                new TableId(Collections.singletonList(new TableId.Id(orderInfo.getUserAddressId())))
        ).getData();
        //1.校验请求对象是否合法(商品信息不需要校验，扣减库存会做校验)
        if (CollectionUtil.isEmpty(addressInfo.getAddressItems())){
            throw  new BusinessException("用户地址不存在:"+orderInfo.getUserAddressId());
        }
        //2.创建订单
        Orders order = new Orders(AccessContext.getLoginUserInfo().getId(), orderInfo.getUserAddressId(), JSON.toJSONString(orderInfo.getOrderItems()));
        boolean save = this.save(order);
        log.info("create order result:[{}]",save);
        //3 扣减商品库存
        if (!notSecuredGoodsClient.deductGoodsInventory(
                orderInfo.getOrderItems()
                        .stream().map(OrderInfo.OrderItem::toDeductGoodsInventory)
                        .collect(Collectors.toList())).getData()){
        throw  new BusinessException("扣减库存失败");
        }
        //4.扣减用户账户余额
        //4.1 获取商品信息，计算总价格
        List<SimpleGoodsInfo> goodsInfos = notSecuredGoodsClient.getSimpleGoodsInfoByTableId(
                new TableId(
                        orderInfo.getOrderItems()
                                .stream()
                                .map(orderItem->new TableId.Id(orderItem.getGoodsId()))
                                .collect(Collectors.toList())
                )
        ).getData();
        Map<Long, SimpleGoodsInfo> goodsInfoMap = goodsInfos.stream().collect(Collectors.toMap(SimpleGoodsInfo::getId, Function.identity()));
        long balance=0;
        for (OrderInfo.OrderItem orderItem : orderInfo.getOrderItems()) {
            balance+= (long) goodsInfoMap.get(orderItem.getGoodsId()).getPrice() *orderItem.getCount();
        }
        assert balance>0;
        BalanceInfo balanceInfo = notSecuredBalanceClient.deductBalance(new BalanceInfo(AccessContext.getLoginUserInfo().getId(), balance)).getData();
        if (null==balanceInfo){
            throw  new BusinessException("扣减用户余额失败");
        }
        log.info("扣减用户余额:[{}]",JSON.toJSON(balanceInfo));
        // 5. 发送订单物流消息 SpringCloud Stream + Kafka
//        LogisticsMessage logisticsMessage = new LogisticsMessage(
//                AccessContext.getLoginUserInfo().getId(),
//                order.getId(),
//                orderInfo.getUserAddressId(),
//                null    // 没有备注信息
//        );
//        if (!logisticsSource.logisticsOutput().send(
//                MessageBuilder.withPayload(JSON.toJSONString(logisticsMessage)).build()
//        )) {
//            throw new RuntimeException("send logistics message failure");
//        }
//        log.info("send create order message to kafka with stream: [{}]",
//                JSON.toJSONString(logisticsMessage));


        // 返回订单 id
        return new TableId(Collections.singletonList(new TableId.Id(order.getId())));
    }

    @Override
    public PageSimpleOrderDetail getSimpleOrderDetailByPage(BasePage page) {
        Page<Orders> orderPage = this.findAllByUserId(AccessContext.getLoginUserInfo().getId(), page);
        List<Orders> orders = orderPage.getRecords();
        if (CollectionUtil.isEmpty(orders)){
            return new PageSimpleOrderDetail( Collections.emptyList(),false);
        }
        // 获取当前订单中所有的 goodsId, 这个 set 不可能为空或者是 null, 否则, 代码一定有 bug
        Set<Long> goodsInfoOrders=new HashSet<>();
            orders.forEach(o->{
                List<DeductGoodsInventory> deductGoodsInventoryList = JSON.parseArray(o.getOrderDetail(), DeductGoodsInventory.class);
                goodsInfoOrders.addAll(deductGoodsInventoryList.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toSet()));
            });
           assert  CollectionUtil.isEmpty(goodsInfoOrders);
         //获取商品信息
        List<SimpleGoodsInfo> goodsInfos = securedGoodsClient.getSimpleGoodsInfoByTableId(
                new TableId(goodsInfoOrders.stream().map(TableId.Id::new).collect(Collectors.toList()))
        ).getData();
        //获取地址信息
        AddressInfo addressInfo = addressClient.getAddressInfoByTablesId(
                new TableId(orders.stream().map(o->new TableId.Id(o.getAddressId())).distinct().collect(Collectors.toList()))
        ).getData();
        //组装订单中的商品，地址信息--->订单信息
        return   new PageSimpleOrderDetail(
                assembleSimpleOrderDetail(orders, goodsInfos, addressInfo),
                true
        );
    }

    /**
     * description:  组装订单详情
     * @param orders 订单信息
     * @param goodsInfos 商品信息
     * @param addressInfo 地址信息
     * @return {@link List<PageSimpleOrderDetail.SingleOrderItem>}
     * @author: feather
     * @since: 2024-04-05 20:19
     **/
    private List<PageSimpleOrderDetail.SingleOrderItem> assembleSimpleOrderDetail(
            List<Orders> orders, List<SimpleGoodsInfo> goodsInfos,
            AddressInfo addressInfo
    ){
        //goodsId-->SimpleGoodsInfo
        Map<Long, SimpleGoodsInfo> goodsInfoMap = goodsInfos.stream().collect(Collectors.toMap(SimpleGoodsInfo::getId, Function.identity()));
        //addressId--->AddressInfo.AddressItem
        Map<Long, AddressInfo.AddressItem> addressItemMap = addressInfo.getAddressItems().stream().collect(Collectors.toMap(AddressInfo.AddressItem::getId, Function.identity()));
        List<PageSimpleOrderDetail.SingleOrderItem>  result=new ArrayList<>(orders.size());
        orders.forEach(o->{
            PageSimpleOrderDetail.SingleOrderItem orderItem=new PageSimpleOrderDetail.SingleOrderItem();
            orderItem.setId(o.getId());
            orderItem.setUserAddress(addressItemMap.getOrDefault(o.getAddressId(),new AddressInfo.AddressItem(-1L)).toUserAddress());
            orderItem.setGoodsItems(buildOrderGoodsItem(o,goodsInfoMap));
            result.add(orderItem);
        });
        return result;
    }

    /**
     * description:  构造订单中的商品信息
     * @param order 订单
     * @param goodsInfoMap
     * @return {@link List<PageSimpleOrderDetail.SingleOrderGoodsItem>}
     * @author: feather
     * @since: 2024-04-05 20:31
     **/
    private List<PageSimpleOrderDetail.SingleOrderGoodsItem> buildOrderGoodsItem(
            Orders order, Map<Long, SimpleGoodsInfo> goodsInfoMap
    ) {
    List<PageSimpleOrderDetail.SingleOrderGoodsItem> goodsItems=new ArrayList<>();
        List<DeductGoodsInventory> deductGoodsInventoryList = JSON.parseArray(order.getOrderDetail(), DeductGoodsInventory.class);
        deductGoodsInventoryList.forEach(gc->{
            PageSimpleOrderDetail.SingleOrderGoodsItem goodsItem=new PageSimpleOrderDetail.SingleOrderGoodsItem();
            goodsItem.setCount(gc.getCount());
            goodsItem.setSimpleGoodsInfo(goodsInfoMap.getOrDefault(gc.getGoodsId(),new SimpleGoodsInfo(-1L)));
            goodsItems.add(goodsItem);
        });
        return goodsItems;
    }
}
