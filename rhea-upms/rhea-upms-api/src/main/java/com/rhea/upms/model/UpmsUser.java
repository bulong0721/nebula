package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_user")
public class UpmsUser extends BaseEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 帐号
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码MD5(密码+盐)
     */
    @Column(name = "password")
    private String password;

    /**
     * 盐
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 姓名
     */
    @Column(name = "realname")
    private String realname;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Byte sex;

    /**
     * 状态(0:正常,1:锁定)
     */
    @Column(name = "locked")
    private Byte locked;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Long ctime;
}