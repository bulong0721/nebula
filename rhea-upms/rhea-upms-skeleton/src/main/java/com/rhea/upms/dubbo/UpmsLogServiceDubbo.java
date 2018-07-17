package com.rhea.upms.dubbo;

import com.rhea.upms.api.UpmsLogService;
import com.rhea.upms.service.impl.UpmsLogServiceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
* Skeleton代码生成器
*
* @author skeleton-generator on 2018-7-17
*/
@Component
@SofaService(bindings = {@SofaServiceBinding(bindingType = "dubbo")})
public class UpmsLogServiceDubbo extends UpmsLogServiceImpl implements UpmsLogService {

}
