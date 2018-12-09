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
    <link href="<c:url value="/static/css/list.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/fileList.js"/>"></script>
</head>
<body>
<div class="panel" style="width: 87%;margin: 10px;display: inline-block;">
    <div class="content">
        <ul id="list" class="list">
        </ul>
    </div>
    <div id="mypage"></div>
</div>
</body>
</html>