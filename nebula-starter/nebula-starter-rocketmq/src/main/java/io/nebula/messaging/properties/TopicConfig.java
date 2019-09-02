package io.nebula.messaging.properties;

import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/11
 */
@Data
public class TopicConfig {
    private String serverUrl;
    private String group;
    private String topic;
    private String tag;

    /**
     * 生成发送地址
     *
     * @return
     */
    public String toDestination() {
        return getTopic();
    }
}
