<%--
  Created by IntelliJ IDEA.
  User: dx
  Date: 2017/4/9
  Time: 上午7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/bootstrap/css/bootstrap-theme.min.css">
    <title>HomePage</title>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        游戏
                    </a>
                </li>
                <li><a href="#ios" data-toggle="tab" id="selfInformation">个人信息</a></li>
                <li class="dropdown">
                    <a href="#" id="myTabDrop1" class="dropdown-toggle"
                       data-toggle="dropdown">比赛记录
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                        <li><a href="#onlineGame" tabindex="-1" data-toggle="tab">对战</a></li>
                        <li><a href="#selfGame" tabindex="-1" data-toggle="tab">个人</a></li>
                    </ul>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="home">
                    <p>打字游戏是用于电脑初学者练习打字，熟悉键盘。初学者可以通过本游戏，逐步提高打字速度。</p>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="thumbnail">
                                <img alt="300x200" src="/static/img/default6.jpg" />
                                <div class="caption">
                                    <h3>
                                        对战
                                    </h3>
                                    <p>
                                        与玩家对战
                                    </p>
                                    <p>
                                        <a class="btn btn-primary" href="/type/onlineGame">开始游戏</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="thumbnail">
                                <img alt="300x200" src="/static/img/default4.jpg" />
                                <div class="caption">
                                    <h3>
                                        单人游戏
                                    </h3>
                                    <p>
                                        挑战自己极限
                                    </p>
                                    <p>
                                        <a class="btn btn-primary" href="/type/selfGame">开始游戏</a>
                                    </p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="tab-pane fade" id="ios">
                    <div class="row">
                        <div class="col-md-1 col-md-offset-5">
                            <img id="headSculpture" data-toggle="tooltip" data-placement="right" title="点击更换" src="http://pinegrow.com/placeholders/img6.jpg" alt="" class="img-circle" style="width:110px;margin-top:30px">
                            <div id="imgUpload" style="display: none">
                                <p><input type="file" id="file1" name="file" /></p>
                                <input type="button" id="uploadButton" value="上传" class ="btn"/>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <!-- form: -->
                            <section>
                                <div class="col-lg-8 col-lg-offset-2">
                                    <div class="page-header">
                                        <h2>个人信息</h2>
                                    </div>
                                    <form id="updateForm" method="post" class="form-horizontal" action="/user/page/getSelfInformation"
                                          data-bv-message="This value is not valid"
                                          data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                                          data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                                          data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                                        <input type="hidden" name="userId" id="userId">
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">用户名</label>
                                            <div class="col-lg-5">
                                                <input type="text" class="form-control" name="userName" id="userName"
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
                                                <input class="form-control" name="email" id="email" type="email" data-bv-emailaddress-message="格式不正确" required data-bv-notempty-message="邮箱不能为空"/>
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
                                                <input type="text" class="form-control" name="birthday" id="birthday" data-bv-date="true" data-bv-date-format="YYYY/MM/DD" data-bv-date-message="格式不正确" required data-bv-notempty-message="生日不能为空"/> (YYYY/MM/DD)
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-lg-9 col-lg-offset-3">
                                                <button type="submit" class="btn btn-primary" id="update">修改</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </section>
                            <!-- :form -->
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="onlineGame">
                    <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
                </div>
                <div class="tab-pane fade" id="selfGame">
                    <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/static/bootstrap/js/jquery-1.10.2.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/bootstrap/js/bootstrapValidator.js"></script>
<script src="/static/js/ajaxfileupload.js"></script>
<script type="text/javascript">
    //表单验证
    $(document).ready(function() {
        $('#updateForm').bootstrapValidator();
    });
    //头像悬停提示
    $(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>
<script type="text/javascript">
    $(document).ready(function() {
        //请求显示信息
        $('#selfInformation').click(function () {
            $.ajax({
                url: "/user/page/getSelfInformation",
                success: function(data){
                    var typeUser = $.parseJSON(data);
                    $("#userId").val(typeUser.userId);
                    $("#userName").val(typeUser.userName);
                    $("#userName").attr("disabled","true");
                    $("#email").val(typeUser.email);
                    $("input:radio[value='"+typeUser.gender+"']").attr('checked','true');
                    $("#birthday").val(typeUser.stringBirthday);
                    $("#update").attr("disabled","true");
                    $("#headSculpture").attr("src",typeUser.img);
                }
            });
        });
        //提交修改信息
        $("#update").click(function () {
            $.ajax({
                url:"/user/page/updateById",
                data:$("#updateForm").serialize(),
                type:"POST",
                beforeSend:function(){
                    $("#update").attr("disabled","true");
                },
                success:function(data){
                }
            });
        });
        $("#headSculpture").click(function(){
            $("#imgUpload").css("display","")
        });
    });
</script>
<script type="text/javascript">
    $(function () {
        $("#uploadButton").click(function () {
            ajaxFileUpload();
        })
    })
    //文件上传
    function ajaxFileUpload() {
        $.ajaxFileUpload
        (
                {
                    url: '/user/page/imgUpload', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'file1', //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        console.log(data);

                        $("#headSculpture").attr("src", data.imgUrl);
                        if (typeof (data.error) != 'undefined') {
                            if (data.error != '') {
                                alert(data.error);
                            } else {
                                alert(data.msg);
                            }
                        }
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert(e);
                    }
                }
        )
        return false;
    }
</script>
</html>
