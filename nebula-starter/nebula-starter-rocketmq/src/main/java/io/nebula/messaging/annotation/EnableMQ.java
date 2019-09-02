package io.nebula.messaging.annotation;

import io.nebula.messaging.configuration.RocketScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
@Import({RocketScannerRegistrar.class})
public @interface EnableMQ {
    /**
     * 定义需要扫描的包
     *
     * @return
     */
    String[] value() default {"io.nebula"};

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
