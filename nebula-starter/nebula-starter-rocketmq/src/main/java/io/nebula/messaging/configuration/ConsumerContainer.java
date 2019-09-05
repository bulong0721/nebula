package io.nebula.messaging.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.nebula.messaging.api.Headers;
import io.nebula.messaging.api.MessageListener;
import io.nebula.messaging.exception.ConsumeException;
import io.nebula.messaging.properties.ConsumeConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.context.SmartLifecycle;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
@Slf4j
public class ConsumerContainer implements SmartLifecycle {
    @Getter
    private final ObjectMapper objectMapper;
    @Getter
    private final ConsumerGroup consumerGroup;
    @Getter
    private DefaultMQPushConsumer consumer;
    private Map<String, io.nebula.messaging.api.MessageListener> listenerMap = new ConcurrentHashMap<>();
    private boolean running;

    public ConsumerContainer(ConsumerGroup consumerGroup, ObjectMapper objectMapper) {
        this.consumerGroup = consumerGroup;
        this.objectMapper = objectMapper;
    }

    public void registerMessageListener(ConsumeConfig consumeConfig, io.nebula.messaging.api.MessageListener listener) throws MQClientException {
        checkConsumeConfig(consumeConfig);
        consumer.subscribe(consumeConfig.getTopic(), consumeConfig.getTag());
        listenerMap.put(consumeConfig.toDestination(), listener);
    }

    private void checkConsumeConfig(ConsumeConfig consumeConfig) {

    }

    public synchronized void init(ConsumeConfig config) {
        consumer = new DefaultMQPushConsumer(consumerGroup.getGroupName());
        consumer.setNamesrvAddr(consumerGroup.getNameServer());
        consumer.setConsumeThreadMax(config.getThreadMax());
        if (config.getThreadMax() < consumer.getConsumeThreadMin()) {
            consumer.setConsumeThreadMin(config.getThreadMax());
        }
        switch (consumerGroup.getConsumeMode()) {
            case ORDERLY:
                consumer.setMessageListener(new ListenerOrderly());
                break;
            case CONCURRENTLY:
                consumer.setMessageListener(new ListenerConcurrently());
                break;
            default:
                throw new IllegalArgumentException("Property 'consumeMode' was wrong.");
        }
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public void start() {
        if (this.isRunning()) {
            return;
        }

        try {
            consumer.start();
        } catch (MQClientException e) {
            throw new IllegalStateException("Failed to start RocketMQ push consumer", e);
        }
        this.setRunning(true);
        log.info("running container: {}", this.toString());
    }

    @Override
    public void stop() {
        if (this.isRunning()) {
            if (Objects.nonNull(consumer)) {
                consumer.shutdown();
            }
            setRunning(false);
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public int getPhase() {
        // Returning Integer.MAX_VALUE only suggests that
        // we will be the first bean to shutdown and last bean to start
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    protected Object doConvertMessage(MessageExt messageExt, Type messageType) {
        if (Objects.equals(messageType, MessageExt.class)) {
            return messageExt;
        } else {
            String str = new String(messageExt.getBody(), Charset.forName("UTF-8"));
            if (Objects.equals(messageType, String.class)) {
                return str;
            } else {
                // If msgType not string, use objectMapper change it.
                try {
                    JavaType javaType = TypeFactory.defaultInstance().constructType(messageType);
                    return objectMapper.readValue(str, javaType);
                } catch (Exception e) {
                    log.error("convert failed. str:{}, msgType:{}", str, messageType);
                    throw new ConsumeException("cannot convert message to " + messageType, e);
                }
            }
        }
    }

    protected void consume(MessageExt msg) throws Exception {
        String destination = toDestination(msg);
        MessageListener listener = listenerMap.get(destination);
        if (null == listener) {
            log.error("can't find match listener by: {}", destination);
            throw new ConsumeException("consume message failed.");
        }
        Object message = doConvertMessage(msg, listener.getMessageType());
        Map<String, String> properties = msg.getProperties();
        listener.onMessage(new Headers(properties), message);
    }

    protected String toDestination(MessageExt msg) {
        return msg.getTopic();
    }

    class ListenerConcurrently implements MessageListenerConcurrently {

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            for (MessageExt messageExt : msgs) {
                log.debug("received msg: {}", messageExt);
                try {
                    long now = System.currentTimeMillis();
                    consume(messageExt);
                    long costTime = System.currentTimeMillis() - now;
                    log.debug("consume {} cost: {} ms", messageExt.getMsgId(), costTime);
                } catch (Exception e) {
                    log.warn("consume message failed. messageExt", e);
                    context.setDelayLevelWhenNextConsume(0);
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

    class ListenerOrderly implements MessageListenerOrderly {

        @Override
        public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
            for (MessageExt messageExt : msgs) {
                log.debug("received msg: {}", messageExt);
                try {
                    long now = System.currentTimeMillis();
                    consume(messageExt);
                    long costTime = System.currentTimeMillis() - now;
                    log.info("consume {} cost: {} ms", messageExt.getMsgId(), costTime);
                } catch (Exception e) {
                    log.warn("consume message failed. messageExt:{}", messageExt, e);
                    context.setSuspendCurrentQueueTimeMillis(1000L);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
            }

            return ConsumeOrderlyStatus.SUCCESS;
        }
    }
}
