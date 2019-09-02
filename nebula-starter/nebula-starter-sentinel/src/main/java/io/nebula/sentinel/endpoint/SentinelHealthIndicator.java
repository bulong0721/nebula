package io.nebula.sentinel.endpoint;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/26
 */
public class SentinelHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

    }
}
