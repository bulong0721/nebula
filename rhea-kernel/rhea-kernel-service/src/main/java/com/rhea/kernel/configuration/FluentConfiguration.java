package com.rhea.kernel.configuration;

import com.baidu.unbiz.fluentvalidator.registry.impl.SpringApplicationContextRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author xubulong
 * @version V1.0
 */
@Configuration
public class FluentConfiguration {

    @Bean
    Validator hibernateValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    @Bean
    SpringApplicationContextRegistry contextRegistry(ApplicationContext applicationContext) {
        SpringApplicationContextRegistry s = new SpringApplicationContextRegistry();
        s.setApplicationContext(applicationContext);
        return s;
    }
}
