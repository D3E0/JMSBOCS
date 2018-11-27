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
    <link href="<c:url value="/static/css/class.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<div class="panel">
    <div class="panel-title">java程序设计
        <button class="layui-btn additem"><a class="fa fa-plus fa-fw" style="color: #FFFFFF"></a>&nbsp;&nbsp;上传资源
        </button>
    </div>
    <div class="leftbar">
        <ul class="list">
            <li>
                <a><i class="fa fa-file fa-fw"></i>&nbsp;&nbsp;课程资源</a>
            </li>
            <li>
                <a id="teacher"><i class="fa fa-user fa-fw"></i>&nbsp;&nbsp;联系老师</a>
            </li>
            <li>
                <a><i class="fa fa-mortar-board fa-fw"></i>&nbsp;&nbsp;课程讨论</a>
            </li>
        </ul>
    </div>
    <iframe src="/filelist" id="myiframe" width="85%" scrolling="no" frameborder="0" style="float: right"
            onload="setIframeHeight(this)">
    </iframe>
</div>
<script>
    layui.use(['laypage', 'element', 'layer'], function () {
        var laypage = layui.laypage, $ = layui.$;
        //执行一个laypage实例m
        laypage.render({
            elem: 'mypage' //注意，这里的 test1 是 ID，不用加 # 号
            , count: 50 //数据总数，从服务端得到
        });
        window.onload = function () {
            window.parent.setIframeHeight();
        };
        $('#teacher').click(function () {
            console.info("click");
            window.parent.layui.use(['layer'], function () {//调用父页面的layer
                var layer=window.parent.layui.layer;
                layer.open({
                    title: false,
                    area: ['500px', '500px'],
                    type: 2,
                    content:  ['/teacher', 'no']
                });
            });
        });
    });
    var test;
    function setIframeHeight(iframe) {
        if (!test)
            test = iframe;
        else
            iframe = test;
        if (iframe) {
            iframe.height = 0;
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
            console.info("sds");
        }
    }
</script>
</body>
</html>
