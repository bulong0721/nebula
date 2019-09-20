package ${package}.controller;

import io.nebula.kernel.exchange.*;
import lombok.extern.slf4j.Slf4j;
import ${package}.model.Employee;
{package}.service.EmployeeService;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.nebula.kernel.exception.BusinessException;
import io.nebula.kernel.exception.FluentException;
import io.nebula.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController extends BaseController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/page")
    public PageableEntity<Employee> page(@RequestBody PageRequest<Employee> request) {
        IPage pager = MpToolkit.toPage(request);
        QueryWrapper<Employee> wrapper = MpToolkit.queryWrapper(request);
        IPage<Employee> result = employeeService.page(pager, wrapper);
        return MpToolkit.build(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(employee)
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        if (!employeeService.saveOrUpdate(employee)) {
            throw new BusinessException("实体创建失败");
        }
        return StatusCode.OK.build(employee);
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(employee)
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        if (!employeeService.saveOrUpdate(employee)) {
            throw new BusinessException("实体更新失败");
        }
        return StatusCode.OK.build(employee);
    }

    @PostMapping("/remove")
    public ResponseEntity<Employee> remove(@RequestBody Employee employee) {
        if (!employeeService.remove(MpToolkit.updateWrapper(employee))) {
            throw new BusinessException("实体删除失败");
        }
        return StatusCode.OK.build(employee);
    }
}
