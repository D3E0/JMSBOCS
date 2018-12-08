<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/11/11
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/static/css/home.css"/>" rel="stylesheet">
</head>
<body>
<div class="navbar">
    <ul class="layui-nav">
        <li class="logo">基于开放云储存的作业管理系统</li>
        <li class="layui-nav-item layui-this">
            <a href="<c:url value="/main"/>">
                <i class="fa fa-home fa-fw"></i>&nbsp;首页
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="<c:url value="/courseList"/>">
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
                <dd><a href="javascript:f('/profile')">个人信息</a></dd>
                <dd><a href="javascript:f('/logout')">退出登录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item  layout-right">
            <a href="/notify">
                <i class="layui-icon layui-icon-notice"></i>
            </a>
        </li>
    </ul>
</div>

<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>
