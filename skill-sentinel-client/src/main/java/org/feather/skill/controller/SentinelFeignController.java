package org.feather.skill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.feign.SentinelFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: SentinelFeignController
 * @author: feather
 * @description: OpenFeign  集成 Sentinel 实现熔断降级
 * @since: 2024-04-20 10:01
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/sentinel-feign")
public class SentinelFeignController {

    private final SentinelFeignClient sentinelFeignClient;

    /**
     * description: 通过Feign 接口去获取结果
     * @param code
     * @return {@link FeatherResponse<String>}
     * @author: feather
     * @since: 2024-04-20 10:02
     **/
    @GetMapping("/result-by-feign")
    public FeatherResponse<String> getResultByFeign (@RequestParam Integer code){
        log.info("coming in get result by feign :[{}]",code);
        return  sentinelFeignClient.getResultByFeign(code);
    }
}
