package io.nebula.core.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;

import java.lang.annotation.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ConfigChangeListener {

    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    String[] interestedKeys() default {};
}
