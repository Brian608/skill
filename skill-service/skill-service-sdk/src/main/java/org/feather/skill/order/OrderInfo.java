package org.feather.skill.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.skill.goods.DeductGoodsInventory;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.order
 * @className: OrderInfo
 * @author: feather
 * @description: 订单信息
 * @since: 2024-03-30 17:03
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel (description = "用户发起购买订单")
public class OrderInfo {

    @ApiModelProperty(value = "用户地址主键id")
    private Long userAddressId;

    @ApiModelProperty(value = "订单中的商品信息")
    private List<OrderItem> orderItems;

    /**
     * <h2>订单中的商品信息</h2>
     * */
    @ApiModel(description = "订单中的单项商品信息")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItem {

        @ApiModelProperty(value = "商品表主键 id")
        private Long goodsId;

        @ApiModelProperty(value = "购买商品个数")
        private Integer count;
        public DeductGoodsInventory toDeductGoodsInventory() {
            return new DeductGoodsInventory(this.goodsId, this.count);
        }

    }
}
