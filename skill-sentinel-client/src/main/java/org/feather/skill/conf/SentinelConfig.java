package org.feather.skill.conf;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: skill
 * @package: org.feather.skill.conf
 * @className: SentinelConfig
 * @author: feather
 * @description: 开启服务之间调用的服务保护 需要给RestTemplate做 一些包装
 * @since: 2024-04-14 12:46
 * @version: 1.0
 */
@Slf4j
@Configuration
public class SentinelConfig {
    @Bean
//    @SentinelRestTemplate(
//            fallback = "handleFallback",fallbackClass = RestTemplateExceptionUtil.class,
//            blockHandler = "handleBlock",blockHandlerClass = RestTemplateExceptionUtil.class
//    )
    public RestTemplate restTemplate(){
        //可以对其做一些业务相关的配置
        return  new RestTemplate();
    }
}
