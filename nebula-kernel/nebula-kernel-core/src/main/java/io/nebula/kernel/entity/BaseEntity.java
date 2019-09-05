package io.nebula.kernel.entity;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/10/15
 */
public abstract class BaseEntity implements Serializable {

    /**
     * 主键
     *
     * @return
     */
    public Object getId() {
        return null;
    }

    /**
     * 是否为新建实体
     *
     * @return
     */
    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    /**
     * 创建时间
     *
     * @param created
     */
    public void setCreated(Date created) {
        // do nothing
    }

    /**
     * 创建人
     *
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        // do nothing
    }

    /**
     * 更新时间
     *
     * @param updated
     */
    public void setUpdated(Date updated) {
        // do nothing
    }

    /**
     * 更新人
     *
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        // do nothing
    }
}
