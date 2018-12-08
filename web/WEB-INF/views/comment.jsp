<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/12/8
  Time: 15:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Comment</title>
    <link rel="stylesheet" href="<c:url value="/static/elementui/theme-chalk/index.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/valine.css"/>">
    <style>
        body {
            background-color: #fff;
        }

        .vwrap {
            border: 1px solid #f0f0f0;
            border-radius: 4px;
            margin-bottom: 10px;
            overflow: hidden;
            position: relative;
            padding: 10px;
        }
    </style>
</head>

<body>
<div id="app">
    <comment></comment>
</div>

<script type="text/javascript" src="<c:url value="/static/js/axios.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/vue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/vue-router.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/elementui/index.js"/>"></script>

</body>
<script type="module">
    import comment from "/static/js/comment.js"

    new Vue({
        el: '#app',
        data: {
            uid: 2,
            courseId: 2
        },
        components: {
            comment
        }
    });
</script>
</html>

