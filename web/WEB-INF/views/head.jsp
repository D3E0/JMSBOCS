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
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <style>
        body {
            background-color: #eeeeee;
            color: #495060;
            font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, \\5FAE\8F6F\96C5\9ED1, Arial, sans-serif;
        }

        .nav {
            background: #FFFFFF;
        }

        .layui-nav .layui-nav-item a {
            color: #000;
        }

        .layui-nav .layui-nav-item a:hover, .layui-nav .layui-this a {
            color: #2d8cf0;
        }

        .layui-nav .layui-this:after, .layui-nav-bar {
            background-color: #2d8cf0;
        }

        .navbar {
            box-shadow: 0 1px 1px 0 rgba(0, 0, 0, .1);
        }

        .logo {
            margin-left: 2%;
            margin-right: 2%;
            font-size: 20px;
            float: left;
            line-height: 60px;
            color: #000000;
        }

        .fa-fw {
            margin: 2px;
        }
    </style>
</head>
<body>
<div class="navbar">
    <ul class="layui-nav nav">
        <li class="logo">基于开放云储存的作业管理系统</li>
        <li class="layui-nav-item layui-this"><a class="" href=""><i class="fa fa-home fa-fw"></i>&nbsp;首页</a></li>
        <li class="layui-nav-item"><a href=""><i class="fa fa-book fa-fw"></i>&nbsp;课程</a></li>
        <li class="layui-nav-item"><a href=""><i class="fa fa-tasks fa-fw"></i>&nbsp;作业</a></li>
        <li class="layui-nav-item"><a href=""><i class="fa fa-mortar-board fa-fw"></i>&nbsp;讨论</a></li>
        <li class="layui-nav-item" style="float: right;margin-right: 1%;">
            <a href="javascript:"><i class="fa fa-user fa-fw"></i>用户<i class="fa fa-angle-down fa-fw"></i></a>
            <dl class="layui-nav-child">
                <dd><a href="">个人信息</a></dd>
                <dd><a href="">安全设置</a></dd>
                <dd><a href="">退出登录</a></dd>
            </dl>
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
