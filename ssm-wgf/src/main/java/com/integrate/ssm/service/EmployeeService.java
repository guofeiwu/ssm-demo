package com.integrate.ssm.service;

import com.integrate.ssm.dao.mapper.EmployeeMapper;
import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.EmployeeCriteria;
import com.integrate.ssm.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     *  检查用户名是否可用
     * @return  true表示可用，false表示不可用
     */
    public boolean checkUserName(String empName) {
        EmployeeCriteria criteria = new EmployeeCriteria();
        criteria.createCriteria()
                .andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(criteria);
        return count == 0;
    }


    /**
     * 根据员工id来获取员工的信息
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    /**
     * 更新员工
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除员工
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param list_ids
     */
    public void deleteBatch(List<Integer> list_ids) {
        EmployeeCriteria criteria= new EmployeeCriteria();
        criteria.createCriteria().andEmpIdIn(list_ids);
        employeeMapper.deleteByExample(criteria);
    }
}
