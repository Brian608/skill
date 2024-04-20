package org.feather.skill.feign;

import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: SentinelFeignClient
 * @author: feather
 * @description: 通过sentinel对openfeign  实现熔断降级
 * @since: 2024-04-20 09:56
 * @version: 1.0
 */
@FeignClient(
        value = "skill-feather",
        fallback = SentinelFeignClientFallBack.class
)
public interface SentinelFeignClient {


    @RequestMapping(value = "/feather", method = RequestMethod.GET)
    FeatherResponse<String> getResultByFeign(@RequestParam Integer code);

}
