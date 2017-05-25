/**
 * 列表数据操作 模块
 *
 */
define('List/Operation', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var API = SMS.require('API');


    function post(name, params, fn, msg) {

        //延迟显示。 避免数据很快回来造成的只显示瞬间
        SMS.Tips.loading({
            text: msg || '数据加载中...',
            delay: 500
        });


        var api = new API(name, {

            data: params,

            'success': function (data, json) { //success
                fn && fn(data, json);

            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg, 2000);

            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试', 2000);
            },

        });
        api.post();

    }

    function del(classId, list, fn) {
        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }

        items = items.substr(1);

        var api = new API('template/delItem');

        api.get({

            'classId': classId,
            'items': items

        });

        api.on({
            'success': function (data, json) {
                SMS.Tips.success('删除成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function forbid(classId, list, operateType, fn) {
        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }
        items = items.substr(1);

        if (items == '') {
            return;
        }

        var api = new API('template/forbid');
        api.get({

            'classId': classId,
            'items': items,
            'operateType': operateType

        });

        api.on({
            'success': function (data, json) {
                SMS.Tips.success(operateType === 1 ? '禁用成功' : '反禁用成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function review(classId, list, fn) {

        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }

        items = items.substr(1);

        post('template/checkItem', {
            'classId': classId,
            'items': items
        }, fn);

        /*var api = new API('template/checkItem');
         api.get({

         'classId': classId,
         'items': items

         });

         api.on({
         'success': function (data, json) {
         SMS.Tips.success('审核成功', 2000);
         fn();
         },

         'fail': function (code, msg, json) {
         var s = $.String.format('{0} (错误码: {1})', msg, code);
         SMS.Tips.error(s);
         },

         'error': function () {
         SMS.Tips.error('网络繁忙，请稍候再试');
         }
         });*/
    }

    function unReview(classId, list, fn) {

        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }

        items = items.substr(1);

        var api = new API('template/unCheckItem');
        api.get({

            'classId': classId,
            'items': items

        });

        api.on({
            'success': function (data, json) {
                SMS.Tips.success('反审核成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function send(classId, list, fn) {

        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }

        items = items.substr(1);

        var api = new API('sync/hrp/sendItem');
        api.get({

            'classId': classId,
            'items': items

        });

        api.on({
            'success': function (data, json) {
                SMS.Tips.success('发送到医院成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    return {
        del: del,
        forbid: forbid,
        review: review,
        unReview: unReview,
        send: send,
    };

});

