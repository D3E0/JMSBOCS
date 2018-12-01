<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/12/1
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Setting</title>
    <link rel="stylesheet" href="<c:url value="/static/layui/css/layui.css"/>">
    <script src="<c:url value="/static/layui/layui.js"/>"></script>
    <style>
        body {
            background-color: #eee;
            overflow: scroll;
        }
    </style>
</head>
<body>
<div class="content">
    <div class="menu" style="float: left; width: 200px;">
        <ul class="layui-nav layui-nav-tree nav">
            <li class="layui-nav-item layui-this"><a href="">系统通知</a></li>
            <li class="layui-nav-item"><a href="">朋友私信</a></li>
        </ul>
    </div>
    <div style="float: left;">
        <div class="notify"></div>
    </div>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">个人资料</div>
                <div class="layui-card-body">
                    <div class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" class="layui-input" disabled>
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">学号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="uid" class="layui-input" disabled>
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">Bucket</label>
                            <div class="layui-input-inline">
                                <input type="text" name="uid" class="layui-input" disabled>
                            </div>
                            <div class="layui-form-mid layui-word-aux">
                                当前正在使用公有云空间，点击绑定私有云空间
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">专业班级</label>
                            <div class="layui-input-inline">
                                <input type="text" name="speciatly" class="layui-input" disabled>
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">联系方式</label>
                            <div class="layui-input-block">
                                <input type="text" name="telephone" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" name="email" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="profile">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">密码修改</div>
                <div class="layui-card-body">
                    <div class="layui-form" action="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">原密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="pwd" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="newpwd" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">确认新密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="confirmpwd" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="pwdSubmit">立即提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">七牛云</div>
                <div class="layui-card-body">
                    <div class="layui-form ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">Access Key</label>
                            <div class="layui-input-inline">
                                <input type="text" name="ak" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">七牛云 Access Key</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">Secret Key</label>
                            <div class="layui-input-inline">
                                <input type="text" name="sk" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">七牛云 Secret Key</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">Bucket</label>
                            <div class="layui-input-inline">
                                <input type="text" name="bucket" class="layui-input" disabled>
                            </div>
                            <div class="layui-form-mid layui-word-aux">七牛云存储空间名称</div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-filter="*" id="qiniuSubmit">提交修改</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    let contextPath = '${pageContext.request.contextPath}';
    layui.use(['element', 'layer', 'form', 'upload'], function () {
        var element = layui.element,
            layer = layui.layer,
            form = layui.form,
            upload = layui.upload,
            $ = layui.$;
    });
</script>
</body>
</html>
