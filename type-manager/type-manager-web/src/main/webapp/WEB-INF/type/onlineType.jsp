<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>金山在线打字通</title>
    <script src="/type/jquery.js"></script>
    <script src="/type/onlinetypegame.js"></script>
</head>
<style>
    body{
        margin:0;
        background-size:cover;
        font-family: "幼圆";
        background:#E8F0F0;
        overflow:hidden;
    }
    .screen1{
        width:100%;
        height:100%;
        background:url(/type/img/dz2.jpg) no-repeat;
        background-size:cover;
        margin:0 auto; padding:0;
        position:relative;overflow:hidden;
    }
    .xiabian{
        width:100%;
        height:40px;
        background:#000;
        position:absolute;bottom:0;right:0;
        opacity:0.8;
        filter:alpha(opacity=80);
    }
    @keyframes cg {
        0%{
            transform:translateY(-50%);
        }100%{
             transform:translateY(0%);
         }
    }
    @keyframes cgfont {
        0%{
            transform:scale(0,0);
        }100%{
             transform:scale(1,1);
         }
    }
    body{
        background:#84B526;
    }
</style>
<body>
<div class="screen1">
</div>
<div class="stop1">暂停游戏</div>
<div class="xiabian"></div>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
    <p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>
</div>
</body>
</html>
