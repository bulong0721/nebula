package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsMenuMapper;
import com.rhea.stl.dao.model.CmsMenu;
import com.rhea.stl.dao.model.CmsMenuExample;

/**
* 降级实现CmsMenuService接口
* Created by rhea on 2018/7/3.
*/
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
