package io.nebula.messaging.configuration;

import io.nebula.messaging.annotation.ConsumeMode;
import lombok.Data;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
@Data
public class ConsumerGroup {
    private ConsumeMode consumeMode;
    private String threadPool;
    private String nameServer;
    private String groupName;
}
