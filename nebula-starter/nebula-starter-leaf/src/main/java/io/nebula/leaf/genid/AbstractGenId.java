package io.nebula.leaf.genid;

import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.leaf.feign.LeafService;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/7/2
 */
public abstract class AbstractGenId implements GenId<Long> {

    protected static volatile String namespace;
    protected static volatile LeafService client;

    public static void setClient(String namespace, LeafService client) {
        AbstractGenId.namespace = namespace;
        AbstractGenId.client = client;
    }

    @Override
    public Long genId(String table, String column) {
        ResponseEntity<Long> response = generate(AbstractGenId.client, generateKey(table, column));
        return response.getData();
    }

    protected abstract ResponseEntity<Long> generate(LeafService client, String key);

    protected String generateKey(String table, String column) {
        return String.format("%s-%s-%s", AbstractGenId.namespace, table, column);
    }
}
