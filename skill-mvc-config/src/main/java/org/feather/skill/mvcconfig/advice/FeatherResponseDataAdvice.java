package org.feather.skill.mvcconfig.advice;

import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.mvcconfig.annotation.IgnoreResponseAdvice;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @projectName: skill
 * @package: org.feather.skill.mvcconfig.advice
 * @className: FeatherResponseDataAdvice
 * @author: feather
 * @description:
 * @since: 10-Jan-24 1:24 PM
 * @version: 1.0
 */

@RestControllerAdvice(value = "org.feather.skill")
public class FeatherResponseDataAdvice implements ResponseBodyAdvice<Object>{
    /**
     * <h2>判断是否需要对响应进行处理</h2>
     * */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {

        if (methodParameter.getDeclaringClass()
                .isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        // 定义最终的返回对象
        FeatherResponse<Object> response = new FeatherResponse<>("0", "");

        if (null == o) {
            return response;
        } else if (o instanceof FeatherResponse) {
            response = (FeatherResponse<Object>) o;
        } else {
            response.setData(o);
        }

        return response;
    }
}
