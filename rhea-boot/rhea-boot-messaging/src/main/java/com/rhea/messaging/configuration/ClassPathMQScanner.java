package com.rhea.messaging.configuration;

import com.rhea.messaging.annotation.Consumer;
import com.rhea.messaging.annotation.Producer;
import com.rhea.messaging.api.MQConsumer;
import com.rhea.messaging.api.MQProducer;
import lombok.Data;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * @author 050618
 */
@Data
public class ClassPathMQScanner extends ClassPathBeanDefinitionScanner {
    private boolean consumeOn = true;
    private boolean produceOn = true;

    public ClassPathMQScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerFilters() {
        boolean acceptAllInterfaces = true;
        if (produceOn) {
            addIncludeFilter(new CombineTypeFilter(Producer.class, MQProducer.class));
            acceptAllInterfaces = false;
        }
        if (consumeOn) {
            addIncludeFilter(new CombineTypeFilter(Consumer.class, MQConsumer.class));
            acceptAllInterfaces = false;
        }
        if (acceptAllInterfaces) {
            addIncludeFilter(new TypeFilter() {
                @Override
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No MQ Component was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        ScannedGenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (ScannedGenericBeanDefinition) holder.getBeanDefinition();
            if (logger.isDebugEnabled()) {
                logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName()
                        + "' and '" + definition.getBeanClassName() + "' mapperInterface");
            }

            boolean explicitFactoryUsed = false;
            if (!explicitFactoryUsed) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Enabling autowire by type for MapperFactoryBean with name '" + holder.getBeanName() + "'.");
                }
            }
            AnnotationMetadata metadata = definition.getMetadata();
            if (metadata.hasAnnotation(Producer.class.getName())) {
                String className = definition.getBeanClassName();
                ProducerConfig producerConfig = toProducerConfig(metadata.getAnnotatedMethods(Producer.class.getName()));
                definition.setBeanClass(ProducerFactoryBean.class);
                definition.getPropertyValues().add("producerConfig", producerConfig);
//                definition.getPropertyValues().add("producerClass", producerClass);
                definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            } else if (metadata.hasAnnotation(Consumer.class.getName())) {

            }
        }
    }

    ProducerConfig toProducerConfig(Set<MethodMetadata> metadataSet) {
        ProducerConfig config = new ProducerConfig();
        return config;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            logger.warn("Skipping MapperFactoryBean with name '" + beanName
                    + "' and '" + beanDefinition.getBeanClassName() + "' mapperInterface"
                    + ". Bean already defined with the same name!");
            return false;
        }
    }

    static class CombineTypeFilter implements TypeFilter {
        private final AnnotationTypeFilter annotationTypeFilter;
        private final AssignableTypeFilter assignableTypeFilter;

        public CombineTypeFilter(Class<? extends Annotation> annotationClass, Class<?> interfaceClass) {
            this.annotationTypeFilter = new AnnotationTypeFilter(annotationClass);
            this.assignableTypeFilter = new AssignableTypeFilter(interfaceClass);
        }

        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            boolean result = annotationTypeFilter.match(metadataReader, metadataReaderFactory)
                    && assignableTypeFilter.match(metadataReader, metadataReaderFactory);
            System.out.println(metadataReader.getResource().toString() + result);
            return result;
        }
    }
}
