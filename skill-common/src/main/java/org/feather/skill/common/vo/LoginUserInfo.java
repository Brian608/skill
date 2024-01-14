package org.feather.skill.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.vo
 * @className: LoginUserInfo
 * @author: feather
 * @description: 登录用户信息
 * @since: 14-Jan-24 11:20 AM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserInfo {

    /**
     * 用户id
     */
    private Long id;


    /**
     * 用户名
     */
    private String username;
}
