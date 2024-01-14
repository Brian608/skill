package org.feather.skill.common.constants;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.constants
 * @className: CommonConstant
 * @author: feather
 * @description: 通用模块定义
 * @since: 14-Jan-24 11:15 AM
 * @version: 1.0
 */
public interface CommonConstant {
    /**
     * description:  RSA 公钥
     **/
    String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiowjebwg/tAo7/r5qZLlsNPFYQuSzJHDKCO9/JTt6Yr5827zlWV8uEVI2R9EcnBFJZKo4ALxgaOCi2o6h8ESMAaKRYmlbuZjlrcZwuiCU8ObWMSwgZum7L69Fr0ZOEp548vFlPOEWbkY8m96X50Gpov64d5hqqcM4MLZb9OKfIWuQm3xEHRA7DAeXmijo5/zKsvsaA841FPX/udlSAf18lKSRGiMEEJn3Ft5/yWDDnvcz+2BV5M1wGapoIq7yniyE0VbIdHF0ZRHrJwFtJWBH4c51+vqimAPVcDjaPbhYA2ftp4TmWpBSVTlRGaJLjHwksgqRv83KkwG+SiZH+nacQIDAQAB";

    /**
     * description:  jwt 中存储用户信息的key
     **/
    String JWT_USER_INFO_KEY="skill-feather-user";

    /**
     * 授权中心的service-id
     */
    String AUTHORITY_CENTER_SERVICE_ID="skill-authority-center";
}
