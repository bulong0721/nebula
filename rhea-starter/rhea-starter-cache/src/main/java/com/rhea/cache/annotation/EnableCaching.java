package com.rhea.cache.annotation;

import com.rhea.cache.extension.ExtensionConfigurationSelector;
import com.rhea.cache.CachingSelector;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ExtensionConfigurationSelector.class, CachingSelector.class})
public @interface EnableCaching {

    String namespace() default "";

    int order() default Ordered.LOWEST_PRECEDENCE;
}
