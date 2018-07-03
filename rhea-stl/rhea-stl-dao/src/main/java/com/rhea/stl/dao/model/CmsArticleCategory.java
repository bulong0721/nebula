package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_article_category")
public class CmsArticleCategory {
    /**
     * 编号
     */
    @Id
    @Column(name = "article_category_id")
    private Integer articleCategoryId;

    /**
     * 文章编号
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 类目编号
     */
    @Column(name = "category_id")
    private Integer categoryId;
}