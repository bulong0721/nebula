package com.rhea.messaging.annotation;

import com.rhea.messaging.configuration.MQScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MQScannerRegistrar.class)
public @interface EnableMQ {
    String[] value() default {"com.rhea"};

    boolean produceOn() default true;

    boolean consumeOn() default false;
}
