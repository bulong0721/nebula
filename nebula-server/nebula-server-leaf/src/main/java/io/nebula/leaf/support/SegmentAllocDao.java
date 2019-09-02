package io.nebula.leaf.support;

import com.google.common.collect.Lists;
import com.sankuai.inf.leaf.segment.dao.IDAllocDao;
import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import io.nebula.leaf.mapper.LeafAllocMapper;
import io.nebula.leaf.model.LeafAllocExample;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2019/5/23
 */
public class SegmentAllocDao implements IDAllocDao {
    private final LeafAllocMapper mapper;

    public SegmentAllocDao(LeafAllocMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<LeafAlloc> getAllLeafAllocs() {
        return Lists.transform(mapper.selectAll(), a -> {
            return toDto(a);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
   // @SequenceGenerator()
    public LeafAlloc updateMaxIdAndGetLeafAlloc(String s) {
        mapper.updateMaxId(s);
        LeafAllocExample example = new LeafAllocExample();
        example.createCriteria().andBizTagEqualTo(s);
        return toDto(mapper.selectOneByExample(example));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc) {
        mapper.updateMaxIdByCustomStep(leafAlloc);
        LeafAllocExample example = new LeafAllocExample();
        example.createCriteria().andBizTagEqualTo(leafAlloc.getKey());
        return toDto(mapper.selectOneByExample(example));
    }

    @Override
    public List<String> getAllTags() {
        List<io.nebula.leaf.model.LeafAlloc> leafAllocs = mapper.selectAll();
        return Lists.transform(leafAllocs, io.nebula.leaf.model.LeafAlloc::getBizTag);
    }

    public static LeafAlloc toDto(io.nebula.leaf.model.LeafAlloc entity) {
        LeafAlloc leafAlloc= new LeafAlloc();
        if (null != entity) {
            leafAlloc.setKey(entity.getBizTag());
            leafAlloc.setMaxId(entity.getMaxId());
            leafAlloc.setStep(entity.getStep());
            leafAlloc.setUpdateTime(String.valueOf(entity.getUpdateTime()));
        }
        return leafAlloc;
    }
    public int insertLeafAlloc(String key){
        io.nebula.leaf.model.LeafAlloc leafAlloc= new io.nebula.leaf.model.LeafAlloc();
        leafAlloc.setBizTag(key);
        leafAlloc.setMaxId(Long.valueOf(1));
        leafAlloc.setStep(1000);
        int result=0;
        result= mapper.insert(leafAlloc);
        return  result ;
    }
}
