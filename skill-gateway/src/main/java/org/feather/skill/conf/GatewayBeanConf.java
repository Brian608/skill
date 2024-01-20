package org.feather.skill.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @projectName: skill
 * @package: org.feather.skill.conf
 * @className: GatewayBeanConf
 * @author: feather
 * @description: 网关需要注入到容器中的bean
 * @since: 20-Jan-24 3:58 PM
 * @version: 1.0
 */
@Configuration
public class GatewayBeanConf {
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
