package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_user_organization")
public class UpmsUserOrganization extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "user_organization_id")
    private Integer userOrganizationId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 组织编号
     */
    @Column(name = "organization_id")
    private Integer organizationId;
}