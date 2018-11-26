package com.rhea.cache.annotation;

import java.lang.annotation.*;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface CacheExtension {

    /**
     * 缓存数据分区
     *
     * @return
     */
    String namespace();

    /**
     * 数据格式
     *
     * @return
     */
    CacheFormat format() default CacheFormat.JSON;

    /**
     * 缓存存放地方
     *
     * @return
     */
    CacheStore store() default CacheStore.REDIS;

//    /**
//     * 是否压缩
//     *
//     * @return
//     */
//    boolean compress() default false;

//    /**
//     * 缓存数据加载器
//     *
//     * @return
//     */
//    Class<? extends DataLoader> loader();
}
