package com.rhea.schedule.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Schedule {
    String name();

    String description() default "";

    String cron();

    int shardingCount() default 1;

    boolean failover() default true;

    boolean misfire() default false;
}
