package com.rhea.messaging.spring;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.rhea.messaging.annotation.EnableMessaging;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * @author 05061
 */
public class MessagingImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMessaging.class.getName()));

        List<String> list = Lists.newArrayList();
        if (attributes.getBoolean("produceOn")) {
        }

        if (attributes.getBoolean("consumeOn")) {
            list.add(ConsumerScannerConfigurer.class.getName());
        }

        return Iterables.toArray(list, String.class);
    }
}
