package com.rhea.messaging.configuration;

import lombok.Data;

/**
 * @author 050618
 */
@Data
public class ConsumerConfig extends TopicConfig {
    private int threadMin;
    private int threadMax;
    private String tag;
}
