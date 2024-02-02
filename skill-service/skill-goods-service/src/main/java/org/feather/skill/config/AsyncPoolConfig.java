package org.feather.skill.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @projectName: skill
 * @package: org.feather.skill.config
 * @className: AsyncPoolConfig
 * @author: feather
 * @description: 自定义异步任务线程池，异步任务异常捕获处理器
 * @since: 2024-02-02 20:21
 * @version: 1.0
 */
@Slf4j
@EnableAsync  //开启spring异步支持
@Configuration
public class AsyncPoolConfig implements AsyncConfigurer {
    /**
     * description: 自定义的线程池注入到spring容器中
     * @return {@link Executor}
     * @author: feather
     * @since: 2024-02-02 20:22
     **/
    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(20);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("feather-Async");
        //等待所有任务结果执行侯再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationMillis(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化线程池     初始化core 线程
        executor.initialize();
        return executor;
    }

    /**
     * description:  指定系统中的异步任务在出现异常时使用到的处理器
     * @return {@link AsyncUncaughtExceptionHandler}
     * @author: feather
     * @since: 2024-02-02 20:26
     **/
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler();
    }

    /**
     * <h2>异步任务异常捕获处理器</h2>
     * */
    @SuppressWarnings("all")
    class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method,
                                            Object... objects) {

            throwable.printStackTrace();
            log.error("Async Error: [{}], Method: [{}], Param: [{}]",
                    throwable.getMessage(), method.getName(),
                    JSON.toJSONString(objects));

            // TODO 发送邮件或者是短信, 做进一步的报警处理
        }
    }
}
