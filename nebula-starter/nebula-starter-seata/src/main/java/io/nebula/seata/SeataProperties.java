package io.nebula.seata;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
@Data
@ConfigurationProperties("nebula.seata")
public class SeataProperties {
    private String txServiceGroup;
    private boolean enabled;
}
