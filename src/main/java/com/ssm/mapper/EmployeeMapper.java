package com.ssm.mapper;

import com.ssm.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    //查询所有的员工信息
    List<Employee> getAllEmployee();

    Employee getEmployeeById(@Param("id") Integer id);

    void addEmployee(Employee employee);

    void saveEmployee(Employee employee);

    void deleteEmployee(@Param("id") Integer id);
}
