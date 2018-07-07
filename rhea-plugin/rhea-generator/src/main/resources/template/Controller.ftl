package ${package};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * ${modelname}controller
 * Created by controller-generator on ${ctime}.
 */
@Controller
@RequestMapping("/manage")
@Api(value = "${modelname}控制器", description = "${modelname}管理")
public class ${model}Controller extends BaseController {

}
