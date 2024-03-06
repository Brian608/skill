package org.feather.skill.service.communication;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.hateoas.FeignHalAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.communication
 * @className: FeignOkhttpConfig
 * @author: feather
 * @description: openFeign 使用okHttp配置类
 * @since: 2024-03-02 17:25
 * @version: 1.0
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignHalAutoConfiguration.class)
public class FeignOkhttpConfig {
    /**
     * 注入okhttp 并自定义配置
     * @return
     */
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)    // 设置连接超时
                .readTimeout(5, TimeUnit.SECONDS)   // 设置读超时
                .writeTimeout(5, TimeUnit.SECONDS)  // 设置写超时
                .retryOnConnectionFailure(true)     // 是否自动重连
                // 配置连接池中的最大空闲线程个数为 10, 并保持 5 分钟
                .connectionPool(new ConnectionPool(
                        10, 5L, TimeUnit.MINUTES))
                .build();
    }
}
