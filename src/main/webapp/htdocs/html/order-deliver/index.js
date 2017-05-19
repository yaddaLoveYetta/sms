/**
 * 采购订单生成发货单
 * Created by yadda on 2017/5/12.
 */

;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var MessageBox = SMS.require('MessageBox');
    var Iframe = SMS.require('Iframe');

    var Bill = require('Bill');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var items = MiniQuery.Url.getQueryString(window.location.href, 'items');

    var conditions = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    Bill.render({
        'classId': classId,
        'items': items
    });

})();