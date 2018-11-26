package com.rhea.mq.api;

import io.openmessaging.KeyValue;

/**
 * @author xubulong8
 */
public final class MsgProperties {
    private final KeyValue keyValue;

    public MsgProperties(KeyValue keyValue) {
        this.keyValue = keyValue;
    }

    public String getTopic() {
        return keyValue.getString("Topic");
    }
}
