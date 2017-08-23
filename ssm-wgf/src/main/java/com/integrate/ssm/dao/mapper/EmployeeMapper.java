package com.integrate.ssm.dao.mapper;

import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.EmployeeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeMapper {
    long countByExample(EmployeeCriteria example);

    int deleteByExample(EmployeeCriteria example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeCriteria example);

    Employee selectByPrimaryKey(Integer empId);

    List<Employee> selectByExampleWithDept(EmployeeCriteria example);

    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeCriteria example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}