package org.feather.skill.feign;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.stereotype.Component;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: SentinelFeignClientFallBack
 * @author: feather
 * @description: sentinel 对 OpenFeign 接口降级策略
 * @since: 2024-04-20 09:59
 * @version: 1.0
 */
@Slf4j
@Component
public class SentinelFeignClientFallBack implements SentinelFeignClient{
    @Override
    public FeatherResponse<String> getResultByFeign(Integer code) {
        log.error( "request  supply for test has some error :[{}]",code);
        return new FeatherResponse<>("-1","sentinel feign fallback","input code:"+code);
    }
}
