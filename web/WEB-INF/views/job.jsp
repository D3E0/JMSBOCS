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
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/editormd.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/marked.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/prettify.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/raphael.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/underscore.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/sequence-diagram.min.js"/>"></script>
    <script src="<c:url value="/static/editor.md-master/lib/flowchart.min.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/static/editor.md-master/css/editormd.css"/>"/>
    <link href="<c:url value="/static/css/job.css"/>" rel="stylesheet">
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel layui-row layui-col-space10">
    <div class="job-content layui-col-md10">
        <div class="panel-title"><c:out value="${job.jobTitle}"/>
            <c:if test="${!student}">
            <button id="delbtn" class="layui-btn redColor" style="float: right;margin-right: 1%">删除</button>
            <button id="updatebtn" class="layui-btn layui-bg-orange" style="float: right;margin-right: 1%">修改</button>
            </c:if>
        </div>
        <input id="filePrefix" hidden value="<c:out value="${filePrefix}"/>">
        <input id="courseId" hidden value="<c:out value="${job.courseId}"/>">
        <input id="jobId" hidden value="<c:out value="${jobId}"/>">
        <input id="userId" hidden value="<c:out value="${userId}"/>">
        <p class="title">作业描述</p>
        <div id="content" class="content">
        </div>
        <p class="title">作业提交</p>
        <div class="layui-upload">
            <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button>
            <button type="button" class="layui-btn uploadbtn layui-hide" id="testListAction">开始上传</button>
            <div class="layui-upload-list">
                <table class="layui-table " lay-skin="nob">
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
    <div class="rightbar layui-col-md2">
        <div class="info"><i class="fa fa-info-circle fa-fw"></i>&nbsp;&nbsp;作业信息</div>
        <ul class="list" style="width: 300px">
            <li>
                开始时间：${job.jobBeginTime}
            </li>
            <li>
                结束时间：${job.jobEndTime}
            </li>
            <li>
                状态：<c:if test="${job.ended}">
                    <span class="Tag redColor">Ended</span>
                    </c:if>
                <c:if test="${job.ended==false}"><span class="Tag greenColor">UnderWay<span></c:if>
            </li>
            <li>
                <a id="fileList" href="javascript:;" style="color: #3091f2">查看已提交作业</a>
            </li>
            <c:if test="${!student}">
                <li>
                    <a  href="${pageContext.request.contextPath}/jobSubmitRecord?jobId=${jobId}" style="color: #3091f2">查看作业提交记录</a>
                </li>
            </c:if>
        </ul>
    </div>
    <script>
        let courseId=$("#courseId").val();
        let userId=$("#userId").val();
        let jobId=$("#jobId").val();
        layui.use(['layer'], function () {
            let layer=layui.layer;
            $("#delbtn").click(function () {
                var jobId = $("#jobId").val();
                $.post('/deleteJob', {
                    jobId: jobId
                }, function (data) {
                    window.location.href = '/jobList';
                })
            });
            $("#updatebtn").click(function () {
                let param = "?jobId=${job.jobId}&jobTitle=${job.jobTitle}&jobBeginTime=${job.jobBeginTime}&jobEndTime=${job.jobEndTime}";
                window.location.href="/updateJob"+param;
            });
        });
    </script>
    <script src="<c:url value="/static/js/job.js"/>"></script>
    <script src="<c:url value="/static/js/fileupload.js"/>"></script>
</div>
</body>
</html>