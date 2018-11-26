package com.rhea.schedule.configuration;

import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xubulong
 */
@Configuration
@EnableConfigurationProperties(ElasticProperties.class)
public class JobConfiguration {
    @Autowired
    private ElasticProperties elasticProperties;

    @Bean
    ZookeeperConfiguration zkConfig() {
        String zkServer = elasticProperties.getZkServer();
        String namespace = elasticProperties.getNamespace();
        return new ZookeeperConfiguration(zkServer, namespace);
    }

    @Bean
    CoordinatorRegistryCenter setUpRegistryCenter(ZookeeperConfiguration zkConfig) {
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(zkConfig);
        registryCenter.init();
        return registryCenter;
    }
}
