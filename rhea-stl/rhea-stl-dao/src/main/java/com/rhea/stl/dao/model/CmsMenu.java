package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_menu")
public class CmsMenu {
    /**
     * 编号
     */
    @Id
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * 父菜单
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 打开方式
     */
    @Column(name = "target")
    private String target;

    /**
     * 排序
     */
    @Column(name = "orders")
    private Long orders;
}