<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <title>导航界面</title>
    <style>
        .f {
            width: 500px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>
<div class="f">
    <fieldset class="layui-elem-field">
        <legend>导航页</legend>
        <div class="layui-field-box">
            <div class="layui-btn-container">
                <a href="<c:url value="/home"/>" class="layui-btn">首页</a>
                <a href="<c:url value="/login"/>" class="layui-btn">登陆</a>
                <a href="<c:url value="/subject"/>" class="layui-btn">课程</a>
                <a href="<c:url value="/jobList"/>" class="layui-btn">作业</a>
                <a href="<c:url value="/user"/>" class="layui-btn">个人资料</a>
                <a href="<c:url value="/user#/updatePwd"/>" class="layui-btn">密码修改</a>
                <a href="<c:url value="/user#/notify"/>" class="layui-btn">消息中心</a>
                <a href="<c:url value="/user#/qiniu"/>" class="layui-btn">七牛云</a>
            </div>
        </div>
    </fieldset>
</div>
</body>
</html>
