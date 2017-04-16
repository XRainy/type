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
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#">首页</a>
                </li>
                <li>
                    <a href="#">简介</a>
                </li>
                <li class="disabled">
                    <a href="#">信息</a>
                </li>
                <li class="dropdown pull-right">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#">操作</a>
                        </li>
                        <li>
                            <a href="#">设置栏目</a>
                        </li>
                        <li>
                            <a href="#">更多设置</a>
                        </li>
                        <li class="divider">
                        </li>
                        <li>
                            <a href="#">分割线</a>
                        </li>
                    </ul>
                </li>
            </ul>
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
                                练习
                            </h3>
                            <p>
                                单机排行
                            </p>
                            <p>
                                <a class="btn btn-primary" href="/typr/selfGame">开始游戏</a>
                            </p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<script src="/static/bootstrap/js/jquery-1.10.2.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
</html>
