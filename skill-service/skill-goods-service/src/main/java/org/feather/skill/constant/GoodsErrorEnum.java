package org.feather.skill.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.feather.skill.common.enums.BaseErrorCodeEnum;

/**
 * @projectName: skill
 * @package: org.feather.skill.constant
 * @className: GoodsErrorEnums
 * @author: feather
 * @description:
 * @since: 2024-02-07 16:10
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum GoodsErrorEnum  implements BaseErrorCodeEnum {


    DEDUCT_ERROR("100001","扣减库存数量错误"),

    GOODS_NOT_EXIST("100002","错误的商品不存在"),

    GOODS_INVENTORY_NOT_ENOUGH("100003","库存不足"),

    ;
    private final String errorCode;

    private final String errorMsg;
}
