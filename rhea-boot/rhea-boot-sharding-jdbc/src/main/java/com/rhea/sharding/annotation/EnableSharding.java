package com.rhea.sharding.annotation;

import com.rhea.sharding.configuration.ShardConfiguration;
import io.shardingsphere.jdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@EnableAutoConfiguration(exclude = {SpringBootConfiguration.class})
@Import({ShardConfiguration.class})
public @interface EnableSharding {

}
