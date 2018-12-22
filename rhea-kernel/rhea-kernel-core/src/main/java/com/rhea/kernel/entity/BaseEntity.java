package com.rhea.kernel.entity;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xubulong
 * @version V1.0
 */
public abstract class BaseEntity implements Serializable {

    public Object getId() {
        return null;
    }

    @Transient
    public boolean isNew() {
        return getId() == null;
    }

    public void setCreated(Date created) {
    }

    public void setCreatedBy(String createdBy) {
    }

    public void setUpdated(Date updated) {
    }

    public void setUpdatedBy(String updatedBy) {
    }
}
