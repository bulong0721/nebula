package com.rhea.order.service.impl;

import com.rhea.common.base.BaseServiceImpl;
import com.rhea.order.model.Order;
import com.rhea.order.model.OrderExample;
import com.rhea.order.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* OrderServiceImpl
* Created by serviceImpl-generator on 2018-7-12
*/
@Slf4j
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderExample> implements OrderService {

}
