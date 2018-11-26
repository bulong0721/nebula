package com.rhea.cache.extension;

import com.rhea.cache.annotation.CacheExtension;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;
import org.springframework.cache.interceptor.CacheOperation;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
public class ExtensionCacheAnnotationParser extends SpringCacheAnnotationParser {
    @Override
    public Collection<CacheOperation> parseCacheAnnotations(Class<?> type) {
        Collection<CacheOperation> cacheOperations = super.parseCacheAnnotations(type);
        if (null != cacheOperations) {
            CacheExtension extension = type.getAnnotation(CacheExtension.class);
        }
        return cacheOperations;
    }

    @Override
    public Collection<CacheOperation> parseCacheAnnotations(Method method) {
        Collection<CacheOperation> cacheOperations = super.parseCacheAnnotations(method);
        if (null != cacheOperations) {
            CacheExtension extension = method.getAnnotation(CacheExtension.class);
        }
        return cacheOperations;
    }


    protected CacheOperation enhance(CacheOperation operation, CacheExtension extension) {
        return null;
    }
}
