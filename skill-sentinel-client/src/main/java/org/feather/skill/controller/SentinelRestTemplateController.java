package org.feather.skill.controller;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: SentinelRestTemplateController
 * @author: feather
 * @description: 使用Sentinel 保护RestTemplate 服务间调用
 * @since: 2024-04-14 13:00
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/sentinel-rest-template")
public class SentinelRestTemplateController {

    private final RestTemplate restTemplate;

    /**
     * description:从 授权 服务中获取jwtToken
     *  1. 流控降级:
     *      * 是针对于簇点链路中的 http://127.0.0.1:7000/skill-authority-center/authority/token
     *      * 2. 容错降级: 对于服务不可用时不能生效
     * @param userNameAndPassword
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-04-14 13:01
     **/
    @PostMapping("/get-token")
    public JwtToken getTokenFromAuthorityService( @RequestBody UserNameAndPassword userNameAndPassword){
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
}
