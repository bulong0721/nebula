package com.rhea.mq.annotation;

import com.rhea.mq.configuration.MQConfiguration;
import com.rhea.mq.configuration.MQScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({MQConfiguration.class, MQScannerRegistrar.class})
public @interface EnableMQ {
    /**
     * 定义需要扫描的包
     *
     * @return
     */
    String[] value() default {"com.rhea"};

    /**
     * 是否打开队列Producer扫描
     *
     * @return
     */
    boolean produceOn() default true;

    /**
     * 是否打开队列Consumer扫描
     *
     * @return
     */
    boolean consumeOn() default false;
}
