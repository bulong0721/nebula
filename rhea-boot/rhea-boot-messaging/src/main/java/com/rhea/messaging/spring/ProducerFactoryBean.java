package com.rhea.messaging.spring;

import com.rhea.messaging.producer.Producer;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author 05061
 */
public class ProducerFactoryBean extends AbstractFactoryBean<Producer> {

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    protected Producer createInstance() throws Exception {
        return null;
    }
}
