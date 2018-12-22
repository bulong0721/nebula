package com.rhea.kernel.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.io.ByteStreams;
import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @author xubulong
 * @version V1.0
 */
@Slf4j
public class FeignCodec implements Decoder, Encoder {
    static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private final ObjectMapper objectMapper;

    public FeignCodec(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        String text = new String(ByteStreams.toByteArray(response.body().asInputStream()), DEFAULT_CHARSET);
        if (type == String.class) {
            return text;
        }
        JavaType javaType = TypeFactory.defaultInstance().constructType(type);
        return objectMapper.readValue(text, javaType);
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (bodyType == String.class) {
            template.body(object.toString().getBytes(DEFAULT_CHARSET), null);
        } else if (bodyType == byte[].class) {
            template.body((byte[]) object, null);
        } else if (object != null) {
            try {
                template.body(objectMapper.writeValueAsBytes(object), null);
            } catch (JsonProcessingException e) {
                throw new EncodeException("Parse error", e);
            }
        }
    }
}
