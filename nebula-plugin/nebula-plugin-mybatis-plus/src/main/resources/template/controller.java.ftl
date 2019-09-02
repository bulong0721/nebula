package ${package.Controller};

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import BusinessException;
import FluentException;
import io.nebula.kernel.exchange.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    @Autowired
    private ${table.serviceName} ${entity?lower_case}Service;

    @PostMapping("/page")
    public PageableEntity<${entity}> page(@RequestBody PageRequest<${entity}> request) {
        IPage pager = MpToolkit.toPage(request);
        QueryWrapper<${entity}> wrapper = MpToolkit.queryWrapper(request);
        IPage<${entity}> result = ${entity?lower_case}Service.page(pager, wrapper);
        return MpToolkit.build(result);
    }

    @PostMapping("/add")
    public ResponseEntity<${entity}> add(@RequestBody ${entity} ${entity?lower_case}) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(${entity?lower_case})
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        if (!${entity?lower_case}Service.saveOrUpdate(${entity?lower_case})) {
            throw new BusinessException("实体创建失败");
        }
        return StatusCode.OK.build(${entity?lower_case});
    }

    @PostMapping("/update")
    public ResponseEntity<${entity}> update(@RequestBody ${entity} ${entity?lower_case}) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(${entity?lower_case})
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        if (!${entity?lower_case}Service.saveOrUpdate(${entity?lower_case})) {
            throw new BusinessException("实体更新失败");
        }
        return StatusCode.OK.build(${entity?lower_case});
    }

    @PostMapping("/remove")
    public ResponseEntity<${entity}> remove(@RequestBody ${entity} ${entity?lower_case}) {
        if (!${entity?lower_case}Service.remove(MpToolkit.updateWrapper(${entity?lower_case}))) {
            throw new BusinessException("实体删除失败");
        }
        return StatusCode.OK.build(${entity?lower_case});
    }
}
</#if>
