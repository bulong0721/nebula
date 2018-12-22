package com.rhea.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author xubulong
 * @version V1.0
 */
@Data
@ConfigurationProperties(prefix = "rhea.web")
public class WebProperties {
    private Session session;
    private Shiro shiro;
    private Cookie cookie;

    @Data
    public static class Session {
        private String namespace;
        private int timeout;
    }

    @Data
    public static class Cookie {
        private int timeout;
    }

    @Data
    public static class Shiro {
        private String loginUrl;
        private String logoutUrl;
        private String successUrl;
        private String publicUrl;
        private Map<String, String> filterMap;
    }
}
