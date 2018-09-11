package com.rhea.messaging.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private String orderNo;
}
