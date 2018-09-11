package com.rhea.messaging.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 050618
 */
@Data
@ConfigurationProperties(prefix = "rhea.mq")
public class MQProperties {
    private String serverUrl;
    private String producerGroup;
    private String consumerGroup;
}
