package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_system")
public class UpmsSystem extends BaseEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "system_id")
    private Integer systemId;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 背景
     */
    @Column(name = "banner")
    private String banner;

    /**
     * 主题
     */
    @Column(name = "theme")
    private String theme;

    /**
     * 根目录
     */
    @Column(name = "basepath")
    private String basepath;

    /**
     * 状态(-1:黑名单,1:正常)
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 系统名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 系统标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 系统描述
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