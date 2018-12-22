package com.rhea.kernel.security;

import java.io.Serializable;

/**
 * @author xubulong
 * @version V1.0
 */
public interface IUser extends Serializable {

    /**
     * 用户名
     *
     * @return
     */
    String getUserName();

    /**
     * 是否可用
     *
     * @return
     */
    boolean isActive();

    /**
     * 角色
     *
     * @return
     */
    String getRole();

    /**
     * 用户昵称
     *
     * @return
     */
    String getNickname();

    /**
     * 用户口令
     *
     * @return
     */
    String getPassword();
}
