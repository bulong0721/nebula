package com.rhea.mq.api;

import com.alibaba.fastjson.JSON;
import com.rhea.mq.configuration.ConsumerConfig;
import com.rhea.mq.configuration.MQProperties;
import com.rhea.mq.configuration.TopicConfig;
import io.openmessaging.*;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PushConsumer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 队列消费抽象类，无法通过new创建进行消费，需要通过Spring初始化才能正常消费
 *
 * @author xubulong8
 */
@Slf4j
public abstract class MQConsumer<T extends Serializable> implements MessageListener, InitializingBean, DisposableBean {
    @Autowired
    private MQProperties mqProperties;
    @Setter
    private ConsumerConfig consumerConfig;
    private Class<?> msgClass;
    private PushConsumer consumer;

    private static Class<?> getParameterizedType(Class<?> clazz) {
        int retriesCount = 0;
        while (true) {
            if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                Type[] argumentTypes = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments();
                for (Type argumentType : argumentTypes) {
                    Class<?> argumentClass;

                    if (argumentType instanceof ParameterizedType) {
                        argumentClass = (Class<?>) ((ParameterizedType) argumentType).getRawType();
                    } else {
                        argumentClass = (Class<?>) argumentType;
                    }

                    if (Serializable.class.isAssignableFrom(argumentClass)) {
                        return argumentClass;
                    }
                }
            }
            clazz = clazz.getSuperclass();
            retriesCount++;

            if (retriesCount > 5) {
                throw new IllegalArgumentException("Unable to find a ParameterizedType.");
            }
        }
    }

    @Override
    public void onReceived(Message message, Context context) {
        try {
            byte[] body = message.getBody(byte[].class);
            T tMsg = JSON.parseObject(body, msgClass);
            consume(tMsg, new MsgProperties(context.attributes()));
            context.ack();
        } catch (Exception e) {
            log.error("消息消费异常", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        msgClass = getParameterizedType(getClass());
        mqProperties.stuffConfig(consumerConfig);
        KeyValue keyValue = OMS.newKeyValue();
        keyValue.put(OMSBuiltinKeys.CONSUMER_ID, consumerConfig.getGroup());
        MessagingAccessPoint accessPoint = buildAccessPoint(consumerConfig);
        consumer = accessPoint.createPushConsumer(keyValue);
        consumer.attachQueue(consumerConfig.getTopic(), this);
        consumer.startup();
    }

    @Override
    public void destroy() throws Exception {
        if (null != consumer) {
            consumer.shutdown();
        }
    }

    private MessagingAccessPoint buildAccessPoint(TopicConfig topicConfig) {
        return OMS.getMessagingAccessPoint(topicConfig.getServerUrl());
    }

    /**
     * 消息消费方法，如果抛出异常这条消息不会被确认消费后续会重发
     *
     * @param msg
     * @param keyValue
     * @throws Exception
     */
    public abstract void consume(T msg, MsgProperties keyValue) throws Exception;
}
