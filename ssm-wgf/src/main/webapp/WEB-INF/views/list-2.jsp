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
    <title>员工列表</title>



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
                    <table class="table table-hover">
                        <tr>
                            <th>#</th>
                            <th>empName</th>
                            <th>gender</th>
                            <th>email</th>
                            <th>deptName</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${pageInfo.list}" var="emp">
                            <tr>
                                <td>${emp.empId}</td>
                                <td>${emp.empName}</td>
                                <td>${emp.gender == "M"?"男":"女"}</td>
                                <td>${emp.email}</td>
                                <td>${emp.department.deptName}</td>
                                <td>
                                    <button class="btn btn-primary btn-sm">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>新增
                                    </button>
                                    <button class="btn btn-danger btn-sm">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>      删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
             <%--页数栏--%>
            <div class="row">
                <div class="col-md-6">
                    当前在第 ${pageInfo.pageNum} 页,总共 ${pageInfo.pages} 页,一共有 ${pageInfo.total} 条数据
                </div>
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li><a href="${BASE_PATH}/emps?pn=1">首页</a></li>
                            <c:if test="${pageInfo.hasPreviousPage}">
                                <li class="">
                                    <a href="${BASE_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                                <c:if test="${page_num == pageInfo.pageNum}">
                                    <li class="active"><a href="${BASE_PATH}/emps?pn=${page_num}">${page_num}</a></li>
                                </c:if>
                                <c:if test="${page_num != pageInfo.pageNum}">
                                    <li><a href="${BASE_PATH}/emps?pn=${page_num}">${page_num}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${pageInfo.hasNextPage}">
                                <li>
                                    <a href="${BASE_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <li><a href="${BASE_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
                        </ul>
                    </nav>
                </div>

            </div>
        </div>
</body>
</html>
