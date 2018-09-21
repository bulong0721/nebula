package com.rhea.messaging;

import com.rhea.messaging.annotation.EnableMQ;
import com.rhea.messaging.model.Order;
import com.rhea.messaging.producer.OrderProducer;
import io.openmessaging.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableMQ(consumeOn = true)
@SpringBootApplication
public class MessagingApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(MessagingApplication.class);
        Thread.sleep(5000000L);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            private OrderProducer orderProducer;

            @Override
            public void run(ApplicationArguments applicationArguments) throws Exception {
                Order order = new Order();
                SendResult result = orderProducer.send(order);
                System.out.println(result);
            }
        };
    }
}
