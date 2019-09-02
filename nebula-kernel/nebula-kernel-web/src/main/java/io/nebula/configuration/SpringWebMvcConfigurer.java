package io.nebula.configuration;

import io.nebula.aspect.RequestLogAspect;
import io.nebula.web.filter.ServiceContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/13
 */
@Configuration
public class SpringWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ServiceContextFilter());
    }

    @Bean
    RequestLogAspect requestLogAspect() {
        final int order = Byte.MAX_VALUE;
        return new RequestLogAspect(order);
    }
}
