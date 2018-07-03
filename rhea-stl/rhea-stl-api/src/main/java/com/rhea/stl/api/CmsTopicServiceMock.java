package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsTopicMapper;
import com.rhea.stl.dao.model.CmsTopic;

/**
* 降级实现CmsTopicService接口
* Created by rhea on 2018/7/3.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic> implements CmsTopicService {

}
