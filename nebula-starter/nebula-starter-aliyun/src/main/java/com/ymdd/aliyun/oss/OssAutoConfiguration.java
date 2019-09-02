package io.nebula.aliyun.oss;

import com.aliyun.oss.OSS;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/8/26
 */
@Configuration
@ConditionalOnClass(OSS.class)
@ConditionalOnProperty(name = OssConstants.ENABLED, havingValue = "true", matchIfMissing = true)
public class OssAutoConfiguration {

}
