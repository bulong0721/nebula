package com.rhea.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 05061
 * @version V1.0 created at: 2018/10/18
 */
@Configuration
public class PropertiesConfiguration {

    @Configuration
    @Profile("dev")
    @PropertySource("classpath:META-INF/rhea/env-dev.properties")
    static class Dev {
    }

    @Configuration
    @Profile("uat")
    @PropertySource("classpath:META-INF/rhea/env-uat.properties")
    static class Uat {
    }

    @Configuration
    @Profile("pre")
    @PropertySource("classpath:META-INF/rhea/env-pre.properties")
    static class Pre {
    }

    @Configuration
    @Profile("pro")
    @PropertySource("classpath:META-INF/rhea/env-pro.properties")
    static class Pro {
    }
}
