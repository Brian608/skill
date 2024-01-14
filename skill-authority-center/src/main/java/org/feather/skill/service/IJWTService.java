package org.feather.skill.service;

import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.entity.SkillUser;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: IJWTService
 * @author: feather
 * @description: jwt 相关服务接口定义
 * @since: 14-Jan-24 11:22 AM
 * @version: 1.0
 */
public interface IJWTService {

    /**
     * description: 生成jwt token  使用默认的超时时间
     * @param username 用户名
     * @param password 密码
     * @return {@link String}
     * @author: feather
     * @since: 14-Jan-24 11:24 AM
     **/
    String generateToken(String username,String password) throws Exception;

    /**
     * description: 生成指定超时时间的token 单位是天
     * @param username 账户
     * @param password 密码
     * @param expire 过期时间  单位天
     * @return {@link String}
     * @author: feather
     * @since: 14-Jan-24 11:35 AM
     **/
    String generateToken(String username,String password,int expire)  throws Exception;

    /**
     * description:  注册用户并生成 Token
     * @param usernameAndPassword 账户密码
     * @return {@link String}
     * @author: feather
     * @since: 14-Jan-24 11:35 AM
     **/
    String registerUserAndGenerateToken(UserNameAndPassword usernameAndPassword)
            throws Exception;

}
