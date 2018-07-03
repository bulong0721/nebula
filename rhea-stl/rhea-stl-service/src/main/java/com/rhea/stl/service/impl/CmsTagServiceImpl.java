package com.rhea.stl.service.impl;

import com.rhea.common.annotation.BaseService;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.stl.dao.mapper.CmsTagMapper;
import com.rhea.stl.dao.model.CmsTag;
import com.rhea.stl.dao.model.CmsTagExample;
import com.rhea.stl.rpc.api.CmsTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsTagService实现
* Created by rhea on 2018/7/3.
*/
@Service
@Transactional
@BaseService
public class CmsTagServiceImpl extends BaseServiceImpl<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsTagServiceImpl.class);

    @Autowired
    CmsTagMapper cmsTagMapper;

}