package io.nebula.messaging.configuration;

import io.nebula.messaging.api.Headers;
import io.nebula.messaging.api.MessageSender;
import io.nebula.messaging.properties.ProduceConfig;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/11
 */
@Slf4j
public abstract class AbstractMessageSender<T> implements MessageSender<T> {
    @Setter
    private ProducerTemplate template;
    @Setter
    private ProduceConfig config;

    @Override
    public SendResult send(T msg) {
        return template.syncSend(config.toDestination(), msg);
    }

    @Override
    public SendResult send(Headers headers, T msg) {
        Message<T> message = new GenericMessage(msg, headers.getProperties());
        return template.syncSend(config.toDestination(), message);
    }

    @Override
    public SendResult send(Message<T> msg) {
        return template.syncSend(config.toDestination(), msg);
    }

    @Override
    public SendResult sendOrderly(T msg, String hashKey) {
        return template.syncSendOrderly(config.toDestination(), msg, hashKey);
    }

    @Override
    public SendResult sendOrderly(Message<T> msg, String hashKey) {
        return template.syncSendOrderly(config.toDestination(), msg, hashKey);
    }

    @Override
    public void sendAsync(Headers headers, T msg, SendCallback callback) {
        Message<T> message = new GenericMessage(msg, headers.getProperties());
        template.asyncSend(config.toDestination(), message, callback);
    }

    @Override
    public void sendAsync(T msg, SendCallback callback) {
        template.asyncSend(config.toDestination(), msg, callback);
    }

    @Override
    public void sendAsync(Message<T> msg, SendCallback callback) {
        template.asyncSend(config.toDestination(), msg, callback);
    }

    @Override
    public void sendAsyncOrderly(Headers headers, T msg, SendCallback callback, String hashKey) {
        Message<T> message = new GenericMessage(msg, headers.getProperties());
        template.sendOneWayOrderly(config.toDestination(), message, hashKey);
    }

    @Override
    public void sendAsyncOrderly(T msg, SendCallback callback, String hashKey) {
        template.sendOneWayOrderly(config.toDestination(), msg, hashKey);
    }

    @Override
    public void sendAsyncOrderly(Message<T> msg, SendCallback callback, String hashKey) {
        template.sendOneWayOrderly(config.toDestination(), msg, hashKey);
    }

    @Override
    public void sendOneWay(T msg) {
        template.sendOneWay(config.toDestination(), msg);
    }

    @Override
    public void sendOneWay(Message<T> msg) {
        template.sendOneWay(config.toDestination(), msg);
    }

    @Override
    public void sendOneWayOrderly(T msg, String hashKey) {
        template.sendOneWayOrderly(config.toDestination(), msg, hashKey);
    }

    @Override
    public void sendOneWayOrderly(Message<T> msg, String hashKey) {
        template.sendOneWayOrderly(config.toDestination(), msg, hashKey);
    }
}
