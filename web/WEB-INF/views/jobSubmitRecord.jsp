<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/12/9
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <link href="<c:url value="/static/css/list.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="panel">
    <div class="panel-title">作业提交记录</div>
    <div class="op">
        <div class="searchdiv">
            <input type="text" id="search" placeholder="keyword">
            <i class=" fa-search fa fa-fw"></i>
        </div>
        &nbsp;&nbsp;
    </div>
    <input id="jobId" hidden value="${jobId}">
    <div class="content">
        <table id="demo" lay-filter="test"></table>
    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看提交文件</a>
</script>
<script>
    layui.use('table', function(){
        let table = layui.table;
        let tableIns=table.render({
            elem: '#demo'
            ,height: 312
            ,url: '/jobSubmitRecord' //数据接口
            ,page: true //开启分页
            ,method:"post"
            ,where:{jobId:$("#jobId").val(),keyword:function () {
                    return $("#search").val()
                }}
            ,cols: [[ //表头
                {field: 'userId', title: '学号', width:177, sort: true}
                ,{field: 'userName', title: '用户名', width:120}
                ,{field: 'status', title: '作业是否提交', width:177,sort:true}
                ,{field: 'fileCount', title: '作业提交文件数', width: 177}
                ,{field: 'lastSubmitTime', title: '提交作业时间', width: 177, sort: true,templet: function(d){
                    if (d.lastSubmitTime===undefined)
                        return '无';
                    return new Date(d.lastSubmitTime).toLocaleString();
                }}
                ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo'}
            ]]
        });
        let lastTime;
        $("#search").keyup(function (event) {
            lastTime = event.timeStamp;
            setTimeout(function () {
                if (lastTime - event.timeStamp === 0) {
                    tableIns.reload({
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                }
            }, 300);
        });
    });
</script>
</body>
</html>
