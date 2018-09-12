package com.rhea.schedule.configuration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 050618
 */
@Configuration
@EnableConfigurationProperties(ScheduleProperties.class)
public class JobConfiguration {
    @Autowired
    private ScheduleProperties scheduleProperties;

    @Bean
    ZookeeperConfiguration zkConfig() {
        String zkServer = scheduleProperties.getZkServer();
        String namespace = scheduleProperties.getNamespace();
        return new ZookeeperConfiguration(zkServer, namespace);
    }

    @Bean
    CoordinatorRegistryCenter setUpRegistryCenter(ZookeeperConfiguration zkConfig) {
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(zkConfig);
        registryCenter.init();
        return registryCenter;
    }
}
