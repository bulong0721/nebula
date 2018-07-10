<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * controller
 * Created by controller-generator on ${ctime?date}
 */
@Slf4j
@Controller
@RequestMapping("/manage")
public class ${tableClass.shortClassName}Controller extends BaseController {

}
