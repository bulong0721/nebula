<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * controller
 * Created by controller-generator on ${ctime?date}
 */
@Slf4j
@RestController
@RequestMapping("/${domain}")
public class ${domain}Controller extends BaseController {

}
