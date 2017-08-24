<%--
  User: guofei_wu
  Date: 2017/8/22   17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        pageContext.setAttribute("BASE_PATH", request.getContextPath());
    %>
    <title>员工列表</title>


    <!--引入jQuery-->
    <script type="text/javascript" src="${BASE_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <!-- 引入bootstarp样式 -->
    <link href="${BASE_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${BASE_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>




<!-- 编辑员工 模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑员工</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="input_update_EmpName_static">empName</p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="input_update_Email" placeholder="email@wgf.com">
                            <span  class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender3" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender4" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-3">
                            <select class="form-control" name="dId" id="update_select_dept" ></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="update_employee_btn">更新</button>
            </div>
        </div>
    </div>
</div>



<!-- 添加员工 模态框 -->
<div class="modal fade" id="addEmployeeModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <div class="form-group">
                        <label for="inputEmpName" class="col-sm-2 control-label">empName</label>
                        <div class="col-sm-10">
                            <input type="text"  name="empName" class="form-control" id="inputEmpName" placeholder="empName">
                            <span  class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputEmail" class="col-sm-2 control-label">email</label>
                        <div class="col-sm-10">
                            <input type="email" name="email" class="form-control" id="inputEmail" placeholder="email@wgf.com">
                            <span  class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1" value="M" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-3">
                            <select class="form-control" name="dId"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save_employee_btn">保存</button>
            </div>
        </div>
    </div>
</div>






<div class="container">
    <%--标题--%>
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--新增，删除按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="btn_add_emp">新增</button>
            <button class="btn btn-danger" id="btn_delete_emp">删除</button>
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
                <tbody></tbody>
            </table>
        </div>
    </div>
    <%--页数栏--%>
    <div class="row">
        <div class="col-md-6" id="page_infos_area"></div>
        <div class="col-md-6" id="page_nav_area"></div>
    </div>
</div>





<script type="text/javascript">


    var employeeSize;
    $(function () {
        //第一个到第一页
        to_page(1);
    });

    //显示员工信息
    function build_emp_table(result) {
        $("#emps_table tbody").empty();
        var emps = result.extendm.pageInfo.list;
        $.each(emps, function (index, item) {
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var genderTd = $("<td></td>").append(item.gender == "M" ? "男" : "女");
            var emailTd = $("<td></td>").append(item.email);
            var deptNameTd = $("<td></td>").append(item.department.deptName);


            var editBtn = $("<button></button>").addClass("btn btn-info btn-sm edit_update")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("编辑");
            //给编辑按钮添加上employee的id值
            editBtn.attr("emp_id",item.empId);
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm edit_delete")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>").append(empIdTd)
                .append(empNameTd)
                .append(genderTd)
                .append(emailTd)
                .append(deptNameTd)
                .append(btnTd)
                .appendTo("#emps_table tbody");
        });
    }


    //跳转到哪一页
    function to_page(pn) {
        $.ajax({
                url: "${BASE_PATH}/emps",
                data: "pn=" + pn,
                type: "GET",
                success: function (result) {
                    //1.解析并显示员工信息
                    build_emp_table(result);
                    //2.解析并显示分页信息
                    build_page_info(result);
                    //3.解析显示分页条信息
                    build_page_nav(result);
                }
            }
        );
    }
    //显示分页信息
    function build_page_info(result) {
        $("#page_infos_area").empty();

        var currentNum = result.extendm.pageInfo.pageNum;
        var pages = result.extendm.pageInfo.pages;
        employeeSize = result.extendm.pageInfo.total;

        //  当前在第   页,总共   页,一共有  条数据
        $("#page_infos_area").append("当前在第 " + currentNum + " 页,总共 " + pages + " 页，一共有 " + employeeSize + " 条数据");

    }

    //显示分页条信息
    function build_page_nav(result) {
        //每次跳转页面都将上一次请求的清空
        $("#page_nav_area").empty();

        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("首页"));
        var prePageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("&laquo;"));
        var nextPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").attr("href", "#").append("末页"));


        if (result.extendm.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //到首页
            firstPageLi.click(function () {
                to_page(1);
            });
            //上一页
            prePageLi.click(function () {
                to_page(result.extendm.pageInfo.pageNum - 1);
            });
        }

        ul.append(firstPageLi).append(prePageLi);
        $.each(result.extendm.pageInfo.navigatepageNums, function (index, item) {

            var itemLi = $("<li></li>").append($("<a></a>").attr("href", "#").append(item));
            if (result.extendm.pageInfo.pageNum == item) {
                itemLi.addClass("active");
            }
            ul.append(itemLi);
            itemLi.click(function () {
                to_page(item);
            });
        });

        if (result.extendm.pageInfo.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //下一页
            nextPageLi.click(function () {
                to_page(result.extendm.pageInfo.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.extendm.pageInfo.pages);
            });
        }
        ul.append(nextPageLi).append(lastPageLi);
        var nav = $("<nav></nav>").append(ul);
        nav.appendTo("#page_nav_area");
    }

    //完全重置form表单
    function reset_form(ele) {
        $(ele)[0].reset();
        $(ele).find("*").removeClass("has-success has-error");
        $(ele).find(".help-block").text("");
    }
    //新增按钮添加点击事件
    $("#btn_add_emp").click(function () {


        //$("#addEmployeeModal form")[0].reset();
        //完全重置form表单
        reset_form("#addEmployeeModal form");
        //获取部门信息
        getDepts("#addEmployeeModal select");
        //通过js的方式打开模态框
        $("#addEmployeeModal").modal({
            backdrop:"static",
            keyboard:true
        });

    });
    //获取部门信息
    function getDepts(ele) {
        //将部门信息重置
        $(ele).empty();
        $.ajax({
            url:"${BASE_PATH}/depts",
            type:"GET",
            success:function (result) {
                $.each(result.extendm.depts,function (index, dept) {
                    $(ele).append($("<option></option>") .attr("value",dept.deptId)
                        .append(dept.deptName));
//                   var optionEle = $("<option></option>").attr("value",dept.deptId).append(dept.deptName);
//                    $("#addEmployeeModal select").append(optionEle);
                })
            }
        });
    }
    
    //验证表单数据
    function validate_form_data() {
        var empName = $("#inputEmpName").val();
        var regEmpName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;//英文字符6-16，中文2-5位
        if(!regEmpName.test(empName)){
            show_validate_msg("#inputEmpName","error","用户名为6-16位的英文和数字字符的组合或中文为2-5位");
            return false;
        }
        show_validate_msg("#inputEmpName","success","");

        var email = $("#inputEmail").val();
        var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
        if(!regEmail.test(email)){
            show_validate_msg("#inputEmail","error","邮箱格式不正确");
            return false;
        }
        show_validate_msg("#inputEmail","error"," ");
        return true;
    }

    //校验提示信息
    function show_validate_msg(element,status,msg) {
        //先清除上一次所增加的属性
        $(element).parent().removeClass("has-success has-error");
        $(element).next("span").text("");

        if(status=="success"){
            $(element).parent().addClass("has-success");
            $(element).next("span").text(msg);
        }else if(status == "error"){
            $(element).parent().addClass("has-error");
            $(element).next("span").text(msg);
        }
    }


    //保存员工信息
    $("#save_employee_btn").click(function () {

        //先进行数据的检验，然后在发ajax请求到服务器。
        if(!validate_form_data()){
            return false;
        }

        // 服务端校验用户名是否可用
        if($("#inputEmpName").attr("empName-invalidate") == "error"){
            return false;
        }

       $.ajax({
            url:"${BASE_PATH}/saveEmp",
            type:"POST",
            data:$("#addEmployeeModal form").serialize(),
            success:function (result) {
                //alert(result.hintMsg);
                //进行后端校验
                if (result.code == 100){
                    //1.关闭模态框
                    $("#addEmployeeModal").modal('hide');
                    //2.跳转到最后一页，显示刚添加的数据
                    to_page(employeeSize);
                }else{
                    if(undefined !=result.extendm.saveMsg.email) {
                        show_validate_msg("#inputEmail", "error", result.extendm.saveMsg.email);
                    }
                    if(undefined!=result.extendm.saveMsg.empName){
                        show_calidate_msg("#inputEmpName","error",result.extendm.saveMsg.empName);
                    }
                }
            }
        })
    });

    //监听用户名输入框的文本变化
    $("#inputEmpName").change(function () {
        //alert(this.value);
        var empName = this.value;
        $.ajax({
            url:"${BASE_PATH}/checkUser",
            type:"POST",
            data:"empName="+empName,
            success:function (result) {
                if(result.code == 100){
                    show_validate_msg("#inputEmpName","success","用户名可用");
                    $("#inputEmpName").attr("empName-invalidate","success");
                }else {
                    show_validate_msg("#inputEmpName","error",result.extendm.val_msg);
                    $("#inputEmpName").attr("empName-invalidate","error");
                }
            }
        });
    });

    //1.可以在创建的时候给按钮添加点击事件  2.或者使用jquery的on方法
    $("#emps_table tbody").on("click",".edit_update",function () {
            //alert("edit ... ");
        $("#empUpdateModal").modal({
            backdrop:"static",
            keyboard:true
        });

        //显示部门信息
        getDepts("#empUpdateModal select");

        //显示员工信息
        var emp_id = $(this).attr("emp_id");

        get_emp(emp_id);
    });

    //更新显示员工信息
    function get_emp(emp_id){
        $.ajax({
            url:"${BASE_PATH}/getEmp/"+emp_id,
            type:"GET",
            success:function (result) {
                //console.log(result);
                var emp = result.extendm.emp;
                $("#input_update_EmpName_static").text(emp.empName);
                $("#input_update_Email").val(emp.email);
                $("#empUpdateModal input[name=gender]").val([emp.gender]);


                $("#empUpdateModal select").val([emp.dId]);

                //$("#update_select_dept option[value='"+ emp.dId+ "']").attr("selected", true);


                console.log(emp.dId);
            }
        });
    }
    
</script>
</body>
</html>
