<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/11/25
  Time: 15:14
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
<body style="text-align:center;background-color: #FFFFFF">
<div>
    <input id="jobId" hidden value="${jobId}">
    <input id="courseId" hidden value="${courseId}">
    <input id="studentId" hidden value="${studentId}">
    <div class="content">
        <div class="nothing layui-col-md-offset4 layui-col-md4">
            <div>
                <img src="<c:url value="/static/img/nothing.png"/>">
            </div>
            <div>
                这里空空如也
            </div>
        </div>
        <ul id="list" class="list">
        </ul>
    </div>
</div>
<script src="<c:url value="/static/js/jobFileList.js"/>">
</script>
</body>
</html>