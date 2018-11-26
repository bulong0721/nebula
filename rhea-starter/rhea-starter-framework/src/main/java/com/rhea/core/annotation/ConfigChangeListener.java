package com.rhea.core.annotation;

import com.ctrip.framework.apollo.core.ConfigConsts;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ConfigChangeListener {

    String[] value() default {ConfigConsts.NAMESPACE_APPLICATION};

    String[] interestedKeys() default {};
}
