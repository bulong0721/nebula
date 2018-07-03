package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_article")
public class CmsArticle {
    /**
     * 文章编号
     */
    @Id
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 所属专题
     */
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章原作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 转载来源网址
     */
    @Column(name = "fromurl")
    private String fromurl;

    /**
     * 封面图
     */
    @Column(name = "image")
    private String image;

    /**
     * 关键字
     */
    @Column(name = "keywords")
    private String keywords;

    /**
     * 简介
     */
    @Column(name = "description")
    private String description;

    /**
     * 类型(1:普通,2:热门...)
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 是否允许评论(0:不允许,1:允许)
     */
    @Column(name = "allowcomments")
    private Byte allowcomments;

    /**
     * 状态(-1:不通过,0未审核,1:通过)
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 发布人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 阅读数量
     */
    @Column(name = "readnumber")
    private Integer readnumber;

    /**
     * 置顶等级
     */
    @Column(name = "top")
    private Integer top;

    /**
     * 所属系统
     */
    @Column(name = "system_id")
    private Integer systemId;

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
     * 内容
     */
    @Column(name = "content")
    private String content;
}