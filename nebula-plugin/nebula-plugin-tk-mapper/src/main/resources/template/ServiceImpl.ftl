<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.service.impl;

import TkMapperServiceImpl;
import ${context}.model.${domain};
import ${context}.model.${domain}Example;
import ${context}.service.${domain}Service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* @author nebulaGenerator
* ${domain}ServiceImpl
* Created by serviceImpl-generator on ${ctime?date}
*/
@Slf4j
@Component
public class ${domain}ServiceImpl extends TkMapperServiceImpl<${domain}, ${domain}Example> implements ${domain}Service {

}
