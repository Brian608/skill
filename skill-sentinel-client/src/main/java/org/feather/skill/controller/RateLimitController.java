package org.feather.skill.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.blockHandler.FeatherBlockHandler;
import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: RateLimitController
 * @author: feather
 * @description: 基于sentinel 控制台配置流控规则  sentinel 是懒加载的，先去访问一下，就可以在sentinel Dashboard 看到了
 * @since: 2024-04-13 11:04
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/dashboard")
public class RateLimitController {

    /**
     * description: 在dashboard 中 “流控规则” 中按照资源名称新增流控规则
     * @return {@link FeatherResponse<String>}
     * @author: feather
     * @since: 2024-04-13 17:34
     **/
    @GetMapping("/by-resource")
    @SentinelResource(
            value = "byResource",
            blockHandler = "featherHandleBlockException",
            blockHandlerClass = FeatherBlockHandler.class
    )
    public FeatherResponse<String> btResource(){
        log.info("coming in rate limit controller by resource ");
        return new FeatherResponse<>("0","","byResource");
    }

    /**
     * description: "蔟点链路" 中给url新增流控规则
     * @return {@link FeatherResponse<String>}
     * @author: feather
     * @since: 2024-04-13 17:34
     **/
    @GetMapping("/by-url")
    @SentinelResource(
            value = "byUrl",
            blockHandler = "featherHandleBlockException",
            blockHandlerClass = FeatherBlockHandler.class
    )
    public FeatherResponse<String> byUrl(){
        log.info("coming in rate limit controller by url ");
        return new FeatherResponse<>("0","","byUrl");
    }
}
