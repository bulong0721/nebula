package ${package};

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import org.springframework.stereotype.Component;

/**
* 通用 Mapper 代码生成器
*
* @author skeleton-generator
*/
@Component
@SofaService(@SofaServiceBinding(bindingType = "dubbo")})
public class ${tableClass.shortClassName}Dubbo extends ${tableClass.shortClassName}ServiceImpl {

}