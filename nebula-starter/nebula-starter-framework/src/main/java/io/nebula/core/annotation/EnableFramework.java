package io.nebula.core.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;
import io.nebula.core.configuration.DynamicConfigRegistrar;
import io.nebula.core.configuration.PropertiesConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@SpringBootApplication
@Import({PropertiesConfiguration.class, DynamicConfigRegistrar.class})
@ComponentScan(value = "io.nebula")
public @interface EnableFramework {
    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    int order() default Ordered.LOWEST_PRECEDENCE;

    boolean configOn() default true;
}
