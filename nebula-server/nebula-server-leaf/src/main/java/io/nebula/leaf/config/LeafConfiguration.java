package io.nebula.leaf.config;

import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import io.nebula.leaf.mapper.LeafAllocMapper;
import io.nebula.leaf.support.LeafProperties;
import io.nebula.leaf.support.SegmentAllocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/23
 */
@Configuration
@EnableConfigurationProperties(LeafProperties.class)
public class LeafConfiguration {
    @Autowired
    private LeafProperties properties;

    @Bean(initMethod = "init")
    public SnowflakeIDGenImpl snowflakeIDGen() {
        LeafProperties.Snowflake snowflake = properties.getSnowflake();
        String zkAddr = snowflake.getZkAddress();
        int port = snowflake.getPort();
        return new SnowflakeIDGenImpl(zkAddr, port);
    }

    @Bean(initMethod = "init")
    public SegmentIDGenImpl segmentIDGen(IDAllocDao allocDao) {
        SegmentIDGenImpl segmentIDGen = new SegmentIDGenImpl();
        segmentIDGen.setDao(allocDao);
        return segmentIDGen;
    }

    @Bean
    public IDAllocDao allocDao(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") LeafAllocMapper mapper) {
        return new SegmentAllocDao(mapper);
    }
}
