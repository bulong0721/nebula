package io.nebula.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/18
 */
@Data
@ConfigurationProperties(prefix = "nebula.web")
public class WebProperties {
    private Shiro shiro;
    private Cookie cookie;
    private Session session;

    @Data
    public static class Cookie {
        private int timeout;
    }

    @Data
    public static class Session {
        private String namespace;
        private int timeout;
    }

    @Data
    public static class Shiro {
        private boolean notRedirect = true;
        private String loginUrl;
        private String logoutUrl;
        private String successUrl;
        private String unauthorizedUrl;
        private FilterDef[] filters;
    }

    @Data
    public static class FilterDef {
        private String urls;
        private String expr;
    }
}
