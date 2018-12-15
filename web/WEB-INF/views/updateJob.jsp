<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/12/12
  Time: 10:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/static/editor.md-master/css/editormd.css"/>"/>
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <link href="<c:url value="/static/css/update.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/editor.md-master/editormd.min.js"/>"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel">
    <input id="courseId" hidden value="${courseId}">
    <div class="panel-title">添加作业
    </div>
    <form class="layui-form" action="" style="margin: 20px 3%">
        <input id="jobId" name="jobId" hidden value="${job.jobId}">
        <div class="layui-form-item">
            <label class="layui-form-label">作业标题</label>
            <div class="layui-input-block">
                <input id="jobTitle" type="text" name="jobTitle" required lay-verify="required"
                       placeholder="请输入标题" autocomplete="off" value="${job.jobTitle}" class="layui-input">
                <span id="tag" class="Tag">可以使用</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input id="jobBeginTime" type="text"
                       name="jobBeginTime" required lay-verify="required" autocomplete="off"
                       class="layui-input" value="${job.jobBeginTime}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-block">
                <input id="jobEndTime" type="text"
                       value="${job.jobEndTime}"
                       name="jobEndTime"
                       required lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">作业描述</label>
            <div id="test-editormd" class="layui-input-block">
                <textarea id="mdtextarea" name="jobContent"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script>
    let testEditor;
    let jobId=$("#jobId").val();
    $.post('/getJobContent',{jobId},function (data) {
        $('#mdtextarea').val(data);
    });
    testEditor = editormd("test-editormd", {
        placeholder: '本编辑器支持Markdown编辑，左边编写，右边预览',  //默认显示的文字，这里就不解释了
        width: "90%",
        height: 400,
        syncScrolling: "single",
        path: '<c:url value="/static/editor.md-master/lib/"/>',   //你的path路径（原资源文件中lib包在我们项目中所放的位置）
        emoji: false,
        taskList: true,
        tocm: true,         // Using [TOCM]
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
        toolbarIcons: function () {  //自定义工具栏，后面有详细介绍
            return editormd.toolbarModes['simple']; // full, simple, mini
        },
    });
    layui.use(['form', 'laydate','layer'], function () {
        let form = layui.form, laydate = layui.laydate,layer=layui.layer;
        let different = 0;
        $("#jobTitle").blur(function (event) {
            let jobTitle = $('#jobTitle').val();
            let courseId = $('#courseId').val();
            $.post('/isSameJobTitle', {jobTitle,courseId}, function (data) {
                let tag = $("#tag");
                tag.removeClass("redColor");
                tag.removeClass("greenColor");
                tag.text('');
                console.info("jobTitle"+jobTitle);
                if (data > 1||jobTitle==="") {
                    different=1;
                    tag.addClass("redColor");
                    tag.text('重复或不可用');
                } else {
                    different = 0;
                    tag.addClass("greenColor");
                    tag.text('可以使用');
                }
            });
        });
        form.on('submit(formDemo)', function (data) {
            // layer.msg(JSON.stringify(data.field));
            console.info(JSON.stringify(data.field));
            if (different === 0){
                $.post('/updateJob', data.field, function (data) {
                    if(data===1)
                    window.location.href = '/jobList';
                    else
                        layer.msg("日期格式错误");
                });
            }else{
                layer.msg('请填写合法的作业标题');
            }
            return false;
        });
        laydate.render({
            elem: document.getElementById('jobBeginTime')
            , type: 'datetime', min: 0
        });
        laydate.render({
            elem: document.getElementById('jobEndTime')
            , type: 'datetime'
        });
    });
</script>
</body>
</html>
