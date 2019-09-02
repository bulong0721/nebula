package io.nebula.configuration;

import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/3
 */
@Configuration
@EnableCaching
public class SpringCacheConfigurer {


    /**
     * Spring Cache 集成
     * 方法名不能与 Shiro cacheManager名字相同，否则无法正常启动
     *
     * @param redissonClient
     * @return
     */
    @Bean
    public CacheManager springCacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();

        // create "testMap" cache with ttl = 24 minutes and maxIdleTime = 12 minutes
        LocalCachedMapOptions options = LocalCachedMapOptions.defaults()
                .evictionPolicy(LocalCachedMapOptions.EvictionPolicy.LFU)
                .cacheSize(1000);
        config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    /**
     * 流式客户端
     *
     * @param redissonClient
     * @return
     */
    @Bean
    public RedissonReactiveClient reactiveClient(RedissonClient redissonClient) {
        Config config = redissonClient.getConfig();
        return Redisson.createReactive(config);
    }
}
