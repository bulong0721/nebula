package com.rhea.messaging.spring;

import com.rhea.messaging.consumer.Consumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Map;

/**
 * @author 050618
 */
public class ConsumerScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Consumer> consumerMap = beanFactory.getBeansOfType(Consumer.class);
        for (Map.Entry<String, Consumer> entry : consumerMap.entrySet()) {

        }
    }
}
