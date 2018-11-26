package com.rhea.cache.extension;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ExtensionCachingConfiguration extends ProxyCachingConfiguration {

    @Override
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public CacheOperationSource cacheOperationSource() {
        ExtensionCacheAnnotationParser cacheAnnotationParser = new ExtensionCacheAnnotationParser();
        return new AnnotationCacheOperationSource(cacheAnnotationParser);
    }
}
