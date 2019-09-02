package io.nebula.leaf.genid;

import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.leaf.feign.LeafService;
import lombok.Data;

/**
 * @author nebula
 */
@Data
public class LeafZKGenId extends AbstractGenId {

    @Override
    protected ResponseEntity<Long> generate(LeafService client, String key) {
        return client.segmentZK(key);
    }
}
