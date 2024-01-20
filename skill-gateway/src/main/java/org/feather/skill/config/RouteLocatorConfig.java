package org.feather.skill.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: skill
 * @package: org.feather.skill.config
 * @className: RouteLocatorConfig
 * @author: feather
 * @description: 配置登录请求转发规则
 * @since: 20-Jan-24 5:14 PM
 * @version: 1.0
 */
@Configuration
public class RouteLocatorConfig {
   /**
    * description:  使用代码定义路由规则，在网关层面拦截下登录和注册接口
    * @param builder builder
    * @return {@link RouteLocator}
    * @author: feather
    * @since: 20-Jan-24 5:15 PM
    **/
    @Bean
    public RouteLocator loginRouteLocator(RouteLocatorBuilder builder){
        // 手动定义 Gateway 路由规则需要指定 id、path 和 uri
        return builder.routes()
                .route(
                        "skill_authority",
                        r -> r.path(
                                "/feather/skill/login",
                                "/feather/skill/register"
                        ).uri("http://localhost:9001/")
                ).build();
    }
}
