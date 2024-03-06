package org.feather.skill.service.communication;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.communication
 * @className: FeignConfig
 * @author: feather
 * @description: openFeign 配置类
 * @since: 2024-03-02 17:17
 * @version: 1.0
 */
@Configuration
public class FeignConfig {
    public static final int CONNECT_TIMEOUT_MILLS = 5000;
    public static final int READ_TIMEOUT_MILLS = 5000;

    /**
     * 开启openfeign 日志
     * @return
     */
    @Bean
    public Logger.Level  feignLogger(){
        //日志级别需要修改为Debug
        return Logger.Level.FULL;
    }

    /**
     * <h2>OpenFeign 开启重试</h2>
     * period = 100 发起当前请求的时间间隔, 单位是 ms
     * maxPeriod = 1000 发起当前请求的最大时间间隔, 单位是 ms
     * maxAttempts = 5 最多请求次数
     * */
    @Bean
    public Retryer feignRetryer(){
        return  new Retryer.Default(100,
                SECONDS.toMillis(1),
                5);

    }

    /**
     * <h2>对请求的连接和响应时间进行限制</h2>
     * */
    @Bean
    public Request.Options options() {
        return new Request.Options(
                CONNECT_TIMEOUT_MILLS, TimeUnit.MICROSECONDS,
                READ_TIMEOUT_MILLS, TimeUnit.MILLISECONDS,
                true
        );
    }

}
