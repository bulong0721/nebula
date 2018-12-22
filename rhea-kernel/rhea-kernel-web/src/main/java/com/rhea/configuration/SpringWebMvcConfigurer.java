package com.rhea.configuration;

import com.rhea.web.support.PageRequestMethodProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author xubulong
 * @version V1.0
 */
@Configuration
public class SpringWebMvcConfigurer implements WebMvcConfigurer {
    private List<HandlerMethodArgumentResolver> resolvers;
    private List<HttpMessageConverter<?>> converters;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        this.resolvers = resolvers;
        if (this.converters != null) {
            this.resolvers.add(new PageRequestMethodProcessor(converters));
        }
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        this.converters = converters;
        if (this.resolvers != null) {
            this.resolvers.add(new PageRequestMethodProcessor(converters));
        }
    }
}
