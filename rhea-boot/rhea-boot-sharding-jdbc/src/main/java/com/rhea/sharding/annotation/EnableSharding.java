package com.rhea.sharding.annotation;

import com.rhea.sharding.configuration.ShardConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ShardConfiguration.class})
public @interface EnableSharding {

}
