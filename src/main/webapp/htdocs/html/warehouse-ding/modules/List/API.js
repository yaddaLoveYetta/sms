

/**
* 
* 
*/
define('List/API', function (require, module, exports) {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var Multitask = SMS.require('Multitask');

    var Head = require('/Head'); //完整名称为 List/API/Head
    var Body = require('/Body'); //完整名称为 List/API/Body


    function get(config, fn) {

        var tasks = [
            Head.get,
            {
                fn: Body.get,
                args: [{
                    'pageNo':config.pageNo,
                    'pageSize': config.pageSize,
                }]
            }
        ];


        //并行发起请求
        Multitask.concurrency(tasks, function (list) {

            var headData = list[0];
            var bodyData = list[1];
            console.dir(list);

            var headItems = Head.getItems(
                    bodyData['fieldShow'],
                    headData.formFields[0]);

            var bodyItems = Body.getItems(bodyData.items, headItems, headData.formClass.primaryKey);

            fn && fn({
                checkbox: true,

                primaryKey: headData.formClass.primaryKey,

                head: {
                    //过滤出 visible: true 的项
                    'items': $.Array.grep(headItems, function (item, index) {
                        return item.visible;
                    }),
                },
                body: {
                    'total': bodyData.total,

                    'items': $.Array.keep(bodyItems, function (row, no) { //行
                       
                        //过滤出 visible: true 的项
                        row.items = $.Array.grep(row.items, function (item, index) { //列
                            var field = headItems[index];
                            return field.visible;
                        });

                        return row;
                    }),
                },

            });
        });

    }



    return {
        get: get,
    };

});






    