package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsTagMapper;
import com.rhea.stl.dao.model.CmsTag;
import com.rhea.stl.dao.model.CmsTagExample;

/**
* 降级实现CmsTagService接口
* Created by rhea on 2018/7/3.
*/
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
