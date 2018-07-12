<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.dubbo;

import ${context}.api.${domain}Service;
import ${context}.service.impl.${domain}ServiceImpl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
* Skeleton代码生成器
*
* @author skeleton-generator on ${ctime?date}
*/
@Component
@SofaService(bindings = {@SofaServiceBinding(bindingType = "dubbo")})
public class ${domain}ServiceDubbo extends ${domain}ServiceImpl implements ${domain}Service {

}