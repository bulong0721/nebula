package com.rhea.messaging.configuration;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.producer.Producer;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @author 050618
 */
@Data
public class ProducerFactoryBean implements FactoryBean {
    @Autowired
    private MQProperties mqProperties;
    private ProducerConfig producerConfig;
    private final Class<?> producerClass;
    private Object instance;

    public ProducerFactoryBean(Class<?> producerClass) {
        this.producerClass = producerClass;
    }

    @Override
    public Object getObject() throws Exception {
        if (null == instance) {
            mqProperties.stuffConfig(producerConfig);
            Producer producer = buildAccessPoint(producerConfig).createProducer();
            ProducerHandler producerHandler = new ProducerHandler(producerConfig, producer);
            instance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{producerClass}, producerHandler);
            producer.startup();
        }
        return instance;
    }

    private MessagingAccessPoint buildAccessPoint(TopicConfig topicConfig) {
        return OMS.getMessagingAccessPoint(topicConfig.getServerUrl());
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
