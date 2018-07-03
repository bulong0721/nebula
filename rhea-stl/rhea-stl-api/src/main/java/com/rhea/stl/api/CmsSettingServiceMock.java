package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsSettingMapper;
import com.rhea.stl.dao.model.CmsSetting;
import com.rhea.stl.dao.model.CmsSettingExample;

/**
* 降级实现CmsSettingService接口
* Created by rhea on 2018/7/3.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
