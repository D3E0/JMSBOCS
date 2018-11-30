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
        <ul id="list" class="list">
        </ul>
    </div>
</div>
<script>
    layui.use('laypage', function () {
        function getLocalTime(nS) {
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        }

        var $ = layui.$;
        //执行一个laypage实例m
        $.post('/domain',{
            courseId: 1
        },function (data) {
            domain=data;
            $.post('/uploadfiles', {
                jobId: 1,
                courseId: 1,
                studentId: 1
            }, function (data) {
                for (var i = 0; i < data.length; i++) {
                    var arr=data[i].key.split("/");
                    var filename=arr[arr.length-1];
                    console.info(filename);
                    var item =
                        '                <img class="icon" src="<c:url value="/static/img/file.png"/>" height="60px">\n' +
                        '                <div class="item">\n' +
                        '                    <div class="title">\n' +
                        '                        '+filename+'\n' +
                        '                    </div>\n' +
                        '                    <div class="time">\n' +
                        '                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>'+getLocalTime(data[i].putTime/10000000)+'&nbsp;&nbsp;\n' +
                        '                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>' + parseInt(data[i].fsize / 1024) + 'KB&nbsp;&nbsp;\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '                <button type="button" class="layui-btn downloadbtn"><a href="http://'+domain+'/'+data[i].key+'" class="a_post">下载</a></button>\n';
                    var li = document.createElement("li");
                    li.innerHTML = item;
                    $("#list").append(li);
                }
            });
        });

    });
</script>
</body>
</html>