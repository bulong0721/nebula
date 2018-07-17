package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_permission")
public class UpmsPermission extends BaseEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 所属系统
     */
    @Column(name = "system_id")
    private Integer systemId;

    /**
     * 所属上级
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 类型(1:目录,2:菜单,3:按钮)
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 权限值
     */
    @Column(name = "permission_value")
    private String permissionValue;

    /**
     * 路径
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 状态(0:禁止,1:正常)
     */
    @Column(name = "status")
    private Byte status;

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