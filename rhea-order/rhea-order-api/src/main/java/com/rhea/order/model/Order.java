package com.rhea.order.model;

import com.rhea.common.base.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "order")
public class Order extends BaseEntity {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;
}