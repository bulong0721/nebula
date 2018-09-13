package com.rhea.schedule.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.rhea.schedule.annotation.Schedule;

import java.util.List;

@Schedule(name = "batchJob2", cron = "0/5 * * * * ?")
public class BatchJob implements DataflowJob<String> {

    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        return null;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {

    }
}
