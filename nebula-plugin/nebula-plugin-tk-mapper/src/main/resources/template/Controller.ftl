<#assign ctime = .now>
<#assign domain = tableClass.shortClassName>
package ${context}.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.github.pagehelper.PageInfo;
import BusinessException;
import FluentException;
import io.nebula.kernel.exchange.*;
import ${context}.model.${domain};
import ${context}.model.${domain}Example;
import BaseController;
import ${context}.service.${domain}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author nebulaGenerator controller
 * @version V1.0
 * @date ${ctime?date}
 */
@Slf4j
@RestController
@RequestMapping("/${domain?lower_case}")
public class ${domain}Controller extends BaseController {

    @Autowired
    private ${domain}Service ${domain?lower_case}Service;

    @PostMapping("/page")
    public PageableEntity<${domain}> page(@RequestBody PageRequest<${domain}> request) {
        ${domain}Example example = new ${domain}Example();
        PageInfo<${domain}> pageInfo = ${domain?lower_case}Service.pageByExample(example, request);
        return TkToolkit.build(pageInfo);
    }

    @PostMapping("/add")
    public ResponseEntity<${domain}> add(@RequestBody ${domain} ${domain?lower_case}) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(${domain?lower_case})
                        .doValidate()
                        .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        if (!${domain?lower_case}Service.insertUseGeneratedKeys(${domain?lower_case})) {
            throw new BusinessException("实体创建失败");
        }
        return StatusCode.OK.build(${domain?lower_case});
    }

    @PostMapping("/update")
    public ResponseEntity<${domain}> update(@RequestBody ${domain} ${domain?lower_case}) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(${domain?lower_case})
                        .doValidate()
                        .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
        throw new FluentException(cr.getErrors());
        }
        if (!${domain?lower_case}Service.updateByPKSelective(${domain?lower_case})) {
            throw new BusinessException("实体更新失败");
        }
        return StatusCode.OK.build(${domain?lower_case});
    }

    @PostMapping("/remove")
    public ResponseEntity<${domain}> remove(@RequestBody ${domain} ${domain?lower_case}) {
        if (!${domain?lower_case}Service.deleteByPK(${domain?lower_case})) {
            throw new BusinessException("实体删除失败");
        }
        return StatusCode.OK.build(${domain?lower_case});
    }
}
