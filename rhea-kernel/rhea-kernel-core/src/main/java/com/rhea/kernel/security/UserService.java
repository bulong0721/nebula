package com.rhea.kernel.security;

import com.rhea.kernel.exception.LoginException;

import java.util.Map;

/**
 * @author xubulong
 * @version V1.0
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
}
