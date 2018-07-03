package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_article_tag")
public class CmsArticleTag {
    /**
     * 编号
     */
    @Id
    @Column(name = "article_tag_id")
    private Integer articleTagId;

    /**
     * 文章编号
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 标签编号
     */
    @Column(name = "tag_id")
    private Integer tagId;
}