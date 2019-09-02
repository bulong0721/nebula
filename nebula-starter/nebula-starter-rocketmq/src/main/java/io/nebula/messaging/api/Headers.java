package io.nebula.messaging.api;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/7/4
 */
public class Headers {
    private final Map<String, String> properties;

    public Headers(Map<String, String> properties) {
        this.properties = properties;
    }

    private static String usersKey(String key) {
        return "USERS_" + key;
    }

    public static Headers create(String key, String value) {
        Map<String, String> properties = new HashMap<>();
        properties.put(key, value);
        return new Headers(properties);
    }

    public String get(String key) {
        if (properties.containsKey(key)) {
            return properties.get(key);
        }
        return properties.get(usersKey(key));
    }

    public String put(String key, String value) {
        return properties.put(key, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
