package com.rhea.schedule.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.rhea.schedule.annotation.Schedule;

@Schedule(cron = "0/10 * * * *")
public class HelloJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("hello world");
    }
}
