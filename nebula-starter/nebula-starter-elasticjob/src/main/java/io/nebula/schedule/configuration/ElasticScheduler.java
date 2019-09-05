package io.nebula.schedule.configuration;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.google.common.base.Optional;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 徐步龙
 */
public class ElasticScheduler implements InitializingBean {
    @Setter
    private ElasticConfig elasticConfig;
    @Setter
    private ElasticJob elasticJob;
    @Autowired
    private CoordinatorRegistryCenter registryCenter;
    @Autowired(required = false)
    private JobEventConfiguration jobEventConfiguration;

    @Override
    public void afterPropertiesSet() throws Exception {
        String jobName = elasticConfig.getName();
        String corn = elasticConfig.getCron();
        int shardingCount = elasticConfig.getShardingCount();
        JobCoreConfiguration jobConfig = JobCoreConfiguration.newBuilder(jobName, corn, shardingCount)
                .failover(elasticConfig.isFailover())
                .misfire(elasticConfig.isMisfire())
                .build();
        if (null != jobEventConfiguration) {
            new SpringJobScheduler(registryCenter, buildJobConfig(jobConfig), jobEventConfiguration, elasticJob).init();
        } else {
            new SpringJobScheduler(registryCenter, buildJobConfig(jobConfig), elasticJob).init();
        }

    }

    protected LiteJobConfiguration buildJobConfig(JobCoreConfiguration jobConfig) {
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


    public static class SpringJobScheduler extends JobScheduler {
        private final ElasticJob elasticJob;

        public SpringJobScheduler(CoordinatorRegistryCenter regCenter, LiteJobConfiguration liteJobConfig, ElasticJob elasticJob, ElasticJobListener... elasticJobListeners) {
            super(regCenter, liteJobConfig, elasticJobListeners);
            this.elasticJob = elasticJob;
        }

        public SpringJobScheduler(CoordinatorRegistryCenter regCenter, LiteJobConfiguration liteJobConfig, JobEventConfiguration jobEventConfiguration, ElasticJob elasticJob, ElasticJobListener... elasticJobListeners) {
            super(regCenter, liteJobConfig, jobEventConfiguration, elasticJobListeners);
            this.elasticJob = elasticJob;
        }

        @Override
        protected Optional<ElasticJob> createElasticJobInstance() {
            return Optional.of(elasticJob);
        }
    }
}
