package org.feather.skill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: SentinelClientApplication
 * @author: feather
 * @description:
 * @since: 2024-04-12 08:19
 * @version: 1.0
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelClientApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SentinelClientApplication.class);
        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n\t"+
                        "--------------------------------------------------------------",
                applicationName);
    }
}
