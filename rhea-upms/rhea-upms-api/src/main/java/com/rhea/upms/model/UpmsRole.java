package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_role")
public class UpmsRole extends BaseEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 角色标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 角色描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Long ctime;

    /**
     * 排序
     */
    @Column(name = "orders")
    private Long orders;
}