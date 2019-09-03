package io.nebula.kernel.configuration;

import feign.Request;
import org.springframework.context.annotation.Bean;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/28
 */
public abstract class AbstractFeign {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(2000, 10000);
    }
}
