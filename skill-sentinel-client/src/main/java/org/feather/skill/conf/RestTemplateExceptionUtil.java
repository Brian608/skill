package org.feather.skill.conf;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.JwtToken;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * @projectName: skill
 * @package: org.feather.skill.conf
 * @className: RestTemplateExceptionUtil
 * @author: feather
 * @description:  restTemplate 在限流或异常的兜底方法
 * @since: 2024-04-14 12:49
 * @version: 1.0
 */
@Slf4j
public class RestTemplateExceptionUtil {
    /**
     * description: 限流后的处理方法
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return {@link SentinelClientHttpResponse}
     * @author: feather
     * @since: 2024-04-14 12:50
     **/
    public static SentinelClientHttpResponse handleBlock(HttpRequest request,
                                                         byte [] body,
                                                         ClientHttpRequestExecution execution,
                                                         BlockException ex){
        log.error("Handle RestTemplate Block Exception: [{}], [{}]",
                request.getURI().getPath(), ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new JwtToken("feather-block")));
    }
    /**
     * description: 异常降级之后的处理方法
     * @param request
     * @param body
     * @param execution
     * @param ex
     * @return {@link SentinelClientHttpResponse}
     * @author: feather
     * @since: 2024-04-14 12:53
     **/
    public  static  SentinelClientHttpResponse handleFallback(
            HttpRequest request,
            byte [] body,
            ClientHttpRequestExecution execution,
            BlockException ex
    ){
        log.error("Handle RestTemplate Fallback Exception: [{}], [{}]",
                request.getURI().getPath(), ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse(
                JSON.toJSONString(new JwtToken("feather-block"))
        );
    }

}
