package com.rhea.kernel.codec;

import feign.FeignException;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author 05061
 * @version V1.0 created at: 2018/10/17
 */
public class FeignCodec implements Decoder, Encoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        return null;
    }

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {

    }
}
