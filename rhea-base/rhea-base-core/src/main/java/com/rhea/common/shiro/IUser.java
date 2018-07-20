package com.rhea.common.shiro;

public interface IUser {
    Integer getUserId();

    String getPassword();

    String getSalt();

    int getLocked();
}
