package com.rhea.order;

import com.rhea.order.mapper.OrderMapper;
import com.rhea.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.concurrent.CountDownLatch;

@MapperScan("com.rhea.order.mapper")
@SpringBootApplication
public class SkeletonApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SkeletonApplication.class);
    }

//    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Autowired
            private OrderMapper orderMapper;

            @Override
            public void run(String... strings) throws Exception {
                Order order = new Order();
                for (int i = 0; i < 10; i++) {
                    order.setOrderId((long) i);
                    order.setOrderName("order_" + i);
                    orderMapper.insertSelective(order);
                    System.out.println("数据插入成功:" + i);
                }
            }
        };
    }
}
