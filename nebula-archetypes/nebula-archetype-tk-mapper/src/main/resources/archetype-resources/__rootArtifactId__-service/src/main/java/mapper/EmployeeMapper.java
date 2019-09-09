package ${package}.mapper;

import io.nebula.kernel.mapper.TkBaseMapper;
import ${package}.model.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends TkBaseMapper<Employee> {
}
