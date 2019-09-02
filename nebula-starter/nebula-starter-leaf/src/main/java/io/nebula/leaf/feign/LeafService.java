package io.nebula.leaf.feign;

import io.nebula.kernel.exchange.ResponseEntity;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author nebula
 */
@FeignClient(value = "nebula-server-leaf")
public interface LeafService {

    @RequestLine("GET /segmentDB/get/{key}")
    ResponseEntity<Long> segmentDB(@Param("key") String key);

    @RequestLine("GET /snowflakeZK/get/{key}")
    ResponseEntity<Long> segmentZK(@Param("key") String key);

}
