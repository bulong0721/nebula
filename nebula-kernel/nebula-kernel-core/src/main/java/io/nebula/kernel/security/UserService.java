package io.nebula.kernel.security;

import io.nebula.kernel.exception.LoginException;

import java.util.Map;
import java.util.Set;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/18
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    IUser findByName(String username);

    /**
     * 验证登录
     *
     * @param username
     * @param password
     * @param properties
     * @return
     * @throws LoginException
     */
    IUser validate(String username, String password, Map<String, Object> properties) throws LoginException;

    /**
     * 根据用户查询角色
     *
     * @param username
     * @return
     */
    Set<String> listRole(String username);

    /**
     * 根据用户查询权限
     *
     * @param username
     * @return
     */
    Set<String> listPermission(String username);
}
