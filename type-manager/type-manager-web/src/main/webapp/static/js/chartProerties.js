/******************************************************************
 * HighChart公共属性JS
 *
 * @author zoutuo
 * @version 2013-09-29
 *
 * history:
 *
 */
var HChart = new _HChart();

function _HChart() {

    this.xAxis = [
        {
            labels: {
                formatter: function () {
                    return Highcharts.dateFormat("%b %e日", this.value);
                }

            },
            dateTimeLabelFormats: {
                second: '%Y-%m-%d<br/>%H:%M:%S',
                minute: '%Y-%m-%d<br/>%H:%M',
                hour: '%Y-%m-%d<br/>%H:%M',
                day: '%Y<br/>%m-%d',
                week: '%Y<br/>%m-%d',
                month: '%Y-%m',
                year: '%Y'
            }
        }
    ];


    /**
     * 公共button——1小时、3小时、一天等
     * @type {Array}
     */
    this.buttons = [
        {
            type: 'minute',
            count: 60,
            text: '1h'
        },
        {
            type: 'minute',
            count: 180,
            text: '3h'
        },
        {
            type: 'day',
            count: 1,
            text: '1d'
        },
        {
            type: 'week',
            count: 1,
            text: '1w'
        },
        {
            type: 'month',
            count: 1,
            text: '1M'
        },
        {
            type: 'month',
            count: 3,
            text: '3M'
        },
        {
            type: 'month',
            count: 6,
            text: '6M'
        },
        {
            type: 'year',
            count: 1,
            text: '1Y'
        },
        {
            type: 'all',
            text: 'all'
        }
    ];

    this.customColor = [
        'green',//第三个颜色，
        'yellow',//第二个颜色
        'red',//第一个颜色，
        'blue',//第四个颜色
        '#ff1ae1',
        '#183bff',
        '#f28f43',
        '#77a1e5',
        '#c42525',
        '#white',
        '#2effce',
        '#31ffd3',
        '#ba0b35',
        '#000000'
    ];

    /**
     * 按钮主题样式
     */
    this.buttonTheme = { // styles for the buttons
        'stroke-width': 1,
        r: 8,
        style: {
//                    color: '#039',
//                    fontWeight: 'bold'
        },
        states: {
            hover: {
            },
            select: {
                fill: '#039'
            }
        }
    };
}

/**
 * 汉化
 */
Highcharts.setOptions({
    lang: {
        decimalPoint: ".",//小数点符号
        thousandsSep: "",//千位隔位符
        downloadJPEG: "下载 JPEG 图片",
        downloadPDF: "下载 PDF 文件",
        downloadPNG: "下载 PNG 图片",
       /* downloadSVG: "下载 SVG 图片",*/
        printChart: "打印图片",
        rangeSelectorFrom: "可统计时间范围:从",
        rangeSelectorTo: "至",
        rangeSelectorZoom: "",
        loading: "加载中...",
        months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        //['1月/', '2月/', '3月/', '4月/', '5月/', '6月/', '7月/', '8月/', '9月/', '10月/', '11月/', '12月/'],
        //['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
        shortMonths: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
        numericSymbols: ['k', 'M', 'G', 'T', 'P', 'E'],
        printButtonTitle: "导出报表",
        resetZoom: "还原",
        resetZoomTitle: "还原比例 1:1",
        weekdays: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
    }
});


/**
 * 同比/环比正负值颜色区分
 * @param value
 * @param count
 * @return {string}
 */
var colorCount = function (count, value) {
    var returnColorCount = "";
    if (value == '#') {
        return "";
    } else if (value > 0) {
        returnColorCount = "<label style='color:#FF0000'> " + count + ":" + value + "%</label>";
    } else if (value < 0) {
        returnColorCount = "<label style='color:#00FF00'> " + count + ":" + value + "%</label>";
    }
    if (value == 0) {
        returnColorCount = "<label> " + count + ":" + value + "%</label>";
    }
    return returnColorCount;
}

/**
 * 是否显示逗号
 * @param date
 * @param value
 * @return {string}
 */

var isShowComma = function (date, value) {
    var Comma = "";
    if (value == '#' && date == '#') {
        Comma = "";
    } else {
        Comma = ",";
    }
    return Comma;
}

/**
 * 折现部分计算平均耗时以及其单位
 * @param averageTime
 * @returns {string}
 */
function getLineTime(averageTime) {
    var timeStr = "";
    if (averageTime / 3600000 < 1) {
        timeStr = Util.round(averageTime / 60000, 2) + " 分钟#分钟";
    } else if (averageTime / 864000000 > 1) {
        timeStr = Util.round(averageTime / 86400000, 2) + " 天#天";
    } else {
        timeStr = Util.round(averageTime / 3600000, 2) + " 小时#小时";
    }
    return timeStr;
}

/**
 * 折线图部分计算时间单位计算时间
 * @param averageTime
 * @param timeUnit
 * @returns {*|number}
 */
function getLineTimeUnit(averageTime, timeUnit) {
    var normalTime = 0.0;
    if (timeUnit == '分钟') {
        normalTime = (averageTime / 60000);
    } else if (timeUnit == '小时') {
        normalTime = (averageTime / 3600000);
    } else if (timeUnit == '天') {
        normalTime = (averageTime / 86400000);
    }
    return Util.round(normalTime, 2);
}

/**
 * 报表帮助提示
 * @param x
 * @param chartId
 * @returns {{customButton: {x: number, onclick: onclick, symbol: string, symbolSize: number}}}
 */
var chartHelp = function (x, chartId, chartType) {
    var exporting = {
        buttons: {
            customButton: {
                x: -x,
                onclick: function () {
                    showChartContext(chartId, chartType);
                },
                symbol: 'triangle-down',
                symbolSize: 7
            }
        }
    }

    return exporting;
}

/**
 * 显示帮助提示
 * @param chartId
 * @param chartType
 */
function showChartContext(chartId, chartType) {
    var obj = null;
    if(chartType == "node"){
        obj = NodeChartContext.chartId[chartId];
    } else if(chartType == "process"){
        obj = ProcessChartContext.chartId[chartId];
    } else if(chartType == "dept"){
        obj = DeptChartContext.chartId[chartId];
    }
    if(!obj){
        Commons.showError("参数错误", "出错了", 200, null);
        return;
    }
    var address = obj.picAddress;
    var width = obj.width;
    var high = obj.high;
    myOpen(address,width,high);
    //Commons.showInfo(obj.context, obj.title, obj.width, null);
}