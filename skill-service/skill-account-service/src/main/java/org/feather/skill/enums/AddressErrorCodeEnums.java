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
public enum AddressErrorCodeEnums implements BaseErrorCodeEnum {

    NOT_EXIST("1000001","地址信息不存在");
    ;

    private final String errorCode;

    private final String errorMsg;
}
