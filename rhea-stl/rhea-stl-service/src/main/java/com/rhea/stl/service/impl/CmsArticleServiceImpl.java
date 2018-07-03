package com.rhea.stl.service.impl;

import com.rhea.common.annotation.BaseService;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.stl.dao.mapper.CmsArticleMapper;
import com.rhea.stl.dao.model.CmsArticle;
import com.rhea.stl.dao.model.CmsArticleExample;
import com.rhea.stl.rpc.api.CmsArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsArticleService实现
* Created by rhea on 2018/7/3.
*/
@Service
@Transactional
@BaseService
public class CmsArticleServiceImpl extends BaseServiceImpl<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsArticleServiceImpl.class);

    @Autowired
    CmsArticleMapper cmsArticleMapper;

}