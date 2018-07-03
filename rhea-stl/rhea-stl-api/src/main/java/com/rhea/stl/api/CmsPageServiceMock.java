package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsPageMapper;
import com.rhea.stl.dao.model.CmsPage;
import com.rhea.stl.dao.model.CmsPageExample;

/**
* 降级实现CmsPageService接口
* Created by rhea on 2018/7/3.
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
