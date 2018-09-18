package com.rhea.messaging.configuration;

import com.google.common.base.Strings;
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

    /**
     * 填充默认属性
     * @param config
     */
    public void stuffConfig(ProducerConfig config) {
        if (Strings.isNullOrEmpty(config.getServerUrl())) {
            config.setServerUrl(serverUrl);
        }
        if (Strings.isNullOrEmpty(config.getGroup())) {
            config.setGroup(producerGroup);
        }
    }

    /**
     * 填充默认属性
     * @param config
     */
    public void stuffConfig(ConsumerConfig config) {
        if (Strings.isNullOrEmpty(config.getServerUrl())) {
            config.setServerUrl(serverUrl);
        }
        if (Strings.isNullOrEmpty(config.getGroup())) {
            config.setGroup(consumerGroup);
        }
    }
}
