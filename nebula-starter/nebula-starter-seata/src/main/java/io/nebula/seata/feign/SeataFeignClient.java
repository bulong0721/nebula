package io.nebula.seata.feign;

import feign.Client;
import feign.Request;
import feign.Response;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
public class SeataFeignClient implements Client {
    private static final int MAP_SIZE = 16;
    private final Client delegate;
    private final BeanFactory beanFactory;

    SeataFeignClient(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.delegate = new Client.Default(null, null);
    }

    SeataFeignClient(BeanFactory beanFactory, Client delegate) {
        this.delegate = delegate;
        this.beanFactory = beanFactory;
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Request modifiedRequest = getModifyRequest(request);
        return this.delegate.execute(modifiedRequest, options);
    }

    private Request getModifyRequest(Request request) {

        String xid = RootContext.getXID();

        if (StringUtils.isEmpty(xid)) {
            return request;
        }

        Map<String, Collection<String>> headers = new HashMap<>(MAP_SIZE);
        headers.putAll(request.headers());

        List<String> seataXid = new ArrayList<>();
        seataXid.add(xid);
        headers.put(RootContext.KEY_XID, seataXid);

        return Request.create(request.httpMethod(), request.url(), headers, request.requestBody());
    }
}
