package io.nebula.seata.feign;

import feign.Client;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
public class SeataFeignObjectWrapper {
    private final BeanFactory beanFactory;

    private CachingSpringLoadBalancerFactory cachingSpringLoadBalancerFactory;
    private SpringClientFactory springClientFactory;

    protected SeataFeignObjectWrapper(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object wrap(Object bean) {
        if (bean instanceof Client && !(bean instanceof SeataFeignClient)) {
            if (bean instanceof LoadBalancerFeignClient) {
                LoadBalancerFeignClient client = ((LoadBalancerFeignClient) bean);
                return new SeataLoadBalancerFeignClient(client.getDelegate(), factory(),
                        clientFactory(), this.beanFactory);
            }
            return new SeataFeignClient(this.beanFactory, (Client) bean);
        }
        return bean;
    }

    public CachingSpringLoadBalancerFactory factory() {
        if (this.cachingSpringLoadBalancerFactory == null) {
            this.cachingSpringLoadBalancerFactory = this.beanFactory
                    .getBean(CachingSpringLoadBalancerFactory.class);
        }
        return this.cachingSpringLoadBalancerFactory;
    }

    public SpringClientFactory clientFactory() {
        if (this.springClientFactory == null) {
            this.springClientFactory = this.beanFactory
                    .getBean(SpringClientFactory.class);
        }
        return this.springClientFactory;
    }
}
