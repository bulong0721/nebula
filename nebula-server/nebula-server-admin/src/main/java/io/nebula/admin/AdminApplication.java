package io.nebula.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.nebula.core.annotation.EnableFramework;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/4/19
 */

@Configuration
@EnableAutoConfiguration
@EnableFramework
@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class);
    }
}
