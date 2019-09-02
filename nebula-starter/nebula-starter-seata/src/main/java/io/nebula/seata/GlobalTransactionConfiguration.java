package io.nebula.seata;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
@Configuration
@EnableConfigurationProperties(SeataProperties.class)
public class GlobalTransactionConfiguration {
    @Autowired
    private DataSourceProperties properties;
    @Autowired
    private SeataProperties seataProperties;
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        String txServiceGroup = seataProperties.getTxServiceGroup();

        if (StringUtils.isEmpty(txServiceGroup)) {
            txServiceGroup = applicationName + "-seata-service-group";
            seataProperties.setTxServiceGroup(txServiceGroup);
        }
        return new GlobalTransactionScanner(applicationName, txServiceGroup);
    }


    @Bean
    @Primary
    @ConditionalOnProperty(name = "nebula.seata.enabled")
    public DataSourceProxy dataSourceProxy() {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
        return new DataSourceProxy(dataSource);
    }
}
