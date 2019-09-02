package io.nebula.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/5
 */
@Data
@ConfigurationProperties(prefix = "nebula.websocket")
public class WebSocketProperties {
    private String hostname;
    private Integer port;
    private Map<String, Namespace> namespaces;
    private Scheduler ping;

}
