package com.rhea.common.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.rhea.*.mapper")
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceAutoConfiguration {

}
