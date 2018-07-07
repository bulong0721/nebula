package com.rhea.order.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(name = "order")
public class Order {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;
}