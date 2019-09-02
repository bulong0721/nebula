package io.nebula.messaging.properties;

import io.nebula.messaging.annotation.ConsumeMode;
import io.nebula.messaging.configuration.ConsumerGroup;
import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/11
 */
@Data
public class ConsumeConfig extends TopicConfig {
    private String threadPool;
    private int threadMin;
    private int threadMax;

    public ConsumerGroup toConsumeGroup() {
        ConsumerGroup consumerGroup = new ConsumerGroup();
        consumerGroup.setNameServer(getServerUrl());
        consumerGroup.setThreadPool(getThreadPool());
        consumerGroup.setGroupName(getGroup());
        consumerGroup.setConsumeMode(ConsumeMode.CONCURRENTLY);
        return consumerGroup;
    }
}
