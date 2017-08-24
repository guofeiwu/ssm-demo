package com.integrate.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.integrate.ssm.model.Employee;
import com.integrate.ssm.model.Msg;
import com.integrate.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @GetMapping(value = "/getEmp/{id}")
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }











    @ResponseBody
    @PostMapping(value = "/checkUser")
    public Msg checkUser(@RequestParam("empName") String empName) {
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!empName.matches(regx)) {
            return Msg.failed().add("val_msg", "用户名为6-16位的英文和数字字符的组合或中文为2-5位");
        }
        boolean b = employeeService.checkUserName(empName);
        if (b) {
            return Msg.success();
        }
        return Msg.failed().add("val_msg", "用户名已经存在");
    }


    @RequestMapping(value = "/saveEmp")
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee , BindingResult result) {
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            Map<String,Object> errors = new HashMap<String,Object>();
            for(FieldError fieldError: fieldErrors){
                System.out.println("fieldId:"+fieldError.getField());
                System.out.println("fieldMessage"+fieldError.getDefaultMessage());
                errors.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.failed().add("saveMsg",errors);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }


    @RequestMapping(value = "/emps")
    @ResponseBody
    public Msg getAllEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        //引入pageHelper,成为一个分页查询
        //在查询之前调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();

        //使用pageInfo包装查询的结果，只需将pageInfo交给页面就可以
        //封装了详细的分页信息，包括所有的查询结果，出入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);


        for(Employee e:emps){
            System.out.println( "id: "+e.getEmpId());
        }

        return Msg.success().add("pageInfo", pageInfo);
    }


    //@RequestMapping(value = "/emps")
    public String getAllEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        //引入pageHelper,成为一个分页查询
        //在查询之前调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);

        List<Employee> emps = employeeService.getAll();

        //使用pageInfo包装查询的结果，只需将pageInfo交给页面就可以
        //封装了详细的分页信息，包括所有的查询结果，出入连续显示的页数
        PageInfo pageInfo = new PageInfo(emps, 5);

        model.addAttribute("pageInfo", pageInfo);

        return "list";
    }

}
