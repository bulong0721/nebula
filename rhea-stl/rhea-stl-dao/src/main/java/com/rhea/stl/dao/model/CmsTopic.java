package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_topic")
public class CmsTopic {
    /**
     * 编号
     */
    @Id
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Long ctime;
}