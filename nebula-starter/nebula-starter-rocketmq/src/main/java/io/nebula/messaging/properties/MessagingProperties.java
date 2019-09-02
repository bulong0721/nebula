package io.nebula.messaging.properties;

import io.nebula.util.StringUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/11
 */
@Data
@ConfigurationProperties(prefix = "nebula.messaging")
public class MessagingProperties {
    private String serverUrl;
    private String producerGroup;
    private String consumerGroup;

    /**
     * 填充默认属性
     *
     * @param config
     */
    public void stuffConfig(ProduceConfig config) {
        if (StringUtil.isBlank(config.getServerUrl())) {
            config.setServerUrl(serverUrl);
        }
        if (StringUtil.isBlank(config.getGroup())) {
            config.setGroup(producerGroup);
        }
    }

    /**
     * 填充默认属性
     *
     * @param config
     */
    public void stuffConfig(ConsumeConfig config) {
        if (StringUtil.isBlank(config.getServerUrl())) {
            config.setServerUrl(serverUrl);
        }
        if (StringUtil.isBlank(config.getGroup())) {
            config.setGroup(consumerGroup);
        }
    }
}
