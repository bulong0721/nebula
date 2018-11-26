package com.rhea.core.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.rhea.core.configuration.DynamicConfigRegistrar;
import com.rhea.core.configuration.PropertiesConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@SpringBootApplication(exclude = {/*DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class*/})
@Import({PropertiesConfiguration.class, DynamicConfigRegistrar.class})
public @interface EnableFramework {
    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    int order() default Ordered.LOWEST_PRECEDENCE;
}