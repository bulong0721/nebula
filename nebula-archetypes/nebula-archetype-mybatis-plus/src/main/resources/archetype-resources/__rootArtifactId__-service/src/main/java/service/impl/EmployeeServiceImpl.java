package ${package}.service.impl;

import io.nebula.kernel.service.impl.MybatisPlusServiceImpl;
import ${package}.model.Employee;
import ${package}.mapper.EmployeeMapper;
import ${package}.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl extends MybatisPlusServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
