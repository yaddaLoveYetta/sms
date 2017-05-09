/**
 * Created by yadda on 2017/5/8.
 */

define('Tree', function (require, module, exports) {

    var $ = require('$');
    var SMS = require("SMS");
    var MiniQuery = require('MiniQuery');
    var API = SMS.require('API');
    var Pager = require('Pager');

    var emitter = MiniQuery.Event.create();
    var container = document.getElementById('tree');

    //默认配置
    var defaults = {
        pageSize: 10,
        pageNo: 1,
    };

    function load(config, fn) {


        var api = new API('template/getItems');

        var params = {
            'classId': config.classId,
            'pageNo': config.pageNo,
            'pageSize': config.pageSize,
            'condition': config.conditions.length > 0 ? conditions : '',
        };

        api.post(params);

        api.on({
            'success': function (data, json) {
                fn && fn(data, config.pageSize);
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });


        /*        var treeNodes = [
         {"id": 1, "pId": 0, "name": "test1"},
         {"id": 11, "pId": 1, "name": "test11"},
         {"id": 12, "pId": 1, "name": "test12"},
         {"id": 111, "pId": 11, "name": "test111"},
         ];

         fn && fn(treeNodes);*/
    };

    function render() {

        load({
            classId: 1005,
            pageNo: 1,
            pageSize: defaults.pageSize,
            conditions: [],
        }, function (data, pageSize) {

            Pager.render({
                size: pageSize,
                total: data.count,
                change: function (no) {
                    List.render({
                        classId: classId,
                        pageNo: no,
                        pageSize: defaults.pageSize,
                        conditions: [],
                    });
                }
            });

            SMS.use('ZTree', function (zTree) {

                var tree = new zTree({
                    selector: '#tree',
                    data: data.list,
                });

                tree.on({

                    onClick: function (event, treeId, treeNode) {
                        console.log(treeNode.id + ", " + treeNode.name);
                        emitter.fire("onClick", [treeNode]);
                    },
                });

            });
        });

    };


    return {
        render: render,
        on: emitter.on.bind(emitter),
    };
});
