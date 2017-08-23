package com.integrate.ssm.test;


import com.integrate.ssm.dao.mapper.DepartmentMapper;
import com.integrate.ssm.dao.mapper.EmployeeMapper;
import com.integrate.ssm.model.Department;
import com.integrate.ssm.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {


    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    SqlSession sqlSession;


    @Test
    public void TestCRUD() {
//        System.out.println(departmentMapper);
//        Department department1 = new Department();
//        department1.setDeptName("开发部");
//        Department department2 = new Department();
//        department2.setDeptName("测试部");
//        departmentMapper.insertSelective(department1);
//        departmentMapper.insertSelective(department2);


        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            Employee employee = new Employee();
            employee.setEmpName(uid);
            employee.setEmail(uid + "@wgf.com");
            employee.setGender("M");
            employee.setdId(1);
            employeeMapper.insertSelective(employee);
        }
        System.out.println("批量插入完成");
    }
}
