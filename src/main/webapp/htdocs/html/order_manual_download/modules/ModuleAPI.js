


define('ModuleAPI', function(require, module, exports) {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    function get(action, data, fn, msg) {

        var obj = $.extend(action, data);

        KERP.API.get('eshop/shop', obj, function(data, json) { //成功
            if (data && data.items <= 0) {
                return;
            }

            var items = $.Array.keep(data.items, function(item) {
                return $.extend({}, item, { checked: false });
            });

            fn && fn(items, json);

        }, function(code, msg, json) { //失败

            KERP.Tips.error(msg, 2000);

        }, function() { //错误

            KERP.Tips.error('网络错误，请稍候再试', 2000);
        });
    }

    function post(action, data, fn, msg) {

        //延迟显示。 避免数据很快回来造成的只显示瞬间
        KERP.Tips.loading({
            text: msg || '数据加载中，请稍后...',
            delay: 500
        });


        var obj = $.extend(action, data);

        KERP.API.post('eshop/task', obj, function(data, json) { //成功

            fn && fn(data, json);

        }, function(code, msg, json) { //失败

            KERP.Tips.error(msg, 2000);

        }, function() { //错误

            KERP.Tips.error('网络错误，请稍候再试', 2000);
        });

    }

    function list(args, fn) {
        get({ action: 'list' }, args, fn);
    }

    function addTask(args, fn) {
        post({ action: 'addTask' }, args, fn);
    }

    return {
        list: list,
        addTask: addTask
    }


});