package org.feather.skill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.feather.skill.common.enums.BaseErrorCodeEnum;

/**
 * @projectName: skill
 * @package: org.feather.skill.enums
 * @className: AddressErrorEnums
 * @author: feather
 * @description:
 * @since: 2024-01-27 18:15
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum AccountErrorCodeEnums implements BaseErrorCodeEnum {

    ADDRESS_NOT_EXIST("1000001","地址信息不存在"),

    BALANCE_IS_NOT_ENOUGH("1000002","账户余额不足"),
    ;

    private final String errorCode;

    private final String errorMsg;
}
