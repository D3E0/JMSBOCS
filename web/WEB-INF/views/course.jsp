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
<jsp:include page="head.jsp"/>
<div class="panel">
    <div class="titlediv">
        <div class="course-title">
            ${courseName}
            <input id="courseId" value="${courseId}" hidden>
        </div>
        <button id="getfile" class="layui-btn additem"><a class="fa fa-plus fa-fw" style="color: #FFFFFF"></a>&nbsp;&nbsp;上传资源
        </button>
        <button id="upload"></button>
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
    <jsp:include page="fileList.jsp"/>
</div>
<script>
    layui.use(['laypage', 'element', 'layer', 'upload'], function () {
        let laypage = layui.laypage, $ = layui.$, upload = layui.upload;
        //执行一个laypage实例m
        filename = "test";
        let courseId = $('#courseId').val();
        $.post('/qiniu', {
            courseId: courseId
        }, function (data) {
            upload_token = data;
            upload.render({
                elem: '#getfile' //绑定元素
                , url: 'http://upload.qiniup.com' //上传接口
                , data: {
                    token: upload_token,
                    key: function () {
                        return 'public/' + courseId + '/' + filename;
                    }
                }, accept: 'file'
                , auto: false
                , bindAction: '#upload'
                , choose: function (obj) {
                    let files = obj.pushFile();
                    obj.preview(function (index, file, result) {
                        filename = file.name;//得到文件对象
                        console.info("before filename  " + filename);
                        $("#upload").click();
                    });
                }
                , done: function (res, index, upload) {
                    if (res.error === undefined) { //上传成功
                        window.location.reload();
                    }
                    this.error(index, upload);
                }
                , error: function (index, upload) {
                    layer.msg('上传失败');
                }
            });
        });
        $('#teacher').click(function () {
            console.info("click");
            layer.open({
                title: false,
                area: ['500px', '500px'],
                type: 2,
                content: ['/teacher?courseId=' + courseId, 'no']
            });
        });
    });
</script>
</body>
</html>
