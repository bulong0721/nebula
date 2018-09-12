package com.rhea.messaging.api;

import com.rhea.messaging.configuration.ConsumerConfig;
import io.openmessaging.*;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PushConsumer;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;

/**
 * @author 050618
 */
public abstract class MQConsumer<T extends Serializable> implements MessageListener, InitializingBean {
    @Setter
    private MessagingAccessPoint accessPoint;
    @Setter
    private ConsumerConfig consumerConfig;

    @Override
    public void onReceived(Message message, Context context) {
        context.ack();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KeyValue keyValue = OMS.newKeyValue();
        keyValue.put(OMSBuiltinKeys.CONSUMER_ID, "TestGroup");
        PushConsumer pushConsumer = accessPoint.createPushConsumer(keyValue);
    }

    /**
     * 消息消费
     * @param msg
     * @param keyValue
     */
    public abstract void consume(T msg, MsgProperties keyValue);
}
