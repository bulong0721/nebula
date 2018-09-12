package com.rhea.config.annotation;

import com.rhea.config.configuration.ConfigRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ConfigRegistrar.class)
public @interface EnableConfig {

    int order() default Ordered.LOWEST_PRECEDENCE;
}
