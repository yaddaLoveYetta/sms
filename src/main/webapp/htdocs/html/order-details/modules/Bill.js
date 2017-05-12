/**
 * 表单模块
 * Created by yadda on 2017/5/12.
 */


define('Bill', function (require, module, exports) {

    var $ = require("$");
    var MiniQuery = require("MiniQuery");
    var SMS = require("SMS");
    var API = require("/API");
    // 完整名称为 List/API


    function load(config, fn) {
        SMS.Tips.loading("数据加载中...");
        API.get({
            classId: config.classId,
            id: config.id,
        }, function (data) {
            SMS.Tips.success("数据加载成功", 1500);
            var total = data.body.total;
            fn && fn(data, total);
        });
    }


    function render(config) {
        load(config, function (data) {
            // 填充数据
            console.log(data);
        });
    }

    return {
        render: render,
    }
});