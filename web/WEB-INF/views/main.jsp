<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/11/11
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/list.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel">
    <div class="panel-title">最近公告 </div>
    <button class="layui-btn refresh">Refresh</button>
    <div class="content">
        <ul class="list">
            <li>
                <div class="title">
                    <a href="job.html">你的高数作业</a>
                </div>
                <div class="creator">
                    By root
                </div>
                <div class="date">
                    2017-12-22 10:05:08
                </div>
            </li>
            <li>
                <div class="title">
                    <a href="">你的高数作业</a>
                </div>
                <div class="creator">
                    By root
                </div>
                <div class="date">
                    2017-12-22 10:05:08
                </div>
            </li>
            <li>
                <div class="title">
                    <a href="">你的高数作业</a>
                </div>
                <div class="creator">
                    By root
                </div>
                <div class="date">
                    2017-12-22 10:05:08
                </div>
            </li>
            <li>
                <div class="title">
                    <a href="">你的高数作业</a>
                </div>
                <div class="creator">
                    By root
                </div>
                <div class="date">
                    2017-12-22 10:05:08
                </div>
            </li>
        </ul>
    </div>
</div>
<div id="mypage"></div>
<script>
    layui.use('laypage', function(){
        var laypage = layui.laypage,$=layui.$;
        //执行一个laypage实例m
        laypage.render({
            elem: 'mypage' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: 50 //数据总数，从服务端得到
        });
        $(document).ready(function(){
            window.parent.setIframeHeight();
        });
    });
</script>
</body>
</html>