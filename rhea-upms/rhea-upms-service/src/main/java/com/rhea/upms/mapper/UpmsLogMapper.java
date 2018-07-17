package com.rhea.upms.mapper;

import com.rhea.common.base.BaseMapper;
import com.rhea.upms.model.UpmsLog;
import com.rhea.upms.model.UpmsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UpmsLogMapper extends BaseMapper<UpmsLog> {
    List<UpmsLog> selectByExampleWithBLOBs(UpmsLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmsLog record, @Param("example") UpmsLogExample example);
}