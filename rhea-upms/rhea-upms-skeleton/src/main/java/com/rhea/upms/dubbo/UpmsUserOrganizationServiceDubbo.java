package com.rhea.upms.dubbo;

import com.rhea.upms.api.UpmsUserOrganizationService;
import com.rhea.upms.service.impl.UpmsUserOrganizationServiceImpl;

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
public class UpmsUserOrganizationServiceDubbo extends UpmsUserOrganizationServiceImpl implements UpmsUserOrganizationService {

}
