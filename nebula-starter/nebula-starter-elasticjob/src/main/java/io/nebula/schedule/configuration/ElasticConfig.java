package io.nebula.schedule.configuration;

import lombok.Data;

/**
 * @author 徐步龙
 */
@Data
public class ElasticConfig {
    private String name;
    private String description;
    private String cron = "0/10 * * * *";
    private int shardingCount = 1;
    private boolean failover = true;
    private boolean misfire = false;
}
