package io.nebula.kernel.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Contract;
import io.nebula.kernel.feign.FeignCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/10/17
 */
@Slf4j
@Configuration
public class OpenFeign extends AbstractFeign {

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public FeignCodec feignCodec(ObjectMapper objectMapper) {
        return new FeignCodec(objectMapper);
    }
}
