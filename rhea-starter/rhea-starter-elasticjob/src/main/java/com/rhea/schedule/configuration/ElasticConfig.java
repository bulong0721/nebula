package com.rhea.schedule.configuration;

import lombok.Data;

/**
 * @author xubulong
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
