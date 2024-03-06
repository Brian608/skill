package org.feather.skill.controller;

import lombok.RequiredArgsConstructor;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.service.communication.AuthorityFeignClient;
import org.feather.skill.service.communication.UseFeignApi;
import org.feather.skill.service.communication.UseRestTemplateService;
import org.feather.skill.service.communication.UseRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: CommunicationController
 * @author: feather
 * @description: 微服务通讯
 * @since: 2024-03-02 11:21
 * @version: 1.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/communication")
@RestController
public class CommunicationController {

    private  final UseRestTemplateService useRestTemplateService;

    private final UseRibbonService ribbonService;

    private final AuthorityFeignClient feignClient;

    private  final UseFeignApi useFeignApi;
    @PostMapping("/rest-template")
    public FeatherResponse<JwtToken> getTokenFromAuthorityService(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success(useRestTemplateService.getTokenFromAuthorityService(userNameAndPassword));
    }


    @PostMapping("/rest-template-load-balancer")
    public FeatherResponse<JwtToken> getTokenFromAuthorityServiceWithLoadBalancer(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success(useRestTemplateService.getTokenFromAuthorityServiceWithLoadBalance(userNameAndPassword));
    }
    @PostMapping("/ribbon")
    public FeatherResponse<JwtToken> getTokenFromAuthorityServiceByRibbon(
            @RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success(ribbonService.getTokenFromAuthorityServiceByRibbon(userNameAndPassword));
    }
    @PostMapping("/thinking-in-ribbon")
    public FeatherResponse<JwtToken> thinkingInRibbon(@RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success( ribbonService.thinkingInRibbon(userNameAndPassword));
    }

    @PostMapping("/token-by-feign")
    public FeatherResponse<JwtToken> getTokenByFeign(@RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success( feignClient.getTokenByFeign (userNameAndPassword));
    }

    @PostMapping("/thinking-in-feign")
    public FeatherResponse<JwtToken> thinkingInFeign(@RequestBody UserNameAndPassword userNameAndPassword) {
        return FeatherResponse.success(useFeignApi.thinkingInFeign(userNameAndPassword));
    }

}
