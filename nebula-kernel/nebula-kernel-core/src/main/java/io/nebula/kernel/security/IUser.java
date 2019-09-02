package io.nebula.kernel.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 徐步龙
 * @version V1.0 created at: 2018/12/18
 */
public interface IUser extends Serializable {

    /**
     * 用户名
     *
     * @return
     */
    String getUserName();

    /**
     * 用户昵称
     *
     * @return
     */
    String getNickname();

    /**
     * 用户简介信息
     *
     * @return
     */
    default Object summary() {
        return this;
    }

    @Data
    @AllArgsConstructor
    public static class Summary {
        private String userName;
        private String nickname;
    }
}
