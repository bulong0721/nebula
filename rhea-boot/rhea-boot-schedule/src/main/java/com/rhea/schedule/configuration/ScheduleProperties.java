package com.rhea.schedule.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 050618
 */
@Data
@Component
@ConfigurationProperties(prefix = "rhea.schedule")
public class ScheduleProperties {

    private String zkServer;

    private String namespace;
}
