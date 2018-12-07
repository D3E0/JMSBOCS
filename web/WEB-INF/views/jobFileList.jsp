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
    layui.use(['laypage','layer'], function () {
        layer=layui.layer;
        function getLocalTime(nS) {
            return new Date(parseInt(nS) * 1000).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
        }

        var $ = layui.$;
        $.post('/uploadFiles', {
            jobId: 1,
            courseId: 1,
            studentId: 1
        }, function (data) {
            for (var i = 0; i < data.length; i++) {
                var arr = data[i].fileName.split("/");
                var filename = arr[arr.length - 1];
                console.info(filename);
                var item =
                    '                <img class="icon" src="<c:url value="/static/img/file.png"/>" height="60px">\n' +
                    '                <div class="item">\n' +
                    '                    <div class="title">\n' +
                    '                        ' + filename + '\n' +
                    '                    </div>\n' +
                    '                    <div class="time">\n' +
                    '                        <a class="fa fa-calendar-o fa-fw" style="color: #2d8cf0"></a>' + getLocalTime(data[i].uploadTime / 10000000) + '&nbsp;&nbsp;\n' +
                    '                        <a class="fa fa-clock-o fa-fw" style="color: #2d8cf0"></a>' + data[i].fileSize + '&nbsp;&nbsp;\n' +
                    '                    </div>\n' +
                    '                </div>\n' +
                    '                <a href="' + data[i].downloadUrl + '" target="_blank" ><button type="button" class="layui-btn downloadbtn">下载</button></a>\n<button type="button" class="layui-btn deletebtn" data-key="'+data[i].fileName+'">删除</button>' +
                    '';
                var li = document.createElement("li");
                li.innerHTML = item;
                $("#list").append(li);
            }
            $(".deletebtn").click(function (e) {
                var li=$(this);
                var key=li.data("key");
                $.post("/deleteFile",{
                    courseId:1,
                    key:key
                },function (data) {
                    console.info(data);
                    if(data===1){
                        layer.msg("删除成功");
                        li.parent("li").remove();
                    }else{
                        layer.msg("删除失败");
                    }
                })
            });
        });

    });
</script>
</body>
</html>