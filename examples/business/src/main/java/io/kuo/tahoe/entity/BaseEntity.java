package io.kuo.tahoe.entity;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by nikog on 2/5/2015.
 */
@MappedSuperclass
public class BaseEntity {
    private Date createdWhen;
    private String createdBy;

    private Date updatedWhen;
    private String updatedBy;

    public Date getCreatedWhen() {
        return createdWhen;
    }

    public void setCreatedWhen(Date createdWhen) {
        this.createdWhen = createdWhen;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedWhen() {
        return updatedWhen;
    }

    public void setUpdatedWhen(Date updatedWhen) {
        this.updatedWhen = updatedWhen;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
