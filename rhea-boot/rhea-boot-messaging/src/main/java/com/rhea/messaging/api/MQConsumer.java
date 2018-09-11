package com.rhea.messaging.api;

import io.openmessaging.Message;
import io.openmessaging.consumer.MessageListener;

import java.io.Serializable;

/**
 * @author 050618
 */
public abstract class MQConsumer<T extends Serializable> implements MessageListener {

    @Override
    public void onReceived(Message message, Context context) {
        context.ack();
    }

    /**
     * 消息消费
     * @param msg
     * @param keyValue
     */
    public abstract void consume(T msg, MsgProperties keyValue);
}
