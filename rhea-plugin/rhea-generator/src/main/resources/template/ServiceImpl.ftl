<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.service.impl;

import com.rhea.common.base.BaseServiceImpl;
import ${context}.model.${domain};
import ${context}.model.${domain}Example;
import ${context}.api.${domain}Service;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* ${domain}ServiceImpl
* Created by serviceImpl-generator on ${ctime?date}
*/
@Slf4j
@Service
public class ${domain}ServiceImpl extends BaseServiceImpl<${domain}, ${domain}Example> implements ${domain}Service {

}