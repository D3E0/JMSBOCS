<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/12/9
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/list.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/record.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel">
    <div class="panel-title">作业提交记录</div>
    <div class="op">
        <%--<button id="downloadAll" class="layui-btn">点击下载所有文件</button>--%>
        <span class="jobInfo">提交情况：${already}/${need}</span>
        <div class="searchdiv">
            <input type="text" id="search" placeholder="keyword">
            <i class=" fa-search fa fa-fw"></i>
        </div>
        &nbsp;&nbsp;
    </div>
    <input id="jobId" hidden value="${jobId}">
    <div class="content">
        <table id="demo" lay-filter="record"></table>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm detailbtn" lay-event="detail">查看提交文件</a>
</script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button id="downloadAll" class="layui-btn" lay-event="downloadAll">点击下载所有文件</button>
    </div>
</script>
<script  src="<c:url value="/static/js/jobSubmitRecord.js"/>">
</script>
</body>
</html>
