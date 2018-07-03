package com.rhea.stl.service.impl;

import com.rhea.common.annotation.BaseService;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.stl.dao.mapper.CmsPageMapper;
import com.rhea.stl.dao.model.CmsPage;
import com.rhea.stl.dao.model.CmsPageExample;
import com.rhea.stl.rpc.api.CmsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsPageService实现
* Created by rhea on 2018/7/3.
*/
@Service
@Transactional
@BaseService
public class CmsPageServiceImpl extends BaseServiceImpl<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPageServiceImpl.class);

    @Autowired
    CmsPageMapper cmsPageMapper;

}