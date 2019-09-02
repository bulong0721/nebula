package io.nebula.leaf;

import io.nebula.core.annotation.EnableFramework;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/23
 */
@EnableFramework
@EnableDiscoveryClient
public class LeafApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeafApplication.class);
    }
}
