package org.feather.skill.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.service.NacosClientService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: NacosClientController
 * @author: feather
 * @description:
 * @since: 10-Jan-24 11:02 PM
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/nacos-client")
public class NacosClientController {

    private final NacosClientService nacosClientService;

    public NacosClientController(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }

   /**
    * description:  根据 service id 获取服务所有的实例信息
    * @param serviceId  service id
    * @return {@link List<ServiceInstance>}
    * @author: feather
    * @since: 10-Jan-24 11:03 PM
    **/
    @GetMapping("/service-instance")
    public List<ServiceInstance> logNacosClientInfo(
            @RequestParam(defaultValue = "skill-nacos-client") String serviceId) {

        log.info("coming in log nacos client info: [{}]", serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }
}
