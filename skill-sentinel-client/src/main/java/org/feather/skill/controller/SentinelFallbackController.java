package org.feather.skill.controller;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.fallbackHandler.FeatherFallbackHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: SentinelFallbackController
 * @author: feather
 * @description: 提供容错降级的功能
 * @since: 2024-04-20 08:26
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/sentinel-fallback")
public class SentinelFallbackController {

        // 注入没有增强的 restTemplate
    private  final RestTemplate restTemplate;

    @SentinelResource(
            value = "getTokenFromAuthorityService",
            fallback = "getTokenFromAuthorityServiceFallback",
            fallbackClass = {FeatherFallbackHandler.class}
    )
    @PostMapping("/get-token")
    public JwtToken getTokenFromAuthorityService(@RequestBody UserNameAndPassword userNameAndPassword){
        String requestUrl="http://127.0.0.1:7000/skill-authority-center/authority/token";
        log.info("RestTemplate request url and body: [{}], [{}]",
                requestUrl, JSON.toJSONString(userNameAndPassword));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );

    }
   /**
    * description:  让 Sentinel 忽略一些异常
    * @param code
    * @return {@link JwtToken}
    * @author: feather
    * @since: 2024-04-20 08:42
    **/
    @GetMapping("/ignore-exception")
    @SentinelResource(
            value = "ignoreException",
            fallback = "ignoreExceptionFallback",
            fallbackClass = { FeatherFallbackHandler.class },
            exceptionsToIgnore = { NullPointerException.class }
    )
    public JwtToken ignoreException(@RequestParam Integer code) {

        if (code % 2 == 0) {
            throw new NullPointerException("you input code is: " + code);
        }

        return new JwtToken("feather-token");
    }



}
