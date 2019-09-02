package io.nebula.leaf.service.impl;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import com.sankuai.inf.leaf.snowflake.SnowflakeIDGenImpl;
import io.nebula.kernel.service.IDGenerateService;
import io.nebula.leaf.support.SegmentAllocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDGenServiceImpl implements IDGenerateService {
    @Autowired
    private SegmentIDGenImpl segmentIDGen;
    @Autowired
    private SnowflakeIDGenImpl snowflakeIDGen;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SegmentAllocDao segmentAllocDao;
    @Override
    public Result getSegmentID (String key) {
        //如果找不到对应key就重新创建一个
        boolean check=checkBizTag(key);
        if(check==false){
            segmentAllocDao.insertLeafAlloc(key);
            segmentIDGen.init();
        }
        return segmentIDGen.get(key);
    }
    @Override
    public Object getSnowflakeID(String key) {
        return snowflakeIDGen.get(key);
    }

    /**
     * 判断DB里面是否有该biz_tag
     * @param key
     * @return
     */
    private boolean checkBizTag(String key){
        List<LeafAlloc> listLc=segmentIDGen.getAllLeafAllocs();
        if(listLc.size()>0){
            for(LeafAlloc lc : listLc){
                if(key.equals(lc.getKey())){
                    return true;
                }
            }
        }
        return false;
    }
}
