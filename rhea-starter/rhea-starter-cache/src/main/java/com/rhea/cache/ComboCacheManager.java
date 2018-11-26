package com.rhea.cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/23
 */
public class ComboCacheManager implements CacheManager, InitializingBean {
    private final List<CacheManager> cacheManagers = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public Cache getCache(String name) {
        return null;
    }

    @Override
    public Collection<String> getCacheNames() {
        return null;
    }
}
