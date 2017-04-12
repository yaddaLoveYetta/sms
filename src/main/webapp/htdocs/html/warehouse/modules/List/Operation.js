

/**
* 列表数据操作 模块
* 
*/
define('List/Operation', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var API = KERP.require('API');

    function del(classID, list, fn) {
        var items = '';
        for (var item in list) {
            if (list[item]) {
                items += (',' + list[item].primaryValue);
            }
        }

        items = items.substr(1);

        var api = new API('bos/baseitem');
        api.get({
            action: 'delete',
            data: {
                'classID': classID,
                'items': items
            }
        });

        api.on({
            'success': function (data, json) {
                KERP.Tips.success('删除成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                KERP.Tips.error(s);
            },

            'error': function () {
                KERP.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function forbid(classID, list, operateType, fn) {

        var items = new Array();
        for (var item in list) {
            if (list[item]) {
                items.push(list[item].primaryValue);
            }
        }

        if (!items) {
            return;
        }

        var api = new API('bos/baseitem');
        api.get({
            action: 'forbid',
            data: {
                'classID': classID,
                'itemID': items[0],
                'operateType': operateType
            }
        });

        api.on({
            'success': function (data, json) {
                KERP.Tips.success(operateType === 1 ? '禁用成功' : '反禁用成功', 2000);
                fn();
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                KERP.Tips.error(s);
            },

            'error': function () {
                KERP.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }



    return {
        del: del,
        forbid: forbid,
    };

});






