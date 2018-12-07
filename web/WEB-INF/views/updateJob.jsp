<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/12/6
  Time: 12:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <link href="<c:url value="/static/css/update.css"/>" rel="stylesheet">
</head>
<body>
<form class="layui-form" action="" style="margin: 20px 3%">
    <input name="jobId" value="${job.jobId}" hidden>
    <div class="layui-form-item">
        <label class="layui-form-label">作业标题</label>
        <div class="layui-input-block">
            <input type="text" name="jobTitle" value="${job.jobTitle}" required lay-verify="required"
                   placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">文本域</label>
        <div class="layui-input-block">
            <textarea name="jobContent" required placeholder="请输入内容" class="layui-textarea">${job.jobContent}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
            <input id="jobBeginTime" type="text"
                   value="<fmt:formatDate value="${job.jobBeginTime}" pattern="yyyy-MM-dd HH:mm:ss" />"
                   name="jobBeginTime" required lay-verify="required" placeholder="请输入标题" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">结束时间</label>
        <div class="layui-input-block">
            <input id="jobEndTime" type="text"
                   value="<fmt:formatDate value="${job.jobEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />" name="jobEndTime"
                   required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form', 'laydate'], function () {
        var form = layui.form, laydate = layui.laydate;
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            $.post('/updateJob', data.field, function (data) {

            });
            return false;
        });
        window.parent.layui.use(['laydate', 'form', 'layer'], function () {//调用父页面的laydate
            let laydate = window.parent.layui.laydate;
            laydate.render({
                elem: document.getElementById('jobBeginTime')
                , type: 'datetime', min: 0
            });
            laydate.render({
                elem: document.getElementById('jobEndTime')
                , type: 'datetime'
            });
        });
    });
</script>
</body>
</html>
