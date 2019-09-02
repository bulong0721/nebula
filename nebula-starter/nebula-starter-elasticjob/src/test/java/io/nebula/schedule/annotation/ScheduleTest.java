package io.nebula.schedule.annotation;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Schedule(cron = "*/1 * * * * ?", name = "ScheduleTest")
public class ScheduleTest implements SimpleJob {

    public static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("测试执行JOB[{}]，当前分片[{}]，运行计数[{}]", shardingContext.getJobName(), shardingContext.getShardingItem(), counter.getAndIncrement());
    }
}
