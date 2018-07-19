package com.rhea.upms.service.impl;

import com.rhea.upms.api.UpmsApiService;
import com.rhea.upms.mapper.UpmsLogMapper;
import com.rhea.upms.mapper.UpmsOrganizationMapper;
import com.rhea.upms.mapper.UpmsRolePermissionMapper;
import com.rhea.upms.mapper.UpmsSystemMapper;
import com.rhea.upms.mapper.UpmsUserMapper;
import com.rhea.upms.mapper.UpmsUserPermissionMapper;
import com.rhea.upms.model.UpmsLog;
import com.rhea.upms.model.UpmsOrganization;
import com.rhea.upms.model.UpmsOrganizationExample;
import com.rhea.upms.model.UpmsPermission;
import com.rhea.upms.model.UpmsRole;
import com.rhea.upms.model.UpmsRolePermission;
import com.rhea.upms.model.UpmsSystem;
import com.rhea.upms.model.UpmsSystemExample;
import com.rhea.upms.model.UpmsUser;
import com.rhea.upms.model.UpmsUserPermission;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UpmsApiServiceImpl implements UpmsApiService {
    @Autowired
    private UpmsRolePermissionMapper rolePermissionMapper;
    @Autowired
    private UpmsUserPermissionMapper userPermissionMapper;
    @Autowired
    private UpmsOrganizationMapper organizationMapper;
    @Autowired
    private UpmsSystemMapper systemMapper;
    @Autowired
    private UpmsUserMapper userMapper;
    @Autowired
    private UpmsLogMapper logMapper;

    @Override public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        return null;
    }

    @Override public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return null;
    }

    @Override public UpmsUser selectUpmsUserByUsername(String username) {
        return null;
    }

    @Override public int insertUpmsLogSelective(UpmsLog record) {
        return 0;
    }
}
