package org.feather.skill.controller;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.annotation.IgnoreResponseAdvice;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.service.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: AuthorityController
 * @author: feather
 * @description: 对外暴露的授权服务接口
 * @since: 14-Jan-24 2:21 PM
 * @version: 1.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    private  final IJWTService ijwtService;

    /**
     * description:  从授权中心获取 Token (其实就是登录功能), 且返回信息中没有统一响应的包装
     * @param usernameAndPassword 请求对象
     * @return {@link JwtToken}
     * @author: feather
     * @since: 14-Jan-24 2:23 PM
     **/
    @IgnoreResponseAdvice
    @PostMapping("/token")
    public JwtToken token(@RequestBody UserNameAndPassword usernameAndPassword)
            throws Exception {
        log.info("request to get token with param: [{}]",
                JSON.toJSONString(usernameAndPassword));
        return new JwtToken(ijwtService.generateToken(
                usernameAndPassword.getUsername(),
                usernameAndPassword.getPassword()
        ));
    }
   /**
    * description:  注册用户并返回当前注册用户的 Token, 即通过授权中心创建用户
    * @param userNameAndPassword 请求对象
    * @return {@link JwtToken}
    * @author: feather
    * @since: 14-Jan-24 2:26 PM
    **/
    @IgnoreResponseAdvice
    @PostMapping("/register")
    public JwtToken register(@RequestBody UserNameAndPassword userNameAndPassword)
            throws Exception {

        log.info("register user with param: [{}]", JSON.toJSONString(
                userNameAndPassword
        ));
        return new JwtToken(ijwtService.registerUserAndGenerateToken(
                userNameAndPassword
        ));
    }

}
