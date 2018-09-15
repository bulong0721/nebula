package com.rhea.common.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.rhea.common.configuration.DynamicConfigRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DynamicConfigRegistrar.class)
public @interface EnableConfig {

    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    int order() default Ordered.LOWEST_PRECEDENCE;
}
