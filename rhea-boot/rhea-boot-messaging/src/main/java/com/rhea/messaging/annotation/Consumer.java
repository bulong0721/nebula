package com.rhea.messaging.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Consumer {
    String serverUrl() default "";

    String group() default "";

    String topic();

    String tag() default "*";

    int threadMin() default 2;

    int threadMax() default 4;
}
