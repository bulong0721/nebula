package com.rhea.config.api;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import lombok.Data;

import java.util.Set;

@Data
public class ConfigChangeObject {
    private final ConfigChangeEvent changeEvent;

    public Set<String> changedKeys() {
        return changeEvent.changedKeys();
    }

    public ConfigChange getChange(String key) {
        return changeEvent.getChange(key);
    }

    public boolean isChanged(String key) {
        return changeEvent.isChanged(key);
    }

    public String getNamespace() {
        return changeEvent.getNamespace();
    }
}
