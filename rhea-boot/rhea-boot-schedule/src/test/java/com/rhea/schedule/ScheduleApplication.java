package com.rhea.schedule;

import com.rhea.schedule.annotation.EnableSchedule;
import com.rhea.schedule.job.HelloJob;
import com.rhea.schedule.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
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
            @Autowired
            HelloService helloService;

            @Autowired
            HelloJob helloJob;

            @Override
            public void run(ApplicationArguments applicationArguments) throws Exception {
                System.out.println("Application start");
                helloService.sayHello("ApplicationRunner");
                System.out.println(helloJob.toString());
                Thread.sleep(1000000);
            }
        };
    }
}
