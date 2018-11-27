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
<body>
<div class="panel">
    <div class="content">
        <ul class="list">
            <li>
                <div class="item">
                    <div class="title">
                        戴霸天.jpg
                    </div>
                    <div class="time">
                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>2017-12-22 10:05:08&nbsp;&nbsp;
                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>26KB&nbsp;&nbsp;
                    </div>
                </div>
                <button type="button" class="layui-btn downloadbtn">下载</button>
            </li>
            <li>
                <img class="icon" src="<c:url value="/static/img/file.png"/>" height="60px">
                <div class="item">
                    <div class="title">
                        戴霸天.jpg
                    </div>
                    <div class="time">
                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>2017-12-22 10:05:08&nbsp;&nbsp;
                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>26KB&nbsp;&nbsp;
                    </div>
                </div>
                <button type="button" class="layui-btn downloadbtn">下载</button>
            </li>
            <li>
                <img class="icon" src="<c:url value="/static/img/file.png"/>" height="60px">
                <div class="item">
                    <div class="title">
                        戴霸天.jpg
                    </div>
                    <div class="time">
                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>2017-12-22 10:05:08&nbsp;&nbsp;
                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>26KB&nbsp;&nbsp;
                    </div>
                </div>
                <button type="button" class="layui-btn downloadbtn">下载</button>
            </li>
        </ul>
    </div>
</div>
<div id="mypage"></div>
<script>
    layui.use('laypage', function () {
        var laypage = layui.laypage, $ = layui.$;
        //执行一个laypage实例m
        laypage.render({
            elem: 'mypage' //注意，这里的 test1 是 ID，不用加 # 号
            , count: 50 //数据总数，从服务端得到
        });
        $(document).ready(function(){
            window.parent.setIframeHeight();
        });
    });
</script>
</body>
</html>