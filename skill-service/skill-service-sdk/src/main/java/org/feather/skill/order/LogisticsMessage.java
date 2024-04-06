package org.feather.skill.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.order
 * @className: LogisticsMessage
 * @author: feather
 * @description:
 * @since: 2024-04-02 20:21
 * @version: 1.0
 */
@ApiModel(description = "stream 物流消息对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsMessage {

    @ApiModelProperty(value = "用户主键id")
    private Long userId;


    @ApiModelProperty(value = "订单主键id")
    private Long orderId;

    @ApiModelProperty(value = "用户地址主键id")
    private Long addressId;

    @ApiModelProperty(value = "备注信息(json存储)")
    private String extraInfo;
}
