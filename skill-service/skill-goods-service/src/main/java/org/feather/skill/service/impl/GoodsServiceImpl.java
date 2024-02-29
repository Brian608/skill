package org.feather.skill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.feather.skill.common.TableId;
import org.feather.skill.constant.GoodsConstant;
import org.feather.skill.constant.GoodsErrorEnum;
import org.feather.skill.entity.Goods;
import org.feather.skill.exception.BusinessException;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.feather.skill.mapper.GoodsMapper;
import org.feather.skill.page.BasePage;
import org.feather.skill.service.IGoodsService;
import org.feather.skill.utils.ParamCheckUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    private  final GoodsMapper goodsMapper;

    private final StringRedisTemplate redisTemplate;

    @Override
    public Goods findByGoodsCategoryAndBrandCategoryAndGoodsName(String goodsCategory, String brandCategory, String goodsName) {
        return this.getOne(
                new LambdaQueryWrapper<Goods>().
                        eq(Goods::getGoodsCategory,goodsCategory).
                        eq(Goods::getBrandCategory,brandCategory)
                        .eq(Goods::getGoodsName,goodsName));
    }

    @Override
    public List<GoodsInfo> getGoodsInfoByTableId(TableId tableId) {
        //详细的商品信息不能从缓存中去取，应该从数据库取
        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get goods info by ids:[{}]", JSON.toJSONString(ids));
        List<Goods> goodsList = goodsMapper.selectBatchIds(ids);
        return goodsList.stream().map(Goods::toGoodsInfo).collect(Collectors.toList());
    }

    @Override
    public Page<SimpleGoodsInfo> getSimpleGoodsInfoByPage(BasePage basePage) {
        Integer pageSize = basePage.getPageSize();
        //分页不能从不敢缓存中拿
        Page<Goods> page=new Page<>(basePage.getPageNo(), pageSize);
        Page<Goods> goodsPage = this.goodsMapper.selectPage(page, new LambdaQueryWrapper<Goods>().orderByDesc(Goods::getCreateTime));
        List<Goods> records = goodsPage.getRecords();
        List<SimpleGoodsInfo> collect = records.stream().map(Goods::toSimple).collect(Collectors.toList());
        Page<SimpleGoodsInfo> pagingInfo=new Page<>();
        pagingInfo.setTotal(goodsPage.getTotal());
        pagingInfo.setRecords(collect);
        pagingInfo.setCurrent(goodsPage.getCurrent());
        pagingInfo.setSize(goodsPage.getSize());
        return pagingInfo;
    }

    @Override
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {
        //获取商品的简单信息，可以从redis 中拿，拿不到从DB，并保存到redis中
        //redis 中的kv 都是字符串类型
        List<Object> goodIds = tableId.getIds().stream().map(i -> i.getId().toString()).collect(Collectors.toList());
        //FIXME 如果 cache 中查不到 goodsId 对应的数据, 返回的是 null, [null, null]
        List<Object> cachedSimpleGoodsInfos = redisTemplate.opsForHash().multiGet(GoodsConstant.SKILL_GOODS_DICT_KEY,goodIds)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());;
        // 如果从 Redis 中查到了商品信息, 分两种情况去操作
        if (CollectionUtils.isNotEmpty(cachedSimpleGoodsInfos)){
            //1：如果从缓存中查询出所需的SimpleGoodsInfo
            if (cachedSimpleGoodsInfos.size()==goodIds.size()){
                log.info("get simple goods info by ids (from cache): [{}]",
                        JSON.toJSONString(goodIds));
                return parseCachedGoodsInfo(cachedSimpleGoodsInfos);
            }else {
                // 2. 一半从数据表中获取 (right), 一半从 redis cache 中获取 (left)
                List<SimpleGoodsInfo> left = parseCachedGoodsInfo(cachedSimpleGoodsInfos);
                //取差集，传递进来的参数-缓存中查询到的参数=缓存中没有的
                Collection<Long> subtractIds = CollectionUtils.subtract(
                        goodIds.stream()
                                .map(g -> Long.valueOf(g.toString())).collect(Collectors.toList()),
                        left.stream()
                                .map(SimpleGoodsInfo::getId).collect(Collectors.toList())
                );
                //缓存中没有的  查询数据表并 缓存
                List<SimpleGoodsInfo> right=queryGoodsFromDBAndCacheToRedis(
                        new TableId(subtractIds.stream().map(TableId.Id::new).collect(Collectors.toList()))
                );
                // 合并 left 和 right 并返回
                log.info("get simple goods info by ids (from db and cache): [{}]",
                        JSON.toJSONString(subtractIds));
                return new ArrayList<>(CollectionUtils.union(left, right));

            }
        }else {
            return queryGoodsFromDBAndCacheToRedis(tableId);
        }
    }

    @Override
    public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
        //检验下参数是否合法
        deductGoodsInventories.forEach(d->{
            if (d.getCount()<=0){
                throw new BusinessException(GoodsErrorEnum.DEDUCT_ERROR);
            }
        });
        List<Goods> goodsList = this.goodsMapper.selectBatchIds(deductGoodsInventories.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toList()));
        //根据传递的goodsIds 查询不到商品信息，抛异常
        ParamCheckUtil.checkCollectionNonEmpty(goodsList,GoodsErrorEnum.GOODS_NOT_EXIST);
        //查询出来的商品与传递的数量不一致 抛异常
        if (goodsList.size()!=deductGoodsInventories.size()){
            throw new BusinessException("request is not valid");
        }
        //goodsId---> DeductGoodsInventory
        Map<Long, DeductGoodsInventory> goodsId2Inventory = deductGoodsInventories.stream().collect(Collectors.toMap(DeductGoodsInventory::getGoodsId, Function.identity()));
        //检查是否可以扣减库存
        goodsList.forEach(g->{
            Long inventory = g.getInventory();
            Integer needDeductInventory = goodsId2Inventory.get(g.getId()).getCount();
            if (inventory<needDeductInventory){
                log.error("goods inventory is not enough:[{}],[{}]",inventory,needDeductInventory);
                throw new BusinessException(GoodsErrorEnum.GOODS_INVENTORY_NOT_ENOUGH);
            }
            //扣减库存
            //TODO 超卖问题
            g.setInventory(inventory-needDeductInventory);
            log.info("deduct goods inventory :[{}],[{}],[{}]",g.getId(),inventory,g.getInventory());

        });
        this.updateBatchById(goodsList);
        log.info("deduct goods inventory done");
        return true;
    }

   /**
    * description:  将缓存中的数据反序列化成 Java Pojo 对象
    * @param cachedSimpleGoodsInfo
    * @return {@link List<SimpleGoodsInfo>}
    * @author: feather
    * @since: 2024-02-07 11:54
    **/
    private List<SimpleGoodsInfo> parseCachedGoodsInfo(List<Object> cachedSimpleGoodsInfo) {

        return cachedSimpleGoodsInfo.stream()
                .map(s -> JSON.parseObject(s.toString(), SimpleGoodsInfo.class))
                .collect(Collectors.toList());
    }
    /**
     * description: 从数据表中查询数据, 并缓存到 Redis 中
     * @param tableId
     * @return {@link List<SimpleGoodsInfo>}
     * @author: feather
     * @since: 2024-02-07 15:30
     **/
    private List<SimpleGoodsInfo> queryGoodsFromDBAndCacheToRedis(TableId tableId) {
        //从数据表中查询数据并做转换
        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get simple goods info by ids(from db) :[{}]",JSON.toJSONString(ids));
        List<Goods> goodsList = this.goodsMapper.selectBatchIds(ids);
        List<SimpleGoodsInfo> simpleGoodsInfos = goodsList.stream().map(Goods::toSimple).collect(Collectors.toList());
        //将结果缓存，下一次直接从redis 中查询
        log.info("cache goods info:[{}]",JSON.toJSONString(ids));
        Map<String,String> id2JsonObject=new HashMap<>(simpleGoodsInfos.size());
        simpleGoodsInfos.forEach(s->id2JsonObject.put(s.getId().toString(),JSON.toJSONString(s)));
        //保存到redis 中
        redisTemplate.opsForHash().putAll(GoodsConstant.SKILL_GOODS_DICT_KEY,id2JsonObject);
        return simpleGoodsInfos;

    }
}
