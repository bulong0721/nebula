package com.rhea.messaging.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 05061
 */
@Data
@ConfigurationProperties(prefix = "rhea.messaging")
public class MessagingProperties {
    private String serverUrl;
    private String producerGroup;
    private String consumerGroup;
}
