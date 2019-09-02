package io.nebula.messaging.support;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/18
 */
public class CallbackWrapper implements SendCallback {
    private final io.nebula.messaging.support.SendCallback callback;

    public CallbackWrapper(io.nebula.messaging.support.SendCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        callback.onSuccess(sendResult);
    }

    @Override
    public void onException(Throwable e) {
        callback.onException(e);
    }
}
