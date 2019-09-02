<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.service;

import ${context}.model.${domain};
import ${context}.model.${domain}Example;

import TkPageableService;

/**
* @author nebulaGenerator
* ${domain}Service接口
* Created by service-generator on ${ctime?date}
*/
public interface ${domain}Service extends TkPageableService<${domain}, ${domain}Example> {

}
