package io.nebula.messaging.support;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/18
 */
public interface SendCallback {
    /**
     * 发送成功
     *
     * @param result
     */
    void onSuccess(SendResult result);

    /**
     * 发送失败
     *
     * @param ex
     */
    void onException(Throwable ex);


    /**
     * 转换成 RocketMQ 的 Callback
     *
     * @return
     */
    default org.apache.rocketmq.client.producer.SendCallback toRocket() {
        return new CallbackWrapper(this);
    }
}
