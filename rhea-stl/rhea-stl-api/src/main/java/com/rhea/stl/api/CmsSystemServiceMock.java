package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsSystemMapper;
import com.rhea.stl.dao.model.CmsSystem;
import com.rhea.stl.dao.model.CmsSystemExample;

/**
* 降级实现CmsSystemService接口
* Created by rhea on 2018/7/3.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
