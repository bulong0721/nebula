package com.rhea.cache.serializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rhea.cache.reflect.ParameterizedTypeImpl;
import org.springframework.cache.Cache;

import java.lang.reflect.Type;

/**
 * @author 05061
 * @version V1.0 created at: 2018/11/23
 */
public class JacksonJsonSerializer implements ISerializer<Object> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JacksonJsonSerializer() {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.registerModule(new SimpleModule());
        MAPPER.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    @Override
    public byte[] serialize(final Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        return MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public Object deserialize(final byte[] bytes, final Type returnType) throws Exception {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        Type[] agsType = new Type[]{returnType};
        JavaType javaType = MAPPER.getTypeFactory()
                .constructType(ParameterizedTypeImpl.make(Cache.class, agsType, null));
        return MAPPER.readValue(bytes, javaType);
    }
}
