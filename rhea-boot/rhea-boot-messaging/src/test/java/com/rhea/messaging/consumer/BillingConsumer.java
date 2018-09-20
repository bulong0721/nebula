package com.rhea.messaging.consumer;

import com.rhea.messaging.annotation.Consumer;
import com.rhea.messaging.api.MQConsumer;
import com.rhea.messaging.api.MsgProperties;
import com.rhea.messaging.model.Order;
import com.rhea.messaging.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

@Consumer(topic = "TopicTest")
public class BillingConsumer extends MQConsumer<Order> {

    @Autowired
    private HelloService helloService;

    @Override
    public void consume(Order msg, MsgProperties keyValue) {

    }
}
