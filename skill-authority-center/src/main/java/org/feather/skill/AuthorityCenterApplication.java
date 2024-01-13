package org.feather.skill;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: AuthorityCenterApplication
 * @author: feather
 * @description: 授权中心启动入口
 * @since: 13-Jan-24 6:37 PM
 * @version: 1.0
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class AuthorityCenterApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(AuthorityCenterApplication.class);
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
