package com.rhea.schedule;

import com.rhea.schedule.annotation.EnableSchedule;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@EnableSchedule
@SpringBootConfiguration
public class ScheduleApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ScheduleApplication.class);
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments applicationArguments) throws Exception {
                System.out.println("Application start");
                Thread.sleep(1000000);
            }
        };
    }
}
