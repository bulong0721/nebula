package com.rhea.messaging.configuration;

import io.openmessaging.producer.Producer;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @author 050618
 */
@Data
public class ProducerFactoryBean implements FactoryBean {
    private ProducerConfig producerConfig;
    private Class<?> producerClass;
    private Producer producer;
    private Object instance;

    @Override
    public Object getObject() throws Exception {
        if (null == instance) {
            ProducerHandler producerHandler = new ProducerHandler(producerConfig, producer);
            instance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{producerClass}, producerHandler);
        }
        return instance;
    }

    @Override
    public Class<?> getObjectType() {
        return producerClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
