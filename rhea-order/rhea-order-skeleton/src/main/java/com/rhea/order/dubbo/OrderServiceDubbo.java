package com.rhea.order.dubbo;

import com.rhea.order.api.OrderService;
import com.rhea.order.service.impl.OrderServiceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
* Skeleton代码生成器
*
* @author skeleton-generator on 2018-7-12
*/
@Component
@SofaService(bindings = {@SofaServiceBinding(bindingType = "dubbo")})
public class OrderServiceDubbo extends OrderServiceImpl implements OrderService {

}
