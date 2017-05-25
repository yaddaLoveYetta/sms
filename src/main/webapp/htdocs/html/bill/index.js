/**
 * 采购订单生成发货单
 * Created by yadda on 2017/5/12.
 */

;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var $API = SMS.require("API");
    var API = require("API");
    var MessageBox = SMS.require('MessageBox');
    var bl = require('ButtonList');

    var Head = require('Head');
    var Entry = require('Entry');

    var ButtonList = bl.create();
    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var id = MiniQuery.Url.getQueryString(window.location.href, 'id');

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }
    ButtonList.render();

    ButtonList.on('click', {
        'optRefresh': function () {
            Bill.render({
                'classId': classId,
                'items': items
            });
        },
        'optSave': function () {
            Bill.save(function (data) {

            });
        }

    });

    //保存
    function save(fn) {

        var valid = true;
        billData = Head.getData();
        var entry = Entry.getData();

        if (billData.errorData && !$.Object.isEmpty(billData.errorData)) {
            Head.showValidInfo(billData.successData, billData.errorData);
            valid = false;
        }

        if (entry.errorData && !$.Object.isEmpty(entry.errorData)) {
            Entry.showValidInfo(entry.errorData);
            valid = false;
        }

        if (!valid) {
            return;
        }

        if (entry.entryData) {
            billData.successData['entry'] = entry.entryData;
        }

        submit(itemId, billData.successData, function (data) {
            itemId = data.id;// 新增成功后记录id，界面变修改逻辑
            if (itemId) {
                SMS.Tips.success("修改发货单成功", 1500);
            }
            SMS.Tips.success("新增发货单成功", 1500);

            fn && fn(data);
        });
    }

    function submit(itemId, data, fn) {

        var action = 'template/addItem';
        if (itemId) {
            action = 'template/editItem';
        }
        var api = new $API(action);

        api.post({
            classId: classId,
            id: itemId,
            data: data,
        });

        api.on('success', function (data, json) {

            fn && fn(data);

        });

        api.on('fail', function (code, msg, json) {

            var s = $.String.format('{0} (错误码: {1})', msg, code);
            SMS.Tips.error(s);

        });

        api.on('error', function () {
            SMS.Tips.error('网络繁忙，请稍候再试');

        });

    }


    function refresh() {

        SMS.Tips.loading("数据加载中...");
        API.get({
            classId: classId,
            id: id,
        }, function (data) {
            // 填充数据
            console.log(data);
            Head.render(data, data.data.headData, id);
            Entry.render(data.template, data.data.entryData, id);
            SMS.Tips.success("数据加载成功", 1500);
        });

    }

})();