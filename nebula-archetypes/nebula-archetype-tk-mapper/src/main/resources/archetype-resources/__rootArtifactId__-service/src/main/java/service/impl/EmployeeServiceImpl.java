package ${package}.service.impl;

import io.nebula.kernel.service.impl.TkMapperServiceImpl;
import ${package}.model.Employee;
import ${package}.model.EmployeeExample;
import ${package}.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl extends TkMapperServiceImpl<Employee, EmployeeExample> implements EmployeeService {

}


