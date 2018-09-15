package com.rhea.common.configuration;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloProcessor;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.rhea.common.annotation.DynamicConfig;
import com.rhea.common.annotation.ConfigChangeListener;
import com.rhea.common.api.ConfigChangeObject;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author 050618
 */
public class AnnotationProcessorAdapter extends ApolloProcessor {
    @Override
    protected void processField(Object bean, String beanName, Field field) {
        DynamicConfig annotation = AnnotationUtils.getAnnotation(field, DynamicConfig.class);
        if (annotation == null) {
            return;
        }

        Preconditions.checkArgument(Config.class.isAssignableFrom(field.getType()),
                "Invalid type: %s for field: %s, should be Config", field.getType(), field);

        String namespace = annotation.value();
        Config config = ConfigService.getConfig(namespace);

        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, bean, config);
    }

    @Override
    protected void processMethod(final Object bean, String beanName, final Method method) {
        ConfigChangeListener annotation = AnnotationUtils.findAnnotation(method, ConfigChangeListener.class);
        if (annotation == null) {
            return;
        }
        Class<?>[] parameterTypes = method.getParameterTypes();
        Preconditions.checkArgument(parameterTypes.length == 1,
                "Invalid number of parameters: %s for method: %s, should be 1", parameterTypes.length,
                method);
        Preconditions.checkArgument(ConfigChangeObject.class.isAssignableFrom(parameterTypes[0]),
                "Invalid parameter type: %s for method: %s, should be ConfigChangeEvent", parameterTypes[0],
                method);

        ReflectionUtils.makeAccessible(method);
        String[] namespaces = annotation.value();
        String[] annotatedInterestedKeys = annotation.interestedKeys();
        Set<String> interestedKeys = annotatedInterestedKeys.length > 0 ? Sets.newHashSet(annotatedInterestedKeys) : null;
        com.ctrip.framework.apollo.ConfigChangeListener configChangeListener = new com.ctrip.framework.apollo.ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                ConfigChangeObject event = new ConfigChangeObject(changeEvent);
                ReflectionUtils.invokeMethod(method, bean, event);
            }
        };

        for (String namespace : namespaces) {
            Config config = ConfigService.getConfig(namespace);

            if (interestedKeys == null) {
                config.addChangeListener(configChangeListener);
            } else {
                config.addChangeListener(configChangeListener, interestedKeys);
            }
        }
    }
}
