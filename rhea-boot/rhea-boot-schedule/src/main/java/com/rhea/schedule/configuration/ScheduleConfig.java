package com.rhea.schedule.configuration;

import lombok.Data;

/**
 * @author 050618
 */
@Data
public class ScheduleConfig {
    private String name;
    private String description;
    private String cron = "0/10 * * * *";
    private int shardingCount = 1;
    private boolean failover = true;
    private boolean misfire = false;
}
