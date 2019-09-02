package ${package};

import io.nebula.core.annotation.EnableFramework;
import io.nebula.kernel.configuration.OpenFeign;
import io.nebula.kernel.batch.BatchFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author nebula-archetype
 * @version ${version}
 * @date ${date}
 */
@EnableFramework
@EnableDiscoveryClient
@EnableFeignClients(value = "io.nebula", defaultConfiguration = OpenFeign.class)
@MapperScan(value = "io.nebula.*.mapper", factoryBean = BatchFactoryBean.class)
@ComponentScan(value = "io.nebula")
public class MsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApplication.class);
    }
}
