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
    var bl = require('ButtonList');
    var Bill = require('Bill');

    var ButtonList = bl.create();
    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var items = MiniQuery.Url.getQueryString(window.location.href, 'items');

    var conditions = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }
    ButtonList.render();

    ButtonList.on({
        'optRefresh': function () {
            Bill.render({
                'classId': classId,
                'items': items
            });
        },
        'optSave': function () {
            Bill.save(function (data) {
                SMS.Tips.success("发货成功", 1500);
            });
        }

    });
    Bill.render({
        'classId': classId,
        'items': items
    });

})();