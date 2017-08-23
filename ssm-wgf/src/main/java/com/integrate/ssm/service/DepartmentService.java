package com.integrate.ssm.service;

import com.integrate.ssm.dao.mapper.DepartmentMapper;
import com.integrate.ssm.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GuofeiWu on 2017/8/23.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepts(){
        return departmentMapper.selectByExample(null);
    }
}
