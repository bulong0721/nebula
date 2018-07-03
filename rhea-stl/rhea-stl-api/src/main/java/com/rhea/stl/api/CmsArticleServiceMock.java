package com.rhea.stl.api;

import com.rhea.common.base.BaseServiceMock;
import com.rhea.stl.dao.mapper.CmsArticleMapper;
import com.rhea.stl.dao.model.CmsArticle;
import com.rhea.stl.dao.model.CmsArticleExample;

/**
* 降级实现CmsArticleService接口
* Created by rhea on 2018/7/3.
*/
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

}
