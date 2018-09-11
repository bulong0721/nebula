package com.rhea.messaging;

import com.rhea.messaging.annotation.EnableMQ;
import com.rhea.messaging.consumer.BillingConsumer;
import com.rhea.messaging.model.Order;
import com.rhea.messaging.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@EnableMQ(consumeOn = true)
@SpringBootConfiguration
public class MessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            private OrderProducer orderProducer;
            @Autowired
            private BillingConsumer billingConsumer;

            @Override
            public void run(ApplicationArguments applicationArguments) throws Exception {
                Order order = new Order();
                orderProducer.sendOneway(order);
            }
        };
    }
}
