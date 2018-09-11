package com.rhea.messaging.configuration;

import com.rhea.messaging.api.MQProducer;
import io.openmessaging.Future;
import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.internal.DefaultKeyValue;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

public class ProducerHandler implements InvocationHandler, MQProducer<Serializable> {
    private final ProducerConfig config;
    private final Producer producer;

    public ProducerHandler(ProducerConfig config, Producer producer) {
        this.config = config;
        this.producer = producer;
    }

    Message buildMessage(Serializable data, Properties properties) {
        return null;
    }

    KeyValue toKeyValue(Properties properties) {
        KeyValue keyValue = new DefaultKeyValue();
        properties.forEach((key, value) -> {
            keyValue.put((String) key, (String) value);
        });
        return keyValue;
    }

    @Override
    public SendResult send(Serializable msg, Properties properties) {
        Message message = buildMessage(msg, properties);
        KeyValue keyValue = toKeyValue(properties);
        return this.producer.send(message, keyValue);
    }

    @Override
    public SendResult send(Serializable msg) {
        Message message = buildMessage(msg, null);
        return this.producer.send(message);
    }

    @Override
    public Future<SendResult> sendAsync(Serializable msg, Properties properties) {
        Message message = buildMessage(msg, properties);
        KeyValue keyValue = toKeyValue(properties);
        return this.producer.sendAsync(message, keyValue);
    }

    @Override
    public Future<SendResult> sendAsync(Serializable msg) {
        Message message = buildMessage(msg, null);
        return this.producer.sendAsync(message);
    }

    @Override
    public void sendOneway(Serializable msg, Properties properties) {
        Message message = buildMessage(msg, properties);
        KeyValue keyValue = toKeyValue(properties);
        this.producer.sendOneway(message, keyValue);
    }

    @Override
    public void sendOneway(Serializable msg) {
        Message message = buildMessage(msg, null);
        this.producer.sendOneway(message);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if ("send".equals(methodName)) {
            return args.length == 1 ? send((Serializable) args[0]) : send((Serializable) args[0], (Properties) args[1]);
        }
        if ("sendAsync".equals(methodName)) {
            return args.length == 1 ? sendAsync((Serializable) args[0]) : sendAsync((Serializable) args[0], (Properties) args[1]);
        }
        if ("sendOneway".equals(methodName)) {
            if (args.length == 1) {
                sendOneway((Serializable) args[0]);
            } else {
                sendOneway((Serializable) args[0], (Properties) args[1]);
            }
        }
        return null;
    }
}
