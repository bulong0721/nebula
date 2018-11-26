package com.rhea.mq.configuration;

import lombok.Data;

/**
 * @author xubulong8
 */
@Data
public class TopicConfig {
    private String serverUrl = "oms:rocketmq://localhost:9876/namespace";
    private String group;
    private String topic;
}
