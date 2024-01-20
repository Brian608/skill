package org.feather.skill.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @projectName: skill
 * @package: org.feather.skill.filter
 * @className: HeadTokenGateWayFilter
 * @author: feather
 * @description: 请求头部携带token 验证过滤器
 * @since: 20-Jan-24 1:51 PM
 * @version: 1.0
 */
public class HeadTokenGateWayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //从http Header 中寻找key 为token ,value  为feather 的键值对
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if ("feather".equals(token)){
            return chain.filter(exchange);
        }
        //标记此次请求没有权限，并结束这次请求
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE+2;
    }
}
