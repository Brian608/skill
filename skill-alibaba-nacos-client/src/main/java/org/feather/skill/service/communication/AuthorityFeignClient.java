package org.feather.skill.service.communication;

import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.communication
 * @className: AuthorityFeignClient
 * @author: feather
 * @description: 与Authority 服务通信的Feign Client 接口定义
 * @since: 2024-03-02 16:57
 * @version: 1.0
 */
@FeignClient(contextId = "AuthorityFeignClient",value = "skill-authority-center"
        // fallback = AuthorityFeignClientFallBack.class
)
public interface AuthorityFeignClient {

    /**
     * description:  通过 OpenFeign 访问 Authority 获取 Token
     * @param userNameAndPassword
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-03-02 17:00
     **/
    @RequestMapping(value = "/skill-authority-center/authority/token",
            method = RequestMethod.POST,
            consumes = "application/json",produces = "application/json")
    JwtToken getTokenByFeign(@RequestBody UserNameAndPassword userNameAndPassword);
}
