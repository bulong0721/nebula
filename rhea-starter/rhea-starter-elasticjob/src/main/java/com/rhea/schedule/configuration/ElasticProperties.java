package com.rhea.schedule.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xubulong
 */
@Data
@Component
@ConfigurationProperties(prefix = "rhea.elastic")
public class ElasticProperties {

    private String zkServer;

    private String namespace;
}
