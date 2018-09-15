package com.rhea.common;

import com.rhea.common.annotation.EnableConfig;
import com.rhea.common.properties.SampleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@EnableConfig
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        SpringApplication.run(ConfigApplication.class);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Autowired
            SampleProperties properties;

            @Override
            public void run(ApplicationArguments applicationArguments) throws Exception {
                System.out.println(properties.getCommandTimeout());
            }
        };
    }
}
