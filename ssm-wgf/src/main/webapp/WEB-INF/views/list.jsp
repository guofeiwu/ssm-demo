<%--
  Created by IntelliJ IDEA.
  User: guofei_wu
  Date: 2017/8/22
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        pageContext.setAttribute("BASE_PATH",request.getContextPath());
    %>
    <title>员工列表2</title>



    <!--引入jQuery-->
    <script type="text/javascript" src="${BASE_PATH}/static/js/jquery-1.12.4.min.js"> </script>
    <!-- 引入bootstarp样式 -->
    <link href="${BASE_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${BASE_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

        <div class="container" >
             <%--标题--%>
            <div class="row">
                <div class="col-md-12">
                         <h1>SSM-CRUD</h1>
                </div>
            </div>
             <%--新增，删除按钮--%>
            <div class="row">
                <div class="col-md-4 col-md-offset-8">
                    <button class="btn btn-primary">新增</button>
                    <button class="btn btn-danger">删除</button>
                </div>
            </div>
             <%--表格数据栏--%>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-hover" id="emps_table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>empName</th>
                            <th>gender</th>
                            <th>email</th>
                            <th>deptName</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>

                    </table>
                </div>
            </div>
             <%--页数栏--%>
            <div class="row">
                <div class="col-md-6">
                    当前在第  页,总共  页,一共有 条数据
                </div>
                <div class="col-md-6">

                </div>

            </div>
        </div>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url:"&{BASE_PATH}/emps",
            data:"pn=1",
            type:"get",
            success:function (result) {
                //Console.alert(result);
                //1.解析并显示员工信息
                build_emp_table(result);
                //2.解析并显示分页信息
            }
          }
        );
    });
    
    function build_emp_table(result) {
        var emps = result.extendm.pageInfo.list;
        $.each(emps,function (index,item) {
            //alert(index+"---"+item.empName);
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var genderTd = $("<td></td>").append(item.gender == "M"?"男":"女");
            var emailTd = $("<td></td>").append(item.email);
            var deptNameTd=$("<td></td>").append(item.department.deptName);

            $("<tr></tr>").append(empIdTd)
                .append(empNameTd)
                .append(genderTd)
                .append(emailTd)
                .append(deptNameTd)
                .appendTo("#emps_table tbody");
        });
    }

    function build_page_nav(result) {

    }
</script>
</body>
</html>
