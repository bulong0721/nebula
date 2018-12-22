package com.rhea.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * @author xubulong
 * @version V1.0
 */
@Configuration
public class PropertiesConfiguration {

    @Configuration
    @Order(-2147483647)
    @Profile("dev")
    @PropertySource("classpath:META-INF/rhea/env-dev.properties")
    static class Dev {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("uat")
    @PropertySource("classpath:META-INF/rhea/env-uat.properties")
    static class Uat {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("pre")
    @PropertySource("classpath:META-INF/rhea/env-pre.properties")
    static class Pre {
    }

    @Configuration
    @Order(-2147483647)
    @Profile("pro")
    @PropertySource("classpath:META-INF/rhea/env-pro.properties")
    static class Pro {
    }
}
