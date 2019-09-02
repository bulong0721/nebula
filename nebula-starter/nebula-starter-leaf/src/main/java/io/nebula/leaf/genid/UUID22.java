package io.nebula.leaf.genid;

import io.nebula.leaf.algorithm.CompressUUID;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/2/25
 */
public class UUID22 implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return CompressUUID.uuid22();
    }
}
