package com.rhea.order.web.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.github.pagehelper.PageInfo;
import com.rhea.common.base.BaseController;
import com.rhea.order.api.OrderService;
import com.rhea.order.model.Order;
import com.rhea.order.model.OrderExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @SofaReference(binding = @SofaReferenceBinding(bindingType = "dubbo"))
    private OrderService orderService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Long id) {
        Order order = new Order();
        order.setOrderId(id);
        order.setOrderName("order_" + id);
        orderService.insertSelective(order);
        return "success";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(Long id) throws IOException {
        OrderExample example = new OrderExample();
        example.or().andOrderIdEqualTo(2L);
        PageInfo<Order> page = orderService.pageByExample(example, 0, 5);
        return JSON.json(page);
    }
}
