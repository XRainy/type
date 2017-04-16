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
    <title>登录</title>
</head>
<body background="/static/img/background.jpg">
<div class="container">
    <div class="row">
        <!-- form: -->
        <section>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>登录</h2>
                </div>

                <form id="defaultForm" method="post" class="form-horizontal" action="/login"
                      data-bv-message="This value is not valid"
                      data-bv-feedb ackicons-valid="glyphicon glyphicon-ok"
                      data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                      data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">用户名</label>
                        <div class="col-lg-5">
                            <input type="text" class="form-control" name="userName"
                                   data-bv-message="用户名无效"
                                   required data-bv-notempty-message="请输入用户名"
                                   pattern="^[a-zA-Z0-9]+$" data-bv-regexp-message="不能包含特殊字符"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">密码</label>
                        <div class="col-lg-5">
                            <input type="password" class="form-control" name="password"
                                   required data-bv-notempty-message="请输入密码"
                                   />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-3">
                            <button type="submit" class="btn btn-primary">登录</button>
                            <button type="button" id="register" class="btn btn-primary">注册</button>
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
$(function(){/* 文档加载，执行一个函数*/
    $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {/*input状态样式图片*/
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {/*验证：规则*/
            userName: {//验证input项：验证规则
                message: '用户名无效',
                validators: {
                    threshold :  6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: '/userNameExistTrue',//验证地址
                        message: '用户名不存在',//提示消息
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                    },
                }
            },
            password: {//验证input项：验证规则
                message: 'The password is not valid',
                validators: {
                    threshold :  6 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    remote: {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}
                        url: '/loginVolidate',//验证地址
                        message: '密码输入错误',//提示消息
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST',//请求方式
                        data: function(validator) {
                            return {
                                userName: $('[name="userName"]').val(),
                                password: $('[name="password"]').val()
                            };
                        }

                    },
                }
            }


        }
    })
});
</script>
<script>
    $(function () {
        $("#register").click(function () {
            window.location.href="/goToRegister";
        });
    })
</script>
</html>
