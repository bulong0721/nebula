package com.rhea.stl.service.impl;

import com.rhea.common.annotation.BaseService;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.stl.dao.mapper.CmsSettingMapper;
import com.rhea.stl.dao.model.CmsSetting;
import com.rhea.stl.dao.model.CmsSettingExample;
import com.rhea.stl.rpc.api.CmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSettingService实现
* Created by rhea on 2018/7/3.
*/
@Service
@Transactional
@BaseService
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

    @Autowired
    CmsSettingMapper cmsSettingMapper;

}