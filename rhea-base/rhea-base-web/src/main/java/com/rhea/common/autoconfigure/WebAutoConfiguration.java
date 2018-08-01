package com.rhea.common.autoconfigure;

import com.rhea.common.shiro.SessionDao;
import org.apache.shiro.session.mgt.SessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnBean({SessionManager.class})
    @ConditionalOnProperty("shiro.session.prefix")
    SessionDao sessionRedisDao(WebProperties webProperties, RedisTemplate redisTemplate) {
        return null;
    }
}
