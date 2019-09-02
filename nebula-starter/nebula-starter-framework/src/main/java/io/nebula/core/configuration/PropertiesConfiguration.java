package io.nebula.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/18
 */
@Configuration
public class PropertiesConfiguration {

    @Configuration
    @Order(-2147483647)
    @Profile("dev")
    @PropertySource("classpath:META-INF/nebula/env-dev.properties")
    static class Dev {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("sit")
    @PropertySource("classpath:META-INF/nebula/env-sit.properties")
    static class Sit {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("uat")
    @PropertySource("classpath:META-INF/nebula/env-uat.properties")
    static class Uat {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("pre")
    @PropertySource("classpath:META-INF/nebula/env-pre.properties")
    static class Pre {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("pro")
    @PropertySource("classpath:META-INF/nebula/env-pro.properties")
    static class Pro {
    }
}
