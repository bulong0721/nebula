package io.nebula.messaging.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.messaging.properties.ConsumeConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
public class ConsumerContainerFactory {

    private final static ConcurrentMap<ConsumerGroup, ConsumerContainer> CONSUMER_MAP = new ConcurrentHashMap<>();

    public synchronized static ConsumerContainer getContainer(ConsumeConfig config, ObjectMapper objectMapper) {
        ConsumerGroup consumerGroup = config.toConsumeGroup();
        if (!CONSUMER_MAP.containsKey(consumerGroup)) {
            ConsumerContainer container = new ConsumerContainer(consumerGroup, objectMapper);
            container.init(config);
            CONSUMER_MAP.put(consumerGroup, container);
        }
        return CONSUMER_MAP.get(consumerGroup);
    }

    public static ConcurrentMap<ConsumerGroup, ConsumerContainer> consumerMap() {
        return CONSUMER_MAP;
    }
}
