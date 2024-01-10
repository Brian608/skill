package org.feather.skill.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: NacosClientService
 * @author: feather
 * @description:
 * @since: 10-Jan-24 11:01 PM
 * @version: 1.0
 */
@Slf4j
@Service
public class NacosClientService {

    private final DiscoveryClient discoveryClient;

    public NacosClientService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    /**
     * description:  打印 Nacos Client 信息到日志中
     * @param serviceId 服务id
     * @return {@link List<ServiceInstance>}
     * @author: feather
     * @since: 10-Jan-24 11:01 PM
     **/
    public List<ServiceInstance> getNacosClientInfo(String serviceId) {
        log.info("request nacos client to get service instance info: [{}]", serviceId);
        return discoveryClient.getInstances(serviceId);
    }
}
