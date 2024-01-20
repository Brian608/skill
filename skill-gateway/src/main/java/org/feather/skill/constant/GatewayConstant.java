package org.feather.skill.constant;

/**
 * @projectName: skill
 * @package: org.feather.skill.constant
 * @className: GatewayConstant
 * @author: feather
 * @description: 网关常量定义
 * @since: 20-Jan-24 2:02 PM
 * @version: 1.0
 */
public interface GatewayConstant {
    /** 登录 uri */
    String LOGIN_URI = "/skill/login";

    /** 注册 uri */
    String REGISTER_URI = "/skill/register";

    /** 去授权中心拿到登录 token 的 uri 格式化接口 */
    String AUTHORITY_CENTER_TOKEN_URL_FORMAT =
            "http://%s:%s/skill-authority-center/authority/token";

    /** 去授权中心注册并拿到 token 的 uri 格式化接口 */
    String AUTHORITY_CENTER_REGISTER_URL_FORMAT =
            "http://%s:%s/skill-authority-center/authority/register";
}
