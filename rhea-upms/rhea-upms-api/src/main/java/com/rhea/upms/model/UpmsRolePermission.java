package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_role_permission")
public class UpmsRolePermission extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "role_permission_id")
    private Integer rolePermissionId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限编号
     */
    @Column(name = "permission_id")
    private Integer permissionId;
}