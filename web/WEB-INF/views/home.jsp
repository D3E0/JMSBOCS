<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/11/11
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <style>
        body {
            background-color: #FFFFFF;
        }

        .content {
            background: #fff;
            padding: 10px;
        }

        * {
            margin: 0 auto;
            padding: 0;
            box-sizing: border-box;
            font-family: -apple-system, BlinkMacSystemFont, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", 微软雅黑, "helvetica neue", helvetica, ubuntu, roboto, noto, "segoe ui", Arial, sans-serif;
        }

        .v * {
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            line-height: 2;
            color: #555;
            -webkit-transition: all .3s ease;
            transition: all .3s ease;
        }

        .v .vwarp {
            border: 1px solid #f0f0f0;
            border-radius: 4px;
            margin-bottom: 10px;
            overflow: hidden;
            position: relative;
            padding: 10px;
        }

        .v .vwrap input {
            background: transparent;
        }

        .v .vinput {
            border: none;
            resize: none;
            outline: none;
            padding: 10px 5px;
            max-width: 100%;
            font-size: .775rem;
        }

        .v .vwrap .vheader .vinput {
            width: 33.33%;
            border-bottom: 1px dashed #dedede;
        }
    </style>
</head>
<html>
<c:import url="head.jsp"/>
<body>
<div class="content">
    <div class="comment v">
        <div class="vwarp">
            <div class="vheader item3">
                <input name="nick" placeholder="昵称" class="vnick vinput" type="text">
                <input name="mail" placeholder="邮箱" class="vmail vinput" type="email">
                <input name="link" placeholder="网址(http://)" class="vlink vinput" type="text">
            </div>
        </div>
    </div>
</div>
</body>
</html>
