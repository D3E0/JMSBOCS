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
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<div class="navbar">
    <ul class="layui-nav nav" lay-filter="">
        <li class="logo">基于开放云储存的作业提交管理系统</li>
        <li id="homebtn" class="layui-nav-item layui-this"><a class="" href="javascript:;"><i class="fa fa-home fa-fw" ></i>&nbsp;首页</a></li>
        <li id="coursebtn" class="layui-nav-item"><a href="javascript:;"><i class="fa fa-book fa-fw" ></i>&nbsp;课程</a></li>
        <li id="jobbtn" class="layui-nav-item"><a href="javascript:;"><i class="fa fa-tasks fa-fw" ></i>&nbsp;作业</a></li>
        <li class="layui-nav-item" style="float: right;margin-right: 1%;">
            <a href="javascript:;"><i class="fa fa-user fa-fw" ></i>用户<i class="layui-icon layui-icon-triangle-d"></i></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="">个人信息</a></dd>
                <dd><a href="">安全设置</a></dd>
                <dd><a href="">退出登录</a></dd>
            </dl>
        </li>
    </ul>
</div>
<div class="contentapp">
    <iframe src="/main" id="myiframe" width="100%" scrolling="no" frameborder="0" onload="setIframeHeight(this)">
    </iframe>
    <script>
        layui.use('element','layer', function(){
            element = layui.element;
            layer = layui.layer;
        });
        $("#jobbtn").click(function () {
            $("#myiframe").attr("src","/joblist");
        });
        $("#coursebtn").click(function () {
            $("#myiframe").attr("src","/courselist");
        });
        $("#homebtn").click(function () {
            $("#myiframe").attr("src","/main");
        });
        var test;
        function setIframeHeight(iframe) {
            if(!test)
                test=iframe;
            else
                iframe=test;
            if (iframe) {
                iframe.height=0;
                var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                if (iframeWin.document.body) {
                    iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                }
            }
        }
    </script>
</div>
</body>
</html>
