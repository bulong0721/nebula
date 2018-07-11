package com.rhea.order.model;

import javax.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Table(name = "order")
public class Order implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_name")
    private String orderName;
}