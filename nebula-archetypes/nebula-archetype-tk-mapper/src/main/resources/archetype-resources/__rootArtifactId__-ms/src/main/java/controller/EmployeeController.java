package ${package}.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import io.nebula.kernel.exception.FluentException;
import io.nebula.kernel.exchange.ResponseEntity;
import io.nebula.kernel.exchange.StatusCode;
import io.nebula.kernel.validator.NotNullValidator;
import io.nebula.web.controller.BaseController;
import ${package}.model.Employee;
import ${package}.model.EmployeeExample;
import ${package}.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ResponseEntity<List<Employee>> query() {
        EmployeeExample example = new EmployeeExample();
        return StatusCode.OK.build(employeeService.listByExample(example));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(employee)
                .on(employee.getName(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        return StatusCode.OK.build(employeeService.insert(employee));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        // 实体校验
        ComplexResult cr = checkAllWithJsr303(employee)
                .on(employee.getName(), new NotNullValidator("姓名"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        // 验证失败
        if (!cr.isSuccess()) {
            throw new FluentException(cr.getErrors());
        }
        return StatusCode.OK.build(employeeService.updateByPK(employee));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<Employee> remove(@RequestBody Employee employee) {
        return StatusCode.OK.build(employeeService.deleteByPK(employee));
    }
}
