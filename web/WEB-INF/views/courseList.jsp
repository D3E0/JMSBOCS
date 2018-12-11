<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/11/24
  Time: 17:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/list.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel">
    <div class="panel-title">已选课程</div>
    <div class="op layui-show-lg-block">
        <div class="searchdiv">
            <input type="text" id="search" placeholder="keyword">
            <i class=" fa-search fa fa-fw"></i>
        </div>
        &nbsp;&nbsp;
        <button class="layui-btn additem" id="addjob">
            <a class="fa fa-plus fa-fw" style="color: #FFFFFF"></a>&nbsp;&nbsp;添加作业
        </button>
    </div>
    <div class="content">
        <ul id="list" class="list">
        </ul>
    </div>
    <div id="mypage"></div>
</div>
<script src="<c:url value="/static/js/courselist.js"/>">
</script>
<script>
    layui.use('laypage', function () {
        var laypage = layui.laypage, $ = layui.$;
    });
</script>
</body>
</html>