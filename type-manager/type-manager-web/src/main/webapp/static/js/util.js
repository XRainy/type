/******************************************************************
 * 日常开发工具类
 *
 * @author zoutuo
 * @version 2013-06-22
 *
 * history:
 *
 */
var Util = new _Util();
function _Util() {
    /**
     * 格式化date类型
     * @param value
     * @param formatType
     * @returns {*}
     */
    this.formatDateTime = function (value, formatType) {
        if (value == undefined) {
            return "";
        }
        if (!formatType) {
            formatType = "yyyy-MM-dd hh:mm:ss";
        }
        return new Date(value.time).Format(formatType);
    };

    /**
     * 字符串转date类型
     * @param date 格式：2014-08-07 22:12:45
     */
    this.str2Date = function (date) {
        var s = date;
        var d = new Date(Date.parse(s.replace(/-/g, "/")));

        return d;
    };

    /**
     * 改变时间
     * @param date 日期，支持字符串、date类型
     * @param num 正数：加N天，负数：减N天
     * @param returnType  返回类别。date：Date类型，string：String类型
     * @returns {string, Date}
     */
    this.changeDay = function (date, num, returnType) {
        if(date == null){
            date = new Date();
        }
        if(num == null){
            num = 0;
        }
        if(typeof date == "string"){
            date = Util.str2Date(date);
        }

        date.setDate(date.getDate() + num);

        if(returnType == "date"){
            return date;
        } else if(returnType == "string"){
            var dd = date.getDate();
            dd = dd < 10 ? "0" + dd : dd;
            var mm = date.getMonth() + 1;
            mm = mm < 10 ? "0" + mm : mm;
            var y = date.getFullYear();

            var someFormattedDate = y + "-" + mm + "-" + dd;
            return someFormattedDate;
        }
    };

    /**
     * easyUI的alert提示
     * @param msg
     * @param returnFunction
     */
    this.alert = function (msg, returnFunction) {
        if (typeof returnFunction != "function") {
            returnFunction = function () {
            };
        }
        $.messager.alert("提示信息：", msg, "info", returnFunction);
    };
    /**
     * easyUI的error提示
     * @param msg
     * @param returnFunction
     */
    this.error = function (msg, returnFunction) {
        if (typeof returnFunction != "function") {
            returnFunction = function () {
            };
        }
        $.messager.alert("错误信息：", msg, "error", returnFunction);
    };
    /**
     * easyUI的question提示
     * @param msg
     * @param returnFunction
     */
    this.question = function (msg, returnFunction) {
        if (typeof returnFunction != "function") {
            returnFunction = function () {
            };
        }
        $.messager.alert("信息：", msg, "question", returnFunction);
    };
    /**
     * easyUI的warn提示
     * @param msg
     * @param returnFunction
     */
    this.warning = function (msg, returnFunction) {
        if (typeof returnFunction != "function") {
            returnFunction = function () {
            };
        }
        $.messager.alert("警告信息：", msg, "warning", returnFunction);
    };

    /**
     * easyUI的confirm提示
     * @param msg
     * @param returnFunction
     */
    this.confirm = function (msg, returnFunction) {
        if (typeof returnFunction != "function") {
            returnFunction = function () {
            };
        }
        $.messager.confirm("确定操作？", msg, function (r) {
            if (r) {
                returnFunction(r);
            }
        });
    };

    /**
     * ajax请求
     * @param url       请求地址
     * @param params    请求参数
     * @param success   回调函数
     * @param ajaxParam 扩展参数。例如：{type : "POST"}
     */
    this.ajax = function (url, params, success, ajaxParam) {
        if (!url) {
            Commons.showError("请传入url");
            return;
        }
        if (!success) {
            success = function (data) {
            };
        }

        var customAjaxParam = Util.clone(ajaxParam);
        if (ajaxParam == null) {
            customAjaxParam = new Object();
        }
        if (customAjaxParam.type == null || customAjaxParam.type == "") {
            customAjaxParam.type = "POST";
        }
        customAjaxParam.url = url;
        customAjaxParam.data = params;
        customAjaxParam.success = function (data) {
            try {
                success(eval("(" + data + ")"));
            } catch (e) {    //若转换失败，则直接返回data
                success(data);
            } finally {
//                overlay.close();//关闭遮罩层
            }
        };
        customAjaxParam.error = function (XmlHttpRequest, textStatus, errorThrown) {
//            overlay.close();//关闭遮罩层
            switch (XmlHttpRequest.status) {
                case 404 :
                    Commons.showError("找不到网络路径");
                    break;
                case 500 :
                    Commons.showError("网络繁忙，请稍后重试");
                    break;
                case 0:
                    Commons.showInfo("您已终止查询操作!");
                    break;
                default :
                    Commons.showError("内部错误，请重试");
            }
        };

//        var overlay = new Overlay(0.0);
//        overlay.show();//开启遮罩层
        var returnVal = $.ajax(customAjaxParam);
        return returnVal;
    };

    /**
     * 清空父元素下指定元素文本值
     * @param element   要清空的父元素ID
     * @param type      要清空的元素类别
     *
     * 例如，清空editPerson下所有text文本框内容：
     * $('#editPerson input[type=text]').val("");
     */
    this.resetInputForm = function (element, type) {
        if (!$("#" + element).get(0)) {
            Util.error("请传入有效的id");
            return;
        }
        if (!type) {
            Util.error("请传入有效的type");
            return;
        }
        $("#" + element + " input[type=" + type + "]").val("");
    };

    this.resetSelectForm = function (element) {
        if (!$("#" + element).get(0)) {
            Util.error("请传入有效的id");
            return;
        }
        $("#" + element + " select").val("");
    };

    /**
     * 四舍五入，保留位数为roundDigit
     * @param numberRound
     * @param roundDigit
     * @return {number}
     */
    this.round = function (numberRound, roundDigit) {
        if (numberRound >= 0) {
            var tempNumber = parseInt((numberRound * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
            return   tempNumber;
        } else {
            var numberRound1 = -numberRound;
            var tempNumber = parseInt((numberRound1 * Math.pow(10, roundDigit) + 0.5)) / Math.pow(10, roundDigit);
            return -tempNumber;
        }
    };

    /**
     * 打开模态窗口
     * @param url 页面地址
     * @param width 窗口宽度
     * @param height 窗口高度
     * @param arguments 传入参数
     * @param explorerVersion
     * @param isChangeForBrowser
     * @param scrolls
     * @returns {Object}
     */
    this.openModalDialog = function (url, width, height, arguments, explorerVersion, isChangeForBrowser, scrolls) {
        if (isChangeForBrowser == null)isChangeForBrowser = true;
        if (explorerVersion == null)explorerVersion = 6;
        if (isChangeForBrowser) {
            var Index = new _Index();
            var MzBrowser = Index.whatExplorer();//判断浏览器类别以及版本号
            if (window['MzBrowser'].ie && window['MzBrowser'].version == 7) {
                if (explorerVersion == 6) {
                    width = width - 6;
                    height = height - 54;
                }
            } else if (window['MzBrowser'].ie) {
                if (explorerVersion == 7) {
                    width = width + 6;
                    height = height + 54;
                }
            }
        }
        if (scrolls != 'no' && scrolls != 'false') {
            scrolls = 'yes';
        } else {
            scrolls = 'no';
        }
        var features = "scroll:" + scrolls + ";status:no;dialogWidth:" + width + "px;dialogHeight:" + height + "px;help:no";
        return showModalDialog(url, arguments, features);
    };

    /**
     * 克隆对象——解决引用传递问题
     * @param obj
     * @returns {*}
     */
    this.clone = function (obj) {
        // Handle the 3 simple types, and null or undefined
        if (null == obj || "object" != typeof obj) return obj;

        // Handle Date
        if (obj instanceof Date) {
            var copy = new Date();
            copy.setTime(obj.getTime());
            return copy;
        }

        // Handle Array
        if (obj instanceof Array) {
            var copy = [];
            for (var i = 0, len = obj.length; i < len; ++i) {
                copy[i] = Util.clone(obj[i]);
            }
            return copy;
        }

        // Handle Object
        if (obj instanceof Object) {
            var copy = {};
            for (var attr in obj) {
                if (obj.hasOwnProperty(attr)) copy[attr] = Util.clone(obj[attr]);
            }
            return copy;
        }

        throw new Error("Unable to copy obj! Its type isn't supported.");
    };
}

/**
 * 对Date进行扩展格式化
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
    return fmt;
}

/**
 * 加入浏览器类型判断
 * @type {Object}
 */
var Browser = new Object();
Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != -1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != -1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != -1);
Browser.isChrome = (navigator.userAgent.toLowerCase().indexOf("chrome") != -1);

/*
 模态遮罩层单例对象
 opacity:背景透明度
 1. show()
 2. close()
 */
Overlay = function (opacity) {
    var self = this;
    self._createDiv = function () {

        if (self._overlay) return;
        self._overlay = $("<div></div>");

        var overlayCss = {
            'width': '100%',
            'min-height': '100%',
            'position': 'fixed',
            'top': 0,
            'left': 0,
            'z-index': Overlay.zindex,
            'background': '#cccccc',
            'text-align': 'center',
            'opacity': opacity
        };

        if ($.browser.msie && $.browser.version.substr(0, 1) < 7) {
            overlayCss.position = "absolute";
            overlayCss.height = dom.pageHeight();
        }
        self._overlay.css(overlayCss);
        $(document.body).append(self._overlay);
    };
    self.show = function () {
        self._createDiv();
        Overlay.zindex++;
        self._overlay.show();
    };
    self.close = function () {
        self._overlay.hide();
        self._overlay.remove();
        self._overlay = undefined;
    };
}
Overlay.zindex = 9999;

/**
 * 判断浏览器及其版本
 * */
function _Index() {
    this.whatExplorer = function () {
        window['MzBrowser'] = {};
        if (MzBrowser.platform)return;
        var ua = window.navigator.userAgent;
        MzBrowser.platform = window.navigator.platform;

        MzBrowser.firefox = ua.indexOf('Firefox') > 0;
        MzBrowser.opera = typeof(window.oper) == 'object';
        MzBrowser.ie = !MzBrowser.opera && ua.indexOf('MSIE') > 0 && window.navigator.product != 'Gecko';
        MzBrowser.mozilla = window.navigator.product == 'Gecko';
        MzBrowser.netscape = window.navigator.vendor == 'Netscape';
        MzBrowser.safari = ua.indexOf('Safari') > -1;

        if (MzBrowser.firefox) {
            var re = /Firefox(\s|\/)(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'firefox'
        }
        else if (MzBrowser.ie) {
            var re = /MSIE( )(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'ie'
        }
        else if (MzBrowser.opera) {
            var re = /Opera(\s|\/)(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'opera'
        }
        else if (MzBrowser.netscape) {
            var re = /Netscape(\s|\/)(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'netscape'
        }
        else if (MzBrowser.safari) {
            var re = /Version(\/)(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'safari'
        }
        else if (MzBrowser.mozilla) {
            var re = /rv(\:)(\d+(\.\d+)?)/;
            MzBrowser.explorer = 'mozilla'
        }
        if ("undefined" != typeof(re) && re.test(ua))
            MzBrowser.version = parseFloat(RegExp.$2);
    };
}