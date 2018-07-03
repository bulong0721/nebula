package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_page")
public class CmsPage {
    /**
     * 编码
     */
    @Id
    @Column(name = "page_id")
    private Integer pageId;

    /**
     * 父页面
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 别名
     */
    @Column(name = "alias")
    private String alias;

    /**
     * 关键字
     */
    @Column(name = "keywords")
    private String keywords;

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

    /**
     * 页面内容
     */
    @Column(name = "content")
    private String content;
}