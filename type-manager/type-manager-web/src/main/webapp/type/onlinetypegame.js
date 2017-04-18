//Websocket对象
var ws = null;
var url = null;
//下落字母集合
var objletter={};
var score=0;
var userMatchId;
$(function () {
    connect();
})
//hkjjsadjkfgksgdfas

//显示匹配中面板
function createMatch() {
        $("<div id = 'match'>匹配中。。。</div>").css({
        position:"absolute",top:"-50%",bottom:0,right:0,left:0,
        //background:"url(/type/sp1.png)",
        width:500,height:350,backgroundSize:"400px 340px",
        margin:"auto",
        borderRadius:"5%",
        backgroundSize:"contain no-repeat",
        animation:"cg 0s linear"
        }).appendTo("body");
        $("#bgsound").attr("autostart","true");

}

//初始化游戏
function createGame(){
    var height=$(window).height();
    var width1=$(window).width();
    $(".screen1").css({
        width:width1,
        height:$(window).height()-10,overflow:"hidden"
    });
    //显示记分板
    $("<div class='score'>0</div>").css({
        background:"url(/type/fs.png) no-repeat",
        backgroundSize:"150px 180px",
        width:150,height:180,
        position:"absolute",right:25,bottom:60,color:"#522E1A",
        fontSize:"60px",lineHeight:"140px",textAlign:"center"
    }).appendTo("body");
    //给字母按键绑定事件
    keydown();

}

//创建下落的字母
function creatletter(letter){
    var randomletter=letter;
    var randomnum=letter.charCodeAt();
    this.objletter[randomletter];
    var top1=-Math.round(Math.random()*100);
    var left1=Math.round(Math.random()*740);
    this.check(left1);
    var time=new Date().getTime();
    var ele=$("<div data-time="+time+"></div>").css({
        width:"100px",height:"100px",
        background:"url(/type/img/"+randomletter+".png) center no-repeat",backgroundSize:"contain"
        ,lineHeight:"60px",fontSize:"30px",color:"#fff",
        textAlign:"center",position:"absolute",left:left1,top:top1
    }).appendTo(".screen1").animate({top:$(window).height()},6000,"",function(){
    });
    this.objletter[randomletter]={start:left1-60,end:left1+60,keycode:randomnum,el:ele}
}
function check(left){
    var flag=false;
    $.each(this.objletter,function(index,value){
        if(left>value.start&&left<value.end){
            flag=true
        }
    });
    return flag;
}

function keydown(){
    $(document).keydown(function(e){
        $.each(objletter,function(index,value){
            var code=e.keyCode;
            if(code==value.keycode){
                var letter=String.fromCharCode(code);
                var message={state:5,userMatchId:userMatchId,letter:letter};
                var messageString=JSON.stringify(message);
                ws.send(messageString);
            }
        })
    })
}
function removeLetter(removeMessage) {
    var letter = removeMessage.letter;
    var code = letter.charCodeAt();
    $.each(objletter,function(index,value){
        if(code==value.keycode){
            value.el.remove();
            delete objletter[index];
            $(".score").html(removeMessage.score);
        }
    });
}
//创建webSocket连接
function connect() {
    //判断是否为https协议
    if (window.location.protocol == 'http:') {
        url = 'ws://' + window.location.host + "/websocket";
    } else {
        url = 'wss://' + window.location.host + "/websocket";
    }
    //判断浏览器是否支持websocket
    if ('WebSocket' in window) {
        ws = new WebSocket(url);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(url);
    } else {
        ws = new SockJS(url);
    }
    ws.onopen = function () {
        console.log('Info: connection opened.');
        createMatch();
    };
    ws.onmessage = function (event) {
        console.log('Received: ' + event.data);
        var webSocketData=$.parseJSON(event.data);
        userMatchId = webSocketData.userMatchId;
        if(webSocketData.state == 1){
            alert("目前游戏人数过多，请刷新页面重试！！！");
        }else if(webSocketData.state == 2){//降落字母
            if(webSocketData.letter=="start"){
                $("#match").remove();
                createGame();
            }
            creatletter(webSocketData.letter);
        }else if(webSocketData.state == 3){
            removeLetter(webSocketData);
        }else if(webSocketData.state==4){
            console.log("end");
            $.each(objletter,function(index,value){
                value.el.remove()
            });

            ws.close();
        }

    };
    ws.onclose = function (event) {
        console.log('Info: connection closed.');
        console.log(event);
    };
}
