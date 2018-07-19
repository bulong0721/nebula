package com.rhea.upms.api;

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

public interface UpmsApiService {
    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId);

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String username);

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    int insertUpmsLogSelective(UpmsLog record);
}
