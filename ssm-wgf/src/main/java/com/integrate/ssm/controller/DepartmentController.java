package com.integrate.ssm.controller;

import com.integrate.ssm.model.Department;
import com.integrate.ssm.model.Msg;
import com.integrate.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by GuofeiWu on 2017/8/23.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //@RequestMapping(value = "/depts")
    @GetMapping(value = "/depts")
    @ResponseBody
    public Msg getAllDepts(){
        List<Department> depts =  departmentService.getAllDepts();
        return Msg.success().add("depts",depts);
    }
}
