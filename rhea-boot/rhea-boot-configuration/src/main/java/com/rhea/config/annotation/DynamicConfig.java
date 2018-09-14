package com.rhea.config.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface DynamicConfig {
    String value() default ConfigConsts.NAMESPACE_APPLICATION;
}
