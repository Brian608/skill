package org.feather.skill.feign;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: FeignConfig
 * @author: feather
 * @description: feign在调用时，吧header也传递到服务提供方
 * @since: 2024-03-30 17:32
 * @version: 1.0
 */
@Slf4j
@Configuration
public class FeignConfig {

    /**
     * description: 给feign 配置拦截器
     * RequestInterceptor 是我们提供给 open-feign 的请求拦截器, 把 Header 信息传递
     * @return {@link RequestInterceptor}
     * @author: feather
     * @since: 2024-03-30 17:34
     **/
    @Bean
    public RequestInterceptor  headerInterceptor(){
        return  template->{
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (null != headerNames) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        // 不能把当前请求的 content-length 传递到下游的服务提供方, 这明显是不对的
                        // 请求可能一直返回不了, 或者是请求响应数据被截断
                        if (!name.equalsIgnoreCase("content-length")) {
                            // 这里的 template 就是 RestTemplate
                            template.header(name, values);
                        }
                    }
                }
            }
        };

    }


}
