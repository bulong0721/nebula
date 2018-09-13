package com.rhea.schedule.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.rhea.schedule.annotation.Schedule;
import com.rhea.schedule.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

@Schedule(name = "helloJob", cron = "0/5 * * * * ?")
public class HelloJob implements SimpleJob {
    @Autowired
    private HelloService helloService;

    @Override
    public void execute(ShardingContext shardingContext) {
        helloService.sayHello("simple job");
    }
}
