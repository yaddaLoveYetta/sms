﻿/**
 * API模块
 */
define('API', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var Multitask = SMS.require('Multitask');

    var Template = require('/Template');
    //完整名称为 API/Template
    var Data = require('/Data');
    //完整名称为 API/Data

    function get(config, fn) {

        var tasks = [{
            fn: Template.get,
            args: [{
                'classId': config.classId,
                'type':config.type,
            }]
        }, {
            fn: Data.get,
            args: [{
                'classId': config.classId,
                'id': config.id,
                'type':config.type,
            }]
        }];

        //并行发起请求
        Multitask.concurrency(tasks, function (list) {

            var template = list[0];
            var valueData = list[1];

            console.dir(list);

            var formFields = Template.getTemplate(template.formFields); // 整理模板
            var data = Data.getValueItems(valueData, formFields, template); // 处理数据


            var visibleTemplate = {}; // 过滤出可显示的模板

            $.Object.each(formFields, function (pageIndex, pageData) {

                visibleTemplate[pageIndex] = $.Array.grep(pageData, function (item, key) {
                    return item.visible;
                })

            });


            fn && fn({
                template: template,
                visibleTemplate: visibleTemplate,
                data: data,
            });
        });

    };

    return {
        get: get
    };
});