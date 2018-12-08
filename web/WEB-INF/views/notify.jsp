<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/12/5
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Notify</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/notify.css"/>">
</head>

<body>
<div class="content">
    <div class="menu" style="float: left; width: 200px;">
        <ul class="layui-nav layui-nav-tree nav">
            <li class="layui-nav-item layui-this"><a href="">系统通知</a></li>
            <li class="layui-nav-item"><a href="">朋友私信</a></li>
        </ul>
    </div>
    <div style="float: left;">
        <div class="notify"></div>
    </div>
</div>
<script src="<c:url value="/static/js/axios.js"/>"></script>
<script src="<c:url value="/static/layui/layui.js"/>"></script>
<script type="module">
    layui.use(['element', 'layer', 'form'], function () {
        var element = layui.element, layer = layui.layer;
    });

    import Proline from "/static/js/proline.js";

    let proline = new Proline();
    proline.init({
        el: '.notify',
        course_id: '1',
        user_id: '1',
        path: window.location.pathname,
    })
</script>
</body>
</html>
