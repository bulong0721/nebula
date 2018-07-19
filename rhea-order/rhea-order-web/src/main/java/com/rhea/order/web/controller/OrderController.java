package com.rhea.order.web.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageInfo;
import com.rhea.common.base.BaseController;
import com.rhea.common.exception.RheaException;
import com.rhea.common.validator.NotNullValidator;
import com.rhea.order.api.OrderService;
import com.rhea.order.model.Order;
import com.rhea.order.model.OrderExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int add(@RequestBody Order order) {
        // 订单实体校验
        ComplexResult result = FluentValidator.checkAll().failOver()
            .on(order.getOrderName(), new NotNullValidator("orderName"))
            .doValidate()
            .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            throw new RheaException();
        }
        return orderService.insertSelective(order);
    }
}
