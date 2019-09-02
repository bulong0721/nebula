package io.nebula.messaging.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.messaging.configuration.ConsumerContainer;
import io.nebula.messaging.configuration.ConsumerContainerFactory;
import io.nebula.messaging.properties.ConsumeConfig;
import io.nebula.messaging.properties.MessagingProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/14
 */
public abstract class MessageListener<T> implements InitializingBean {
    @Getter
    protected Type messageType;
    @Setter
    @Getter
    protected ConsumeConfig config;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MessagingProperties mqProperties;

    public MessageListener() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        messageType = typeArguments[0];
    }

    /**
     * 消息消费
     *
     * @param headers
     * @param message
     * @throws Exception
     */
    public abstract void onMessage(Headers headers, T message) throws Exception;


    @Override
    public void afterPropertiesSet() throws Exception {
        mqProperties.stuffConfig(config);
        ConsumerContainer container = ConsumerContainerFactory.getContainer(config, objectMapper);
        container.registerMessageListener(config, this);
        container.start();
    }
}
