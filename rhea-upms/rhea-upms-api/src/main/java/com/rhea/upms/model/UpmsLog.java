package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_log")
public class UpmsLog extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "log_id")
    private Integer logId;

    /**
     * 操作描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 操作用户
     */
    @Column(name = "username")
    private String username;

    /**
     * 操作时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 消耗时间
     */
    @Column(name = "spend_time")
    private Integer spendTime;

    /**
     * 根路径
     */
    @Column(name = "base_path")
    private String basePath;

    /**
     * URI
     */
    @Column(name = "uri")
    private String uri;

    /**
     * URL
     */
    @Column(name = "url")
    private String url;

    /**
     * 请求类型
     */
    @Column(name = "method")
    private String method;

    /**
     * 用户标识
     */
    @Column(name = "user_agent")
    private String userAgent;

    /**
     * IP地址
     */
    @Column(name = "ip")
    private String ip;

    /**
     * 权限值
     */
    @Column(name = "permissions")
    private String permissions;

    @Column(name = "parameter")
    private String parameter;

    @Column(name = "result")
    private String result;
}