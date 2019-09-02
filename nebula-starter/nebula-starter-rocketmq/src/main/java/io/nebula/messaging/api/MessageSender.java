package io.nebula.messaging.api;

import io.nebula.messaging.support.EDSWrapper;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.messaging.Message;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/10
 */
public interface MessageSender<T> {

    /**
     * 同步发送
     *
     * @param msg
     * @return
     */
    SendResult send(T msg);

    /**
     * 同步发送
     *
     * @param headers
     * @param msg
     * @return
     */
    SendResult send(Headers headers, T msg);

    /**
     * 同步发送
     *
     * @param msg
     * @return
     */
    SendResult send(Message<T> msg);

    /**
     * 同步发送
     *
     * @param msg
     * @param hashKey
     * @return
     */
    SendResult sendOrderly(T msg, String hashKey);

    /**
     * 同步发送
     *
     * @param msg
     * @param hashKey
     * @return
     */
    SendResult sendOrderly(Message<T> msg, String hashKey);

    /**
     * 同步发送
     *
     * @param headers
     * @param msg
     * @param callback
     */
    void sendAsync(Headers headers, T msg, SendCallback callback);

    /**
     * 异步发送
     *
     * @param msg
     * @param callback
     */
    void sendAsync(T msg, SendCallback callback);

    /**
     * 异步发送
     *
     * @param msg
     * @param callback
     */
    void sendAsync(Message<T> msg, SendCallback callback);

    /**
     * 异步顺序发送
     *
     * @param headers
     * @param msg
     * @param callback
     * @param hashKey
     */
    void sendAsyncOrderly(Headers headers, T msg, SendCallback callback, String hashKey);

    /**
     * 异步发送
     *
     * @param msg
     * @param callback
     * @param hashKey
     */
    void sendAsyncOrderly(T msg, SendCallback callback, String hashKey);

    /**
     * 异步发送
     *
     * @param msg
     * @param callback
     * @param hashKey
     */
    void sendAsyncOrderly(Message<T> msg, SendCallback callback, String hashKey);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     */
    void sendOneWay(T msg);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     */
    void sendOneWay(Message<T> msg);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     * @param hashKey
     */
    void sendOneWayOrderly(T msg, String hashKey);

    /**
     * 异步发送不获取结果
     *
     * @param msg
     * @param hashKey
     */
    void sendOneWayOrderly(Message<T> msg, String hashKey);

    /**
     * 包装成 EDS 的消息格式
     *
     * @param payload
     * @param <E>
     * @return
     */
    default <E> EDSWrapper<E> wrapper(E payload) {
        EDSWrapper<E> wrapper = new EDSWrapper<>();
        wrapper.setPayload(payload);
        return wrapper;
    }
}
