package com.rhea.kernel.security;

import java.io.Serializable;

/**
 * @author xubulong
 * @version V1.0
 */
public interface IRole extends Serializable {

    /**
     * 角色ID
     *
     * @return
     */
    long getId();

    /**
     * 角色名称
     *
     * @return
     */
    String getName();

    /**
     * 角色描述
     *
     * @return
     */
    String getDescription();
}
