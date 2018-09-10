package com.rhea.messaging.consumer;

import io.openmessaging.Message;
import io.openmessaging.consumer.MessageListener;

/**
 * @author 050618
 */
public abstract class Consumer<T> implements MessageListener {
    private final String queue;
    private final String tag;

    protected Consumer(String queue, String tag) {
        this.queue = queue;
        this.tag = tag;
    }

    @Override
    public void onReceived(Message message, Context context) {
        context.ack();
    }

    /**
     * 消息消费
     * @param msg
     */
    public abstract void consume(T msg);
}
