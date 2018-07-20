package com.rhea.common.shiro;

import java.util.List;

public interface ShiroService {

    IUser getUserByName(String username);

    <T extends IRole> List<T> getRolesByUserId(Integer userId);

    <T extends IPermission> List<T> getPermissionsByUserId(Integer userId);
}
