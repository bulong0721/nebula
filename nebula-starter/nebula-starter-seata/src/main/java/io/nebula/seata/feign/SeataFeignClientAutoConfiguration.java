package io.nebula.seata.feign;

import feign.Client;
import feign.Feign;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/17
 */
@Configuration
@ConditionalOnClass(Client.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class SeataFeignClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Scope("prototype")
    Feign.Builder feignBuilder(BeanFactory beanFactory) {
        return SeataFeignBuilder.builder(beanFactory);
    }


    @Configuration
    protected static class FeignBeanPostProcessorConfiguration {

        @Bean
        SeataBeanPostProcessor seataBeanPostProcessor(SeataFeignObjectWrapper seataFeignObjectWrapper) {
            return new SeataBeanPostProcessor(seataFeignObjectWrapper);
        }

        @Bean
        SeataContextBeanPostProcessor seataContextBeanPostProcessor(BeanFactory beanFactory) {
            return new SeataContextBeanPostProcessor(beanFactory);
        }

        @Bean
        SeataFeignObjectWrapper seataFeignObjectWrapper(BeanFactory beanFactory) {
            return new SeataFeignObjectWrapper(beanFactory);
        }
    }
}
