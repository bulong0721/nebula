package com.rhea.kernel.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhea.kernel.feign.FeignCodec;
import feign.Contract;
import feign.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xubulong
 * @version V1.0
 */
@Slf4j
@Configuration
public class OpenFeign {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

    @Bean
    public FeignCodec feignCodec(ObjectMapper objectMapper) {
        return new FeignCodec(objectMapper);
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(2000, 10000);
    }
}
