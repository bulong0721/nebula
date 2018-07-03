package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_system")
public class CmsSystem {
    /**
     * 编号
     */
    @Id
    @Column(name = "system_id")
    private Integer systemId;

    /**
     * 系统名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 别名
     */
    @Column(name = "code")
    private String code;

    /**
     * 描述
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