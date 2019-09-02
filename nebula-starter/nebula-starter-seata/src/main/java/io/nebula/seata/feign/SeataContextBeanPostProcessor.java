package io.nebula.seata.feign;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cloud.openfeign.FeignContext;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
public class SeataContextBeanPostProcessor implements BeanPostProcessor {
    private final BeanFactory beanFactory;
    private SeataFeignObjectWrapper seataFeignObjectWrapper;

    SeataContextBeanPostProcessor(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof FeignContext && !(bean instanceof SeataFeignContext)) {
            return new SeataFeignContext(getSeataFeignObjectWrapper(),
                    (FeignContext) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }

    private SeataFeignObjectWrapper getSeataFeignObjectWrapper() {
        if (this.seataFeignObjectWrapper == null) {
            this.seataFeignObjectWrapper = this.beanFactory
                    .getBean(SeataFeignObjectWrapper.class);
        }
        return this.seataFeignObjectWrapper;
    }
}
