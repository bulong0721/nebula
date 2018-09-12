package com.rhea.messaging.annotation;

import java.io.Serializable;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Producer {
    String serverUrl() default "";

    String group() default "";

    String topic();

    Class<?> msgClass() default Serializable.class;
}
