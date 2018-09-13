package com.rhea.schedule.configuration;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.rhea.schedule.annotation.Schedule;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author 050618
 */
public class ClassPathJobScanner extends ClassPathBeanDefinitionScanner {

    public ClassPathJobScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerFilters() {
        addIncludeFilter(new CombineTypeFilter(Schedule.class, ElasticJob.class));

        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
        if (super.checkCandidate(beanName, beanDefinition)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No Job Component was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
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
            if (metadata.hasAnnotation(Schedule.class.getName())) {
                BeanDefinition scheduler = BeanDefinitionBuilder.genericBeanDefinition(ElasticScheduler.class).getBeanDefinition();
                String beanName = holder.getBeanName() + "Scheduler";
                ScheduleConfig scheduleConfig = toScheduleConfig(metadata.getAnnotationAttributes(Schedule.class.getName()));
                scheduler.getPropertyValues().addPropertyValue("scheduleConfig", scheduleConfig);
                scheduler.getPropertyValues().addPropertyValue("elasticJob", new RuntimeBeanReference(holder.getBeanName()));
                getRegistry().registerBeanDefinition(beanName, scheduler);
            }
        }
    }

    private ScheduleConfig toScheduleConfig(Map<String, Object> metadataSet) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadataSet);
        ScheduleConfig config = new ScheduleConfig();
        config.setName(attributes.getString("name"));
        config.setDescription(attributes.getString("description"));
        config.setCron(attributes.getString("cron"));
        config.setShardingCount(attributes.getNumber("shardingCount"));
        config.setFailover(attributes.getBoolean("failover"));
        config.setMisfire(attributes.getBoolean("misfire"));
        return config;
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
            return annotationTypeFilter.match(metadataReader, metadataReaderFactory)
                    && assignableTypeFilter.match(metadataReader, metadataReaderFactory);
        }
    }
}
