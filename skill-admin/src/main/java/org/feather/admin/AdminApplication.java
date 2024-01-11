package org.feather.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * @projectName: skill
 * @package: org.feather.admin
 * @className: AdminApplication
 * @author: feather
 * @description: 监控中心
 * @since: 11-Jan-24 2:57 PM
 * @version: 1.0
 */
@Slf4j
@EnableAdminServer
@SpringBootApplication
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdminApplication.class);
        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n\t"+
                  "--------------------------------------------------------------",
                applicationName);
    }
}
