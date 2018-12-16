<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/12/1
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>基于开放云储存的作业管理系统</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/static/favicon.ico"/>"/>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/course.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/elementui/theme-chalk/index.css"/>">
    <script>
        const user = JSON.parse('${user}');
        const path = '${pageContext.request.contextPath}';
    </script>
    <style>
        body {
            background-color: #fff;
        }

        .headTitle {
            font-size: 21px;
            font-weight: 500;
            margin: 20px 10px;
            line-height: 30px;
            padding: 5px 15px;
            text-align: left;
        }
    </style>
</head>
<jsp:include page="head.jsp"/>
<body>
<div id="app" style="margin-left: 2%;">
    <el-container>
        <el-main>
            <home></home>
        </el-main>
    </el-container>
</div>

<script type="text/javascript" src="<c:url value="/static/js/axios.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/vue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/elementui/index.js"/>"></script>
<script src="<c:url value="/static/js/home-main.js"/>" type="module"></script>

</body>

</html>
