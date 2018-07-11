package com.rhea.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.rhea.common.base.BaseServiceImpl;
import com.rhea.order.model.Order;
import com.rhea.order.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
* OrderServiceImpl
* Created by serviceImpl-generator on 2018-7-10
*/
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {


    public static void main(String[] args) {
        Example example = new Example(Order.class);
        example.and().andNotEqualTo("orderName", "123");

        String text = JSON.toJSONString(example);
        System.out.println(text);
    }
}
