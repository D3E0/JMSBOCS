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
    <script src="<c:url value="/static/js/fileList.js"/>"></script>
</head>
<body>
<div class="panel">
    <div class="content">
        <ul id="list" class="list">
        </ul>
    </div>
</div>
<div id="mypage"></div>
<script>
        $(document).ready(function(){
            window.parent.setIframeHeight();
        });
</script>
</body>
</html>