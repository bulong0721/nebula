package com.rhea.kernel.configuration;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 05061
 * @version V1.0 created at: 2018/10/17
 */
@Slf4j
@Configuration
public class DefaultFeign {
    @Bean
    public Feign.Builder feignBuilder() {
        return new Feign.Builder();
    }
}
