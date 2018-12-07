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
                <a href="<c:url value="/home"/>" class="layui-btn">主界面</a>
                <a href="<c:url value="/api/comment"/>" class="layui-btn">API 评论 新</a>
                <a href="<c:url value="/api/comments"/>" class="layui-btn">API 评论列表</a>
                <a href="<c:url value="/test"/>" class="layui-btn">API 测试</a>
            </div>
        </div>
    </fieldset>
</div>
</body>
</html>
