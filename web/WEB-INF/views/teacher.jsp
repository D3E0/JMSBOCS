<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/11/24
  Time: 17:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/teacher.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<div class="content">
    <div class="photo">
        <img src="<c:url value="/static/img/teacher.png"/>">
    </div>
    <div class="name">
        戴霸天
    </div>
    <ul class="list">
        <li>
            <div class="li_item">
                <i class="fa fa-home fa-fw" ></i>&nbsp;办公室:c1-509
            </div>
        </li>
        <li>
            <div class="li_item">
                <i class="fa fa-phone fa-fw" ></i>&nbsp;联系方式:13758192664
            </div>
        </li>
        <li>
            <div class="li_item">
                <i class="fa fa-envelope fa-fw" ></i>&nbsp;邮箱:1183503933@qq.com
            </div>
        </li>
        <li>
            <div class="li_item">
                <i class="fa fa-qq fa-fw" ></i>&nbsp;QQ:1183503933
            </div>
        </li>
    </ul>
</div>
</body>
</html>
