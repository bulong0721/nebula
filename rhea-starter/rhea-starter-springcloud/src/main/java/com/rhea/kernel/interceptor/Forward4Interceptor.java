package com.rhea.kernel.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author 05061
 * @version V1.0 created at: 2018/10/17
 */
public class Forward4Interceptor implements RequestInterceptor {
    private final String clientName;

    public Forward4Interceptor(String clientName) {
        this.clientName = /*Strings.isNullOrEmpty(clientName) ?*/ "*.rhea.com" /*: clientName*/;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("X-CallBack", clientName);
    }
}
