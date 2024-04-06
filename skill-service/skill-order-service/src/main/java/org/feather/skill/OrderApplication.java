package org.feather.skill;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.feather.skill.conf.DataSourceProxyAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: OrderApplication
 * @author: feather
 * @description:
 * @since: 2024-03-29 20:17
 * @version: 1.0
 */
@Slf4j
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@Import(DataSourceProxyAutoConfiguration.class)
public class OrderApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(OrderApplication.class);
        Environment env = app.run(args).getEnvironment();
        String applicationName = env.getProperty("spring.application.name");
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");


        contextPath = (contextPath == null || contextPath.trim().isEmpty() || "/".equals(contextPath)) ? "" : StringUtils.stripEnd(contextPath, "/");

        String localUrl = String.format("http://localhost:%s%s", serverPort, contextPath);
        String externalUrl = String.format("http://%s:%s%s", InetAddress.getLocalHost().getHostAddress(), serverPort, contextPath);
        String docUrl = String.format("http://%s:%s%s/doc.html", InetAddress.getLocalHost().getHostAddress(), serverPort, contextPath);

        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}\n\t" +
                        "External: \t{}\n\t"+
                        "Doc: \t{}\n"+
                        "----------------------------------------------------------",
                applicationName,
                localUrl,
                externalUrl,
                docUrl);
    }

}
