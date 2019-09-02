package io.nebula.messaging.configuration;

import io.nebula.messaging.api.MessageSender;
import io.nebula.messaging.properties.MessagingProperties;
import io.nebula.messaging.properties.ProduceConfig;
import lombok.Data;
import net.bytebuddy.ByteBuddy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/1/10
 */
@Data
public class ProducerFactoryBean implements FactoryBean, DisposableBean {
    private static Map<Class<? extends MessageSender>, Class<?>> implMap = new ConcurrentHashMap<>();
    private final Class<? extends MessageSender> producerClass;
    @Autowired
    private MessagingProperties mqProperties;
    @Autowired
    private ProducerTemplate template;
    private ProduceConfig produceConfig;
    private AbstractMessageSender instance;

    @Override
    public Object getObject() throws Exception {
        if (null == instance) {
            mqProperties.stuffConfig(produceConfig);
            instance = (AbstractMessageSender) getImpClass().newInstance();
            instance.setConfig(produceConfig);
            instance.setTemplate(template);
        }
        return instance;
    }

    private synchronized Class<?> getImpClass() {
        if (!implMap.containsKey(producerClass)) {
            Class<? extends AbstractMessageSender> implClazz = new ByteBuddy()
                    .subclass(AbstractMessageSender.class)
                    .implement(producerClass)
                    .make()
                    .load(producerClass.getClassLoader())
                    .getLoaded();
            implMap.putIfAbsent(producerClass, implClazz);
        }
        return implMap.get(producerClass);
    }

    @Override
    public void destroy() throws Exception {
        template.destroy();
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
