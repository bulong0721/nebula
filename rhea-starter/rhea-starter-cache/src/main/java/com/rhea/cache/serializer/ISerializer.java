package com.rhea.cache.serializer;

import java.lang.reflect.Type;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/23
 */
public interface ISerializer<T> {
    /**
     * 序列化
     *
     * @param obj
     * @return
     * @throws Exception
     */
    byte[] serialize(final T obj) throws Exception;

    /**
     * 反序列化
     *
     * @param bytes
     * @param returnType
     * @return
     * @throws Exception
     */
    T deserialize(final byte[] bytes, final Type returnType) throws Exception;
}
