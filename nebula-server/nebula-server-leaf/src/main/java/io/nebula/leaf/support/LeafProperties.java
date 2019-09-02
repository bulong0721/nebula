package io.nebula.leaf.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/23
 */
@Data
@ConfigurationProperties(prefix = "nebula.leaf")
public class LeafProperties {
    private Snowflake snowflake;

    @Data
    public static class Snowflake {
        private String zkAddress;
        private int port;
    }
}
