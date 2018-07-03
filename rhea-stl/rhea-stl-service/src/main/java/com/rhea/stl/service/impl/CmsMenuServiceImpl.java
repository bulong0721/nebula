package com.rhea.stl.service.impl;

import com.rhea.common.annotation.BaseService;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.stl.dao.mapper.CmsMenuMapper;
import com.rhea.stl.dao.model.CmsMenu;
import com.rhea.stl.dao.model.CmsMenuExample;
import com.rhea.stl.rpc.api.CmsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsMenuService实现
* Created by rhea on 2018/7/3.
*/
@Service
@Transactional
@BaseService
public class CmsMenuServiceImpl extends BaseServiceImpl<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsMenuServiceImpl.class);

    @Autowired
    CmsMenuMapper cmsMenuMapper;

}