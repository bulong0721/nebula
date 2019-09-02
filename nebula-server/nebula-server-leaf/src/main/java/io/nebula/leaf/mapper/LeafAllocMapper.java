package io.nebula.leaf.mapper;

import io.nebula.kernel.mapper.TkBaseMapper;
import io.nebula.leaf.model.LeafAlloc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeafAllocMapper extends TkBaseMapper<LeafAlloc> {

    void updateMaxId(String value);

    void updateMaxIdByCustomStep(com.sankuai.inf.leaf.segment.model.LeafAlloc leafAlloc);

    void insertLeafAlloc(String value);
}
