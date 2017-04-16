/******************************************************************
 * 动态加载公共类
 *
 * @author zoutuo
 * @version 2013-11-06
 *
 * history:
 *
 */
var nodeChartPage = 6;//节点报表页数
var processChartPage = 6;//流程报表页数
var deptChartPage = 2;//部门报表页数

var iHeight = 0;
var iTop = 0;
var clientHeight = 0;
var iIntervalId = null;
var itemsSize = 0;
var pageNo = 1;   // 当前页数，默认设为第 1 页
var pageSize = 4; // 每页显示的数量
/**
 * 动态加载公共方法
 * @type {_lazyLoad}
 */
var LazyLoad = new _lazyLoad();
function _lazyLoad() {
    /**
     * 重新初始化方法(用于点击查询按钮后)
     */
    this.reInit = function () {
        iHeight = 0;
        iTop = 0;
        clientHeight = 0;
        iIntervalId = null;
        itemsSize = 0;
        pageNo = 1;   // 当前页数，默认设为第 1 页
        pageSize = 4; // 每页显示的数量
    };

    /**
     * 取得当前页面显示所占用的高度
     */
    this.getPageHeight = function () {
        if (document.body.clientHeight && document.documentElement.clientHeight) {
            clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
        } else {
            clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
        }

        iHeight = Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    };

    /**
     * 判断滚动条是否到达底部
     * @return {boolean}
     */
    this.reachBottom = function () {
        var scrollTop = 0;
        if (document.documentElement && document.documentElement.scrollTop) {
            scrollTop = document.documentElement.scrollTop;
        } else if (document.body) {
            scrollTop = document.body.scrollTop;
        }
        if ((scrollTop > 0) && (scrollTop + this.clientHeight == this.iHeight)) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * 检测事件，检测滚动条是否接近或到达页面的底部区域，0.99是为了更接近底部时
     * @param num 页面传来的数，表示该页面有几页
     *              对应本JS上方的nodeChartPage、processChartPage、deptChartPage
     * @private
     */
    this._onScroll = function (num) {
        iTop = document.documentElement.scrollTop + document.body.scrollTop;
        this.getPageHeight();
        if (((iTop + clientHeight) > parseInt(iHeight * 0.99)) || this.reachBottom()) {
            if (pageNo >= num) {
                window.clearInterval(iIntervalId);
                return;
            }
            show();
        }
    };
}