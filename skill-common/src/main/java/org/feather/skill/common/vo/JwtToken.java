package org.feather.skill.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.vo
 * @className: JwtToken
 * @author: feather
 * @description: 授权中心鉴权之后给客户端的token
 * @since: 14-Jan-24 11:19 AM
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    /**
     * jwt
     */
    private String  token;
}
