package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsArticleMapper;
import com.rhea.stl.dao.model.CmsArticle;

/**
* 降级实现CmsArticleService接口
* Created by rhea on 2018/7/3.
*/
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticleMapper, CmsArticle> implements CmsArticleService {

}
