package com.rhea.cache.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rhea.cache.reflect.ParameterizedTypeImpl;
import org.springframework.cache.Cache;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jiayu.qiu
 */
public class FastjsonSerializer implements ISerializer<Object> {

    private final Charset charset;

    private static final SerializerFeature[] FEATURES = {SerializerFeature.DisableCircularReferenceDetect};

    private static final Map<Type, ParameterizedTypeImpl> TYPE_CACHE = new ConcurrentHashMap<>(1024);

    public FastjsonSerializer() {
        this(Charset.forName("UTF8"));
    }

    public FastjsonSerializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(final Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        String json = JSON.toJSONString(obj, FEATURES);
        return json.getBytes(charset);
    }

    @Override
    public Object deserialize(final byte[] bytes, final Type returnType) throws Exception {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        ParameterizedTypeImpl type = TYPE_CACHE.get(returnType);
        if (null == type) {
            Type[] agsType = new Type[]{returnType};
            type = ParameterizedTypeImpl.make(Cache.class, agsType, null);
            TYPE_CACHE.put(returnType, type);
        }

        String json = new String(bytes, charset);
        return JSON.parseObject(json, type);
    }
}
