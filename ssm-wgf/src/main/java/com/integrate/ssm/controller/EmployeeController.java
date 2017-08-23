package com.integrate.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.Msg;
import com.integrate.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/saveEmp")
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.saveEmp(employee);
        return Msg.success();
    }







    @RequestMapping(value = "/emps")
    @ResponseBody
    public Msg getAllEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        //引入pageHelper,成为一个分页查询
        //在查询之前调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();

        //使用pageInfo包装查询的结果，只需将pageInfo交给页面就可以
        //封装了详细的分页信息，包括所有的查询结果，出入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps,5);

        return Msg.success().add("pageInfo",pageInfo);
    }




    //@RequestMapping(value = "/emps")
    public String getAllEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //引入pageHelper,成为一个分页查询
        //在查询之前调用，传入页码，以及每页的大小
        PageHelper.startPage(pn,5);

        List<Employee> emps = employeeService.getAll();

        //使用pageInfo包装查询的结果，只需将pageInfo交给页面就可以
        //封装了详细的分页信息，包括所有的查询结果，出入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps,5);

        model.addAttribute("pageInfo",pageInfo);

        return "list";
    }

}
