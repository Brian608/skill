package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Orders;
import org.feather.skill.order.OrderInfo;
import org.feather.skill.page.BasePage;
import org.feather.skill.vo.PageSimpleOrderDetail;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: IOrderService
 * @author: feather
 * @description:
 * @since: 2024-03-30 16:50
 * @version: 1.0
 */
public interface IOrdersService extends IService<Orders> {

    /**
     * description: 根据用户id 分页查询订单信息
     * @param userId 用户id
     * @param page 分页对象
     * @return {@link Page <Order>}
     * @author: feather
     * @since: 2024-03-30 17:00
     **/
    Page<Orders> findAllByUserId(Long userId, BasePage page);

    /**
     * description: 下单 分布式事务---> 扣减库存--->扣减余额--->创建物流信息(Stream+kafka)
     * @param orderInfo 订单信息
     * @return {@link TableId}
     * @author: feather
     * @since: 2024-03-30 17:17
     **/
    TableId createOrder(OrderInfo orderInfo);

    /**
     * description: 获取当前用户的订单信息: 带有分页
     * @param page 分页对象
     * @return {@link PageSimpleOrderDetail}
     * @author: feather
     * @since: 2024-03-30 17:18
     **/
    PageSimpleOrderDetail getSimpleOrderDetailByPage(BasePage page);
}
