package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsCommentMapper;
import com.rhea.stl.dao.model.CmsComment;

/**
* 降级实现CmsCommentService接口
* Created by rhea on 2018/7/3.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment> implements CmsCommentService {

}
