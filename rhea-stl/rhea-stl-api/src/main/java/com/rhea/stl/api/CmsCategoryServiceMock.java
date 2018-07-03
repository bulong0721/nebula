package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsCategoryMapper;
import com.rhea.stl.dao.model.CmsCategory;
import com.rhea.stl.dao.model.CmsCategoryExample;

/**
* 降级实现CmsCategoryService接口
* Created by rhea on 2018/7/3.
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
