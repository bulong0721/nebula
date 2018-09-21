package com.rhea.messaging.api;

import io.openmessaging.KeyValue;
import io.openmessaging.internal.DefaultKeyValue;

/**
 * @author 050618
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
