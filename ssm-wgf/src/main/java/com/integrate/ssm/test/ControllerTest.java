package com.integrate.ssm.test;


import com.github.pagehelper.PageInfo;
import com.integrate.ssm.dao.mapper.DepartmentMapper;
import com.integrate.ssm.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:G:\\WuIdeaU\\ssm-wgf\\src\\main\\webapp\\WEB-INF\\dispatcherServlet-servlet.xml"})
public class ControllerTest {

    @Autowired
    WebApplicationContext context;


    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","1")).andReturn();
        PageInfo pageInfo = (PageInfo) result.getRequest().getAttribute("pageInfo");
        System.out.println("当前页码:"+pageInfo.getPageNum());
        System.out.println("总页码:"+pageInfo.getPages());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("pageSize:"+pageInfo.getPageSize());

        System.out.println("在页面显示需要连续显示的页码数");
        int[] pages = pageInfo.getNavigatepageNums();
        for (Integer i:pages){
            System.out.print("  "+i);
        }
        //获取员工的数据
        List<Employee> emps = pageInfo.getList();
        for (Employee emp:emps)
        {
            System.out.println("ID:"+emp.getEmpId()+",name====>"+emp.getEmpName());
        }
    }

}
