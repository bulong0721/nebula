package com.rhea.common.annotation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@SpringBootApplication(exclude = {/*DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class*/})
public @interface EnableFramework {
}
