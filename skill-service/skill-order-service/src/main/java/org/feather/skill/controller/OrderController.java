package org.feather.skill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.order.OrderInfo;
import org.feather.skill.page.BasePage;
import org.feather.skill.service.IOrdersService;
import org.feather.skill.vo.PageSimpleOrderDetail;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: OrderController
 * @author: feather
 * @description:
 * @since: 2024-04-06 10:29
 * @version: 1.0
 */
@RequiredArgsConstructor
@Api(tags = "订单服务")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final IOrdersService ordersService;

    @ApiOperation(
            value = "创建",
            notes = "购买(分布式事务): 创建订单 -> 扣减库存 -> 扣减余额 -> 发送物流消息",
            httpMethod = "POST"
    )
    @PostMapping("/create-order")
    public FeatherResponse<TableId> createOrder(@RequestBody OrderInfo orderInfo) {
        return FeatherResponse.success( ordersService.createOrder(orderInfo));
    }

    @ApiOperation(
            value = "订单信息",
            notes = "获取当前用户的订单信息: 带有分页",
            httpMethod = "POST"
    )
    @PostMapping("/order-detail")
    public FeatherResponse<PageSimpleOrderDetail> getSimpleOrderDetailByPage(
            @RequestBody  BasePage page) {
        return  FeatherResponse.success(ordersService.getSimpleOrderDetailByPage(page));
    }

}
