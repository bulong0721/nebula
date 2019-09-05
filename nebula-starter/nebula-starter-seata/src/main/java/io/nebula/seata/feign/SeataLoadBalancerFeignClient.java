package io.nebula.seata.feign;

import feign.Client;
import feign.Request;
import feign.Response;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
public class SeataLoadBalancerFeignClient extends LoadBalancerFeignClient {
    private static final int MAP_SIZE = 16;
    private final BeanFactory beanFactory;

    SeataLoadBalancerFeignClient(Client delegate,
                                 CachingSpringLoadBalancerFactory lbClientFactory,
                                 SpringClientFactory clientFactory, BeanFactory beanFactory) {
        super(wrap(delegate, beanFactory), lbClientFactory, clientFactory);
        this.beanFactory = beanFactory;
    }

    private static Client wrap(Client delegate, BeanFactory beanFactory) {
        return (Client) new SeataFeignObjectWrapper(beanFactory).wrap(delegate);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Request modifiedRequest = getModifyRequest(request);
        return super.execute(modifiedRequest, options);
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
