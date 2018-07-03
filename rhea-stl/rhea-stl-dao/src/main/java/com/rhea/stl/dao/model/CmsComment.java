package com.rhea.stl.dao.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "cms_comment")
public class CmsComment {
    /**
     * 编号
     */
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 回复楼中楼编号回复楼中楼编号
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 文章编号
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 状态(-1:不通过,0:未审核,1:通过)
     */
    @Column(name = "status")
    private Byte status;

    /**
     * 评论人ip地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 评论人终端信息
     */
    @Column(name = "agent")
    private String agent;

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
     * 评论内容
     */
    @Column(name = "content")
    private String content;
}