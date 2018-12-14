<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/12/1
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Notify</title>
    <link rel="stylesheet" href="<c:url value="/static/elementui/theme-chalk/index.css"/>">

    <style>
        body {
            background-color: #fff;
        }

        .content {
            position: absolute;
            height: 300px;
            width: 400px;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        #owl-login {
            top: -100px;
            z-index: 1000;
            width: 211px;
            height: 108px;
            background-image: url("/static/img/owl-login.png");
            position: absolute;
            left: 50%;
            margin-left: -80px;
        }

        @media all and (-webkit-min-device-pixel-ratio: 1.5), (min--moz-device-pixel-ratio: 1.5), (-o-min-device-pixel-ratio: 1.5/1), (min-device-pixel-ratio: 1.5), (min-resolution: 138dpi), (min-resolution: 1.5dppx) {
            #login #owl-login {
                background-image: url("/static/img/owl-login@2x.png");
                -webkit-background-size: 211px 108px;
                -moz-background-size: 211px 108px;
                background-size: 211px 108px;
            }
        }

        #owl-login .hand {
            width: 34px;
            height: 34px;
            -webkit-border-radius: 40px;
            border-radius: 40px;
            background-color: #472d20;
            -webkit-transform: scaleY(0.6);
            -moz-transform: scaleY(0.6);
            -o-transform: scaleY(0.6);
            -ms-transform: scaleY(0.6);
            transform: scaleY(0.6);
            -webkit-transition: 0.3s ease-out;
            -moz-transition: 0.3s ease-out;
            -o-transition: 0.3s ease-out;
            transition: 0.3s ease-out;
            position: absolute;
            left: 14px;
            bottom: -8px;
        }

        #owl-login .hand.hand-r {
            left: 170px;
        }

        #owl-login.password .hand {
            -webkit-transform: translateX(42px) translateY(-15px) scale(0.7);
            -moz-transform: translateX(42px) translateY(-15px) scale(0.7);
            -o-transform: translateX(42px) translateY(-15px) scale(0.7);
            -ms-transform: translateX(42px) translateY(-15px) scale(0.7);
            transform: translateX(42px) translateY(-15px) scale(0.7);
        }

        #owl-login.password .hand.hand-r {
            -webkit-transform: translateX(-42px) translateY(-15px) scale(0.7);
            -moz-transform: translateX(-42px) translateY(-15px) scale(0.7);
            -o-transform: translateX(-42px) translateY(-15px) scale(0.7);
            -ms-transform: translateX(-42px) translateY(-15px) scale(0.7);
            transform: translateX(-42px) translateY(-15px) scale(0.7);
        }

        #owl-login .arms {
            top: 58px;
            position: absolute;
            width: 100%;
            height: 41px;
            overflow: hidden;
        }

        #owl-login .arms .arm {
            width: 40px;
            height: 65px;
            position: absolute;
            left: 20px;
            top: 40px;
            background-image: url("/static/img/owl-login-arm.png");
            -webkit-transition: 0.3s ease-out;
            -moz-transition: 0.3s ease-out;
            -o-transition: 0.3s ease-out;
            transition: 0.3s ease-out;
            -webkit-transform: rotate(-20deg);
            -moz-transform: rotate(-20deg);
            -o-transform: rotate(-20deg);
            -ms-transform: rotate(-20deg);
            transform: rotate(-20deg);
        }

        @media all and (-webkit-min-device-pixel-ratio: 1.5), (min--moz-device-pixel-ratio: 1.5), (-o-min-device-pixel-ratio: 1.5/1), (min-device-pixel-ratio: 1.5), (min-resolution: 138dpi), (min-resolution: 1.5dppx) {
            #login #owl-login .arms .arm {
                background-image: url("/static/img/owl-login-arm@2x.png");
                -webkit-background-size: 40px 65px;
                -moz-background-size: 40px 65px;
                background-size: 40px 65px;
            }
        }

        #owl-login .arms .arm.arm-r {
            -webkit-transform: rotate(20deg) scaleX(-1);
            -moz-transform: rotate(20deg) scaleX(-1);
            -o-transform: rotate(20deg) scaleX(-1);
            -ms-transform: rotate(20deg) scaleX(-1);
            transform: rotate(20deg) scaleX(-1);
            left: 158px;
        }

        #owl-login.password .arms .arm {
            -webkit-transform: translateY(-40px) translateX(40px);
            -moz-transform: translateY(-40px) translateX(40px);
            -o-transform: translateY(-40px) translateX(40px);
            -ms-transform: translateY(-40px) translateX(40px);
            transform: translateY(-40px) translateX(40px);
        }

        #owl-login.password .arms .arm.arm-r {
            -webkit-transform: translateY(-40px) translateX(-40px) scaleX(-1);
            -moz-transform: translateY(-40px) translateX(-40px) scaleX(-1);
            -o-transform: translateY(-40px) translateX(-40px) scaleX(-1);
            -ms-transform: translateY(-40px) translateX(-40px) scaleX(-1);
            transform: translateY(-40px) translateX(-40px) scaleX(-1);
        }
    </style>
</head>
<%--<jsp:include page="head.jsp"/>--%>
<body>
<div id="app" style="margin-left: 2%;">
    <el-container>
        <login></login>
    </el-container>
</div>
<script>
    const path = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value="/static/js/axios.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/vue.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/elementui/index.js"/>"></script>
<script src="<c:url value="/static/js/login-main.js"/>" type="module"></script>

</body>

</html>
