package com.rhea.schedule.configuration;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.google.common.base.Optional;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 050618
 */
public class ElasticScheduler implements InitializingBean {
    @Setter
    private ScheduleConfig scheduleConfig;
    @Setter
    private ElasticJob elasticJob;
    @Autowired
    private CoordinatorRegistryCenter registryCenter;

    @Override
    public void afterPropertiesSet() throws Exception {
        String jobName = scheduleConfig.getName();
        String corn = scheduleConfig.getCron();
        int shardingCount = scheduleConfig.getShardingCount();
        JobCoreConfiguration jobConfig = JobCoreConfiguration.newBuilder(jobName, corn, shardingCount)
                .failover(scheduleConfig.isFailover())
                .misfire(scheduleConfig.isMisfire())
                .build();
        new SpringJobScheduler(registryCenter, buildJobConfig(jobConfig), elasticJob).init();
    }

    LiteJobConfiguration buildJobConfig(JobCoreConfiguration jobConfig) {
        JobTypeConfiguration typeConfiguration = null;
        String jobClass = elasticJob.getClass().getCanonicalName();
        if (elasticJob instanceof SimpleJob) {
            typeConfiguration = new SimpleJobConfiguration(jobConfig, jobClass);
        } else if (elasticJob instanceof DataflowJob) {
            typeConfiguration = new DataflowJobConfiguration(jobConfig, jobClass, true);
        } else if (elasticJob instanceof ScriptJob) {
            typeConfiguration = new ScriptJobConfiguration(jobConfig, jobClass);
        }
        return LiteJobConfiguration.newBuilder(typeConfiguration).build();
    }


    static class SpringJobScheduler extends JobScheduler {
        private final ElasticJob elasticJob;

        public SpringJobScheduler(CoordinatorRegistryCenter regCenter, LiteJobConfiguration liteJobConfig, ElasticJob elasticJob, ElasticJobListener... elasticJobListeners) {
            super(regCenter, liteJobConfig, elasticJobListeners);
            this.elasticJob = elasticJob;
        }

        @Override
        protected Optional<ElasticJob> createElasticJobInstance() {
            return Optional.of(elasticJob);
        }
    }
}
