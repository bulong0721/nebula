package com.rhea.messaging.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Consumer {
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
     * 队列Tag
     * @return
     */
    String tag() default "*";

    /**
     * 消费者线程池最小线程数
     * @return
     */
    int threadMin() default 2;

    /**
     * 消费者线程池最打线程数
     * @return
     */
    int threadMax() default 4;
}
