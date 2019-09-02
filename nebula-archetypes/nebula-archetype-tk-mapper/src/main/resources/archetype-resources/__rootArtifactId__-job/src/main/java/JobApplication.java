package ${package};

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nebula.core.annotation.EnableFramework;
import io.nebula.kernel.configuration.OpenFeign;
import io.nebula.messaging.annotation.EnableMQ;
import io.nebula.schedule.annotation.EnableSchedule;
import io.nebula.kernel.batch.BatchFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author nebula-archetype
 * @version 1.0-SNAPSHOT
 * @date ${date}
 */
@EnableFramework
@EnableMQ
@EnableSchedule
@EnableDiscoveryClient
@EnableFeignClients(value = "io.nebula", defaultConfiguration = OpenFeign.class)
@MapperScan(value = "io.nebula.*.mapper", factoryBean = BatchFactoryBean.class)
@ComponentScan(value = "io.nebula")
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class);
    }
}
