<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/12/12
  Time: 18:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
    <script src="<c:url value="/static/jszip/jszip.js"/>"></script>
    <script src="<c:url value="/static/jszip/jszip-utils.js"/>"></script>
    <script src="<c:url value="/static/jszip/FileSaver.js"/>"></script>
</head>
<body>
<input id="prefix" value="${filePrefix}" hidden>
<input id="jobId" hidden value="${jobId}" hidden>
<div style="margin: 48px">
    <span id="info">当前下载文件</span>
    <div class="layui-progress layui-progress-big progressDiv" lay-showPercent="true" lay-filter="progress">
        <div class="layui-progress-bar layui-bg-blue progress" lay-percent="0%"></div>
    </div>
</div>
<script>
    let jobId = $("#jobId").val();
    function urlToPromise(url) {
        return new Promise(function (resolve, reject) {
            JSZipUtils.getBinaryContent(url, function (err, data) {
                if (err) {
                    reject(err);
                } else {
                    resolve(data);
                }
            });
        });
    }
    let prefix = $('#prefix').val();
    let s = prefix.split('/');
    layui.use([ 'element'], function () {
        let  element = layui.element;
        let zip = new JSZip();
        let next = zip.folder(s[0]);
        next = next.folder(s[1]);
        console.info(s);
        $.post("/getAllFiles", {jobId: jobId}, function (data) {
            for (let i = 0; i < data.length; i++) {
                let arr = data[i].fileName.split("/");
                let filename = arr[arr.length - 1];
                let studentId = arr[arr.length - 2];
                let url = data[i].downloadUrl;
                let pos = next.folder(studentId);
                pos.file(filename, urlToPromise(url), {binary: true})
            }
            zip.generateAsync({type: "blob"}, function updateCallback(metadata) {
                if (metadata.currentFile) {
                    $('#info').text( "当前下载文件   " + metadata.currentFile);
                }
                element.progress('progress', metadata.percent.toFixed(1) + "%");
            })
                .then(function callback(blob) {
                    saveAs(blob, "example.zip");
                }, function (e) {
                });
        })
    });
</script>
</body>
</html>
