/**
 * 表单模块
 * Created by yadda on 2017/5/12.
 */

define('Bill', function (require, module, exports) {

    var $ = require("$");
    var MiniQuery = require("MiniQuery");
    var SMS = require("SMS");
    var API = require("/API");
    // 完整名称为 Bill/API

    var Head = require('/Head');// 完整名称为 Bill/Head
    var Entry = require('/Entry'); // 完整名称为 Bill/Entry

    var template = {};
    var visibleTemplate = {};
    var billData = {};
    var headData = {};
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
        load(config, function (data) {
            // 填充数据
            console.log(data);

            itemId = data.data.headData[data.template.formClass.primaryKey] || 0; // 单据内码

            Head.render(data, data.data.headData);
            // Entry.render(data.visibleTemplate, data.data.entryData);
            Entry.render(data.template, data.data.entryData);
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

        if (entry) {
            billData['entry'] = entry;
        }

        submit(billData, function (data) {
            fn && fn(data);
        });
    }

    return {
        render: render,
        save: save,
    }
});