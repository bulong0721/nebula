package com.rhea.messaging.annotation;

import java.io.Serializable;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Producer {
    /**
     * 消息队列的服务器地址，例如：oms:rocketmq://10.200.112.249:9876/namespace
     * 如果没有设置，则使用全局设置(rhea.mq.serverUrl)
     * @return
     */
    String serverUrl() default "";

    /**
     * 生产者组名
     * 如果没有设置，则使用全局设置
     * @return
     */
    String group() default "";

    /**
     * 队列名
     * @return
     */
    String topic();

    /**
     * 消息类型
     * @return
     */
    Class<?> msgClass() default Serializable.class;
}
