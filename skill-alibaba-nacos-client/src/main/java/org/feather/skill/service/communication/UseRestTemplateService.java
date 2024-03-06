package org.feather.skill.service.communication;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.constants.CommonConstant;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.communication
 * @className: UseRestTemplateService
 * @author: feather
 * @description: 使用RestTemplate 实现微服务通讯
 * @since: 2024-03-02 11:06
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UseRestTemplateService {

    private final LoadBalancerClient balancerClient;
    /**
     * description: 从授权服务中获取jwttoken
     * @param userNameAndPassword
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-03-02 11:07
     **/
    public JwtToken getTokenFromAuthorityService(UserNameAndPassword userNameAndPassword){
        // 第一种方式: 写死 url
        String requestUrl = "http://127.0.0.1:7000/skill-authority-center/authority/token";
        log.info("RestTemplate request url and body: [{}], [{}]",
                requestUrl, JSON.toJSONString(userNameAndPassword));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new RestTemplate().postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );

    }
    /**
     * description: 从授权服务中心获取jwttoken 且带有负载均衡
     * @param userNameAndPassword
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-03-02 11:15
     **/
    public JwtToken getTokenFromAuthorityServiceWithLoadBalance(UserNameAndPassword userNameAndPassword){
        //第二种方式，通过注册中心拿到服务的信息(所有的实例)再去发起调用
        ServiceInstance serviceInstance = balancerClient.choose(
                CommonConstant.AUTHORITY_CENTER_SERVICE_ID
        );
        log.info("nacos client info :[{}],[{}],[{}]",
                serviceInstance.getServiceId(),serviceInstance.getInstanceId(),JSON.toJSONString(serviceInstance.getMetadata()));
        String requestUrl=String.format("http://%s:%s/skill-authority-center/authority/token",serviceInstance.getHost(),serviceInstance.getPort());
        log.info("login request url and body:[{}],[{}]",
                requestUrl,
                JSON.toJSONString(userNameAndPassword));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new RestTemplate().postForObject(
                requestUrl,
                new HttpEntity<>(JSON.toJSONString(userNameAndPassword), headers),
                JwtToken.class
        );
    }

}
