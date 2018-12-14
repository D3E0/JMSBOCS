<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/11/11
  Time: 13:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="<c:url value="/static/favicon.ico"/>"/>
    <link href="<c:url value="/static/css/home.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <title>基于开放云储存的作业管理系统</title>
</head>
<body>
<div class="navbar">
    <ul class="layui-nav">
        <li class="logo">基于开放云储存的作业管理系统
        </li>
        <li class="layui-nav-item">
            <a href="<c:url value="/main"/>">
                <i class="fa fa-home fa-fw"></i>&nbsp;首页
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="<c:url value="/subject"/>">
                <i class="fa fa-book fa-fw"></i>&nbsp;课程
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="<c:url value="/jobList"/>">
                <i class="fa fa-tasks fa-fw"></i>&nbsp;作业
            </a>
        </li>
        <li class="layui-nav-item  layout-right">
            <a href="javascript:">
                <i class="fa fa-user fa-fw"></i>用户
                <i class="layui-icon layui-icon-triangle-d"></i>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="<c:url value="/user"/>">个人信息</a></dd>
                <dd><a href="<c:url value="/user"/>">退出登录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item  layout-right">
            <a href="<c:url value="/user#/notify"/>">
                <i class="layui-icon layui-icon-notice"></i>
            </a>
        </li>
    </ul>
</div>
<script src="<c:url value="/static/layui/layui.js"/>"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>
