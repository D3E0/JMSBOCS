<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/11/11
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/home.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
</head>
<body>
<div class="navbar">
    <ul class="layui-nav">
        <li class="logo">基于开放云储存的作业管理系统</li>
        <li class="layui-nav-item layui-this">
            <a href="javascript:f('/main')">
                <i class="fa fa-home fa-fw"></i>&nbsp;首页
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:f('/courseList')">
                <i class="fa fa-book fa-fw"></i>&nbsp;课程
            </a>
        </li>
        <li class="layui-nav-item">
            <a href="javascript:f('/jobList')">
                <i class="fa fa-tasks fa-fw"></i>&nbsp;作业
            </a>
        </li>

        <li class="layui-nav-item  layout-right">
            <a href="javascript:">
                <i class="fa fa-user fa-fw"></i>用户
                <i class="layui-icon layui-icon-triangle-d"></i>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:f('/user')">个人信息</a></dd>
                <dd><a href="">退出登录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item  layout-right">
            <a href="javascript:f('/profile')">
                <i class="layui-icon layui-icon-notice"></i>
            </a>
        </li>
    </ul>
</div>
<div class="contentapp">
    <iframe src="<c:url value="/profile"/>" id="myiframe" width="100%" scrolling="no" frameborder="0"
            onload="setIframeHeight(this)">
    </iframe>
</div>
<script>
    layui.use('element', function () {
        let element = layui.element;
    });
    let contextPath = '${pageContext.request.contextPath}';

    let _frame = document.getElementById("myiframe");

    function f(url) {
        _frame.setAttribute("src", contextPath + url);
    }

    let test;

    window.onresize = function () {
        setIframeHeight(this);
    };

    function setIframeHeight(iframe) {
        if (!test)
            test = iframe;
        else
            iframe = test;
        if (iframe) {
            iframe.height = 0;
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    }
</script>
</body>
</html>
