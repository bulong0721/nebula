package com.rhea.web.support;

import com.google.common.io.ByteStreams;
import com.rhea.annotation.PageBody;
import com.rhea.kernel.exchange.PageRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author xubulong
 * @version V1.0
 */
public class PageRequestMethodProcessor extends AbstractMessageConverterMethodArgumentResolver {

    public PageRequestMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        InputMessageWrapper inputMessage = new InputMessageWrapper(createInputMessage(webRequest));
        PageRequest<Object> page = (PageRequest<Object>) readWithMessageConverters(inputMessage, parameter, PageRequest.class);
        Type innerType = ((ParameterizedType) parameter.getGenericParameterType()).getActualTypeArguments()[0];
        inputMessage.reset();
        Object data = readWithMessageConverters(inputMessage, parameter, innerType);
        page.setData(data);
        return page;
    }

    class InputMessageWrapper implements HttpInputMessage {
        private final byte[] content;
        private final HttpInputMessage inputMessage;
        private InputStream inputStream;

        public InputMessageWrapper(HttpInputMessage inputMessage) throws IOException {
            this.inputMessage = inputMessage;
            content = ByteStreams.toByteArray(inputMessage.getBody());
            inputStream = new ByteArrayInputStream(content);
        }

        @Override
        public InputStream getBody() throws IOException {
            return inputStream;
        }

        public void reset() throws IOException {
            inputStream = new ByteArrayInputStream(content);
        }

        @Override
        public HttpHeaders getHeaders() {
            return inputMessage.getHeaders();
        }
    }
}
