package io.nebula.leaf.config;

import io.nebula.kernel.exception.NebulaException;
import io.nebula.leaf.feign.LeafService;
import io.nebula.leaf.genid.AbstractGenId;
import io.nebula.leaf.genid.LeafDBGenId;
import io.nebula.leaf.genid.LeafZKGenId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@Configuration
public class LeafConfiguration {
    @Value("${spring.application.name}")
    private String key;

    @Bean
    public LeafDBGenId leafDBGenId(LeafService client) {
        if (StringUtils.isEmpty(client)) {
            throw new NebulaException("LeafDBGenId Service load Fail");
        }
        AbstractGenId.setClient(key, client);
        return new LeafDBGenId();
    }

    @Bean
    public LeafZKGenId leafZKGenId(LeafService client) {
        if (StringUtils.isEmpty(client)) {
            throw new NebulaException("LeafZKGenId Service load Fail");
        }
        AbstractGenId.setClient(key, client);
        return new LeafZKGenId();
    }
}
