/**
 * 表单模块
 * Created by yadda on 2017/5/12.
 */

define('Bill', function (require, module, exports) {

    var $ = require("$");
    var MiniQuery = require("MiniQuery");
    var SMS = require("SMS");
    var $API = SMS.require("API");

    var API = require("/API");
    // 完整名称为 Bill/API

    var Head = require('/Head');// 完整名称为 Bill/Head
    var Entry = require('/Entry'); // 完整名称为 Bill/Entry

    var template = {};
    var visibleTemplate = {};
    var billData = {};
    var headData = {};
    var classId;
    var itemId;

    function load(config, fn) {
        SMS.Tips.loading("数据加载中...");
        API.get({
            classId: config.classId,
            items: config.items,
        }, function (data) {
            SMS.Tips.success("数据加载成功", 1500);
            fn && fn(data);
        });
    }

    function render(config) {

        classId = config.classId;

        load(config, function (data) {
            // 填充数据
            console.log(data);

            itemId = data.data.headData[data.template.formClass.primaryKey] || 0; // 单据内码

            Head.render(data, data.data.headData, itemId);
            // Entry.render(data.visibleTemplate, data.data.entryData);
            Entry.render(data.template, data.data.entryData,itemId);
        });
    }

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


    return {
        render: render,
        save: save,
    }
});