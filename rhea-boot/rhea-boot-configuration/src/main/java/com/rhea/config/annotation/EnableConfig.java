package com.rhea.config.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.rhea.config.configuration.ConfigCenterRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ConfigCenterRegistrar.class)
public @interface EnableConfig {

    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    int order() default Ordered.LOWEST_PRECEDENCE;
}
