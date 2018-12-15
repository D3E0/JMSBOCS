<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/11/24
  Time: 11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
    <div class="panel-title">最近作业</div>
    <div class="op">
        <div class="searchdiv">
            <input type="text" id="search" placeholder="keyword">
            <i class=" fa-search fa fa-fw"></i>
        </div>
        &nbsp;&nbsp;
        <a href="<c:url value="/addJob"/>">
            <button class="layui-btn additem">
                <a class="fa fa-plus fa-fw" style="color: #FFFFFF"></a>&nbsp;&nbsp;添加作业
            </button>
        </a>
    </div>
    <div class="content">
        <ul class="list" id="list">
        </ul>
    </div>
    <div id="mypage"></div>
</div>
<script src="<c:url value="/static/js/joblist.js"/>">

</script>
<script src="<c:url value="/static/js/Date.js"/>"></script>
</body>
</html>