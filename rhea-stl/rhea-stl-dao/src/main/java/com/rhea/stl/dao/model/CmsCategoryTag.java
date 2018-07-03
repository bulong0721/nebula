package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_category_tag")
public class CmsCategoryTag {
    /**
     * 编号
     */
    @Id
    @Column(name = "category_tag_id")
    private Integer categoryTagId;

    /**
     * 类目编号
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 标签编号
     */
    @Column(name = "tag_id")
    private Integer tagId;
}