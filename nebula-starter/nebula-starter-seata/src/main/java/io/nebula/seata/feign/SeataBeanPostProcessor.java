package io.nebula.seata.feign;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
final class SeataBeanPostProcessor implements BeanPostProcessor {
    private final SeataFeignObjectWrapper seataFeignObjectWrapper;

    SeataBeanPostProcessor(SeataFeignObjectWrapper seataFeignObjectWrapper) {
        this.seataFeignObjectWrapper = seataFeignObjectWrapper;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        return this.seataFeignObjectWrapper.wrap(bean);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }
}
