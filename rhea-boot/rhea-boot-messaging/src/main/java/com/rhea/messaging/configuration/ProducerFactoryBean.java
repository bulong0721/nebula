package com.rhea.messaging.configuration;

import com.google.common.reflect.Reflection;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.producer.Producer;
import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @author 050618
 */
@Data
public class ProducerFactoryBean implements FactoryBean, DisposableBean {
    @Autowired
    private MQProperties mqProperties;
    private ProducerConfig producerConfig;
    private final Class<?> producerClass;
    private Object instance;
    private Producer producer;

    public ProducerFactoryBean(Class<?> producerClass) {
        this.producerClass = producerClass;
    }

    @Override
    public Object getObject() throws Exception {
        if (null == instance) {
            mqProperties.stuffConfig(producerConfig);
            producer = buildAccessPoint(producerConfig).createProducer();
            ProducerHandler producerHandler = new ProducerHandler(producerConfig, producer);
            instance = Reflection.newProxy(producerClass, producerHandler);
            producer.startup();
        }
        return instance;
    }

    @Override
    public void destroy() throws Exception {
        if (null != producer) {
            producer.shutdown();
        }
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
