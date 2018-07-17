package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_organization")
public class UpmsOrganization extends BaseEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 所属上级
     */
    @Column(name = "pid")
    private Integer pid;

    /**
     * 组织名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 组织描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Long ctime;
}