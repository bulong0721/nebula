package com.rhea.upms.model;

import com.rhea.common.base.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "upms_user_permission")
public class UpmsUserPermission extends BaseEntity implements Serializable {
    /**
     * 编号
     */
    @Id
    @Column(name = "user_permission_id")
    private Integer userPermissionId;

    /**
     * 用户编号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 权限编号
     */
    @Column(name = "permission_id")
    private Integer permissionId;

    /**
     * 权限类型(-1:减权限,1:增权限)
     */
    @Column(name = "type")
    private Byte type;
}