package org.feather.skill.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.account
 * @className: UserAddress
 * @author: feather
 * @description:  用户地址信息
 * @since: 2024-01-24 20:06
 * @version: 1.0
 */
@ApiModel(description = "用户地址信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {

    @ApiModelProperty(value = "用户姓名")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "详细的地址")
    private String addressDetail;
}
