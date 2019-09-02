package io.nebula.leaf.model;

import io.nebula.kernel.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@Table(name = "leaf_alloc")
public class LeafAlloc extends BaseEntity {
    @Id
    @Column(name = "biz_tag")
    private String bizTag;

    @Column(name = "max_id")
    private Long maxId;

    @Column(name = "step")
    private Integer step;

    @Column(name = "description")
    private String description;

    @Column(name = "update_time")
    private Date updateTime;
}
