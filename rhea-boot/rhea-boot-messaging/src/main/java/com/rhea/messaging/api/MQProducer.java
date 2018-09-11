package com.rhea.messaging.api;

import io.openmessaging.Future;
import io.openmessaging.producer.SendResult;

import java.io.Serializable;
import java.util.Properties;

/**
 * @author 050618
 */
public interface MQProducer<T extends Serializable> {

    /**
     * 同步发送
     *
     * @param msg
     * @param properties
     * @return
     */
    SendResult send(T msg, Properties properties);

    /**
     * 同步发送
     *
     * @param msg
     * @return
     */
    SendResult send(T msg);

    /**
     * 异步发送
     *
     * @param msg
     * @param properties
     * @return
     */
    Future<SendResult> sendAsync(T msg, Properties properties);

    /**
     * 异步发送
     *
     * @param msg
     * @return
     */
    Future<SendResult> sendAsync(T msg);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     * @param properties
     */
    void sendOneway(T msg, Properties properties);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     */
    void sendOneway(T msg);
}
