package org.feather.skill.conf;

import org.feather.skill.filter.LoginUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @projectName: skill
 * @package: org.feather.skill.conf
 * @className: FeatherWebMvcConfig
 * @author: feather
 * @description: web mvc 配置，
 * @since: 2024-01-23 19:36
 * @version: 1.0
 */
@Configuration
public class FeatherWebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        //添加用户统身份统一登录的拦截器
        registry.addInterceptor(new LoginUserInfoInterceptor()).
                addPathPatterns("/**")
                .order(0);
    }

}
