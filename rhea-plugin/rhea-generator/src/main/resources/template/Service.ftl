<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.api;

import ${context}.model.${domain};
import ${context}.model.${domain}Example;

import com.rhea.common.base.BaseService;

/**
* ${domain}Service接口
* Created by service-generator on ${ctime?date}
*/
public interface ${domain}Service extends BaseService<${domain}, ${domain}Example> {

}
