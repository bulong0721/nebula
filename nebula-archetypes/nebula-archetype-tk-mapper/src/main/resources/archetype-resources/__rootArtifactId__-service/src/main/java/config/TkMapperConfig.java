package ${package}.config;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/20
 */
@Configuration
@MapperScan("io.nebula.*.mapper")
public class TkMapperConfig {

}
