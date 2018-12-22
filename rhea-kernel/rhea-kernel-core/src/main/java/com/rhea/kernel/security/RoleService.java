package com.rhea.kernel.security;

import java.util.List;

/**
 * @author xubulong
 * @version V1.0
 */
public interface RoleService {

    /**
     * 根据用户查询角色信息
     *
     * @param username
     * @return
     */
    List<IRole> listRole(String username);
}
