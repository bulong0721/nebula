package com.rhea.messaging.configuration;

import lombok.Builder;
import lombok.Data;

/**
 * @author 050618
 */
@Data
public class TopicConfig {
    private String serverUrl = "oms:rocketmq://localhost:9876/namespace";
    private String group;
    private String topic;
}
