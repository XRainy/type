<%--
  Created by IntelliJ IDEA.
  User: dx
  Date: 2017/4/3
  Time: 下午10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrapValidator.css">
    <title>注册</title>
</head>
<body background="/static/img/background.jpg">
<div class="container">
    <div class="row">
        <!-- form: -->
        <section>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>注册</h2>
                </div>

                <form id="defaultForm" method="post" class="form-horizontal" action="/register"
                      data-bv-message="This value is not valid"
                      data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                      data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                      data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">用户名</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" name="userName"
                                   data-bv-message="用户名无效"
                                   required data-bv-notempty-message="用户名不能为空"
                                   pattern="^[a-zA-Z0-9]+$" data-bv-regexp-message="不能包含特殊字符"
                                   data-bv-stringlength="true" data-bv-stringlength-min="6" data-bv-stringlength-max="30" data-bv-stringlength-message="长度在6-30个字符"
                                   data-bv-different="true" data-bv-different-field="password" data-bv-different-message="不能与密码相同"
                                   data-bv-remote="true" data-bv-remote-url="/userNameExistFalse" data-bv-remote-message="用户名已经存在"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">邮箱</label>
                        <div class="col-lg-5">
                            <input class="form-control" name="email" type="email" data-bv-emailaddress-message="格式不正确" required data-bv-notempty-message="邮箱不能为空"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="password"
                                   required data-bv-notempty-message="密码不能为空"
                                   data-bv-different="true" data-bv-different-field="userName" data-bv-different-message="不能与用户名相同"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">确认密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="confirmPassword"
                                   required data-bv-notempty-message="确认密码不能为空"
                                   data-bv-identical="true" data-bv-identical-field="password" data-bv-identical-message="两次输入密码不同"
                                   data-bv-different="true" data-bv-different-field="userName" data-bv-different-message="不能与用户名相同"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">性别</label>
                        <div class="col-lg-5">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" value="1" required data-bv-notempty-message="请选择性别" /> 男
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" value="2" /> 女
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="gender" value="3" /> 其他
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">出生日期</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" name="birthday" data-bv-date="true" data-bv-date-format="YYYY/MM/DD" data-bv-date-message="格式不正确" required data-bv-notempty-message="生日不能为空"/> (YYYY/MM/DD)
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-primary">注册</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>
</div>
</body>
<script src="/static/bootstrap/js/jquery-1.10.2.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/bootstrap/js/bootstrapValidator.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#defaultForm').bootstrapValidator();
    });
</script>
</html>
