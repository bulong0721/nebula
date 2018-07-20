package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_user_role")
public class UpmsUserRole extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "user_role_id")
    private Integer userRoleId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;
}