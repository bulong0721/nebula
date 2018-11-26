package com.rhea.mq.configuration;

import lombok.Data;

/**
 * @author xubulong8
 */
@Data
public class ConsumerConfig extends TopicConfig {
    private int threadMin;
    private int threadMax;
    private String tag;
}
