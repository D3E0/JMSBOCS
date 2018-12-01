<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/11/24
  Time: 11:11
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/job.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script src="<c:url value="/static/js/fileupload.js"/>"></script>
</head>
<body>
<div class="panel">
    <div class="job-content">
        <div class="panel-title"><c:out value="${job.jobTitle}"/>
            <button class="layui-btn delbtn" style="float: right;margin-right: 1%" >删除</button>
            <button class="layui-btn layui-bg-orange" style="float: right;margin-right: 1%">修改</button>
        </div>
        <input id="courseId" hidden value="<c:out value="${job.courseId}"/>">
        <p class="title">作业描述</p>
        <p class="content">
            ${job.jobContent}
        </p>
        <p class="title">作业提交</p>
        <div class="layui-upload">
            <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button>
            <button type="button" class="layui-btn uploadbtn layui-hide" id="testListAction">开始上传</button>
            <div class="layui-upload-list">
                <table class="layui-table "lay-skin="nob">
                    <thead>
                    <tr>
                        <th>文件名</th>
                        <th>大小</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="demoList"></tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="rightbar">
        <div class="info"><i class="fa fa-info-circle fa-fw" ></i>&nbsp;&nbsp;作业信息</div>
        <ul class="list">
            <li>
                开始时间：${job.jobBeginTime}
            </li>
            <li>
                结束时间：${job.jobEndTime}
            </li>
            <li>
                状态：可提交
            </li>
            <li>
                <a id="filelist" href="javascript:;" style="color: #3091f2">查看已提交作业</a>
            </li>
        </ul>
    </div>
    <script>
        window.onload=function () {
            window.parent.setIframeHeight();
        };
        $('#filelist').click(function () {
            console.info("click");
            window.parent.layui.use(['layer'], function () {//调用父页面的layer
                var layer=window.parent.layui.layer;
                layer.open({
                    title: false,
                    area: ['700px','500px'],
                    type: 2,
                    scrollbar : true,
                    content:  ['/jobfilelist', 'no']
                });
            });
        });
    </script>
</div>
</body>
</html>