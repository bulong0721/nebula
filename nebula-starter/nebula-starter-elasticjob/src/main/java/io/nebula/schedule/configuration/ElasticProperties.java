package io.nebula.schedule.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 徐步龙
 */
@Data
@Component
@ConfigurationProperties(prefix = "nebula.elastic")
public class ElasticProperties {

    private String zkServer;

    private String namespace;

    private boolean tracing = false;
}
