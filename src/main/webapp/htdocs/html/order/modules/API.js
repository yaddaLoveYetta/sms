

//define('API', function (require, exports, module) {

//    var $ = require('$');
//    var MiniQuery = require('MiniQuery');
//    var SMS = require('SMS');

//    function post(action, data, fn, msg) {

//        //延迟显示。 避免数据很快回来造成的只显示瞬间
//        SMS.Tips.loading({
//            text: msg || '数据加载中，请稍后...',
//            delay: 500
//        });


//        var obj = $.extend(action, data);

//        SMS.API.post('eshop/order', obj, function (data, json) { //成功

//            fn && fn(data, json);

//        }, function (code, msg, json) { //失败

//            SMS.Tips.error(msg, 2000);

//        }, function () { //错误

//            SMS.Tips.error('网络错误，请稍候再试', 2000);
//        });

//    }


//    function list(args, fn) {
//        post({ action: 'list' }, args, fn);
//    }

//    function check(args, fn) {
//        post({ action: 'check' }, args, fn);
//    }

//    function merge(args, fn) {
//        post({ action: 'merge' }, args, fn);
//    }

//    function validate(args, fn) {
//        post({ action: 'validate' }, args, fn);
//    }

//    function split(args, fn) {
//        post({ action: 'split' }, args, fn);
//    }

//    function del(args, fn, msg) {
//        post({ action: 'delete' }, args, fn, msg);
//    }

//    function update(args, fn, msg) {
//        post({ action: 'update' }, args, fn, msg);
//    }

//    return {
//        list: list,
//        check: check,
//        merge: merge,
//        split: split,
//        del: del,
//        update: update
//    }

//});


define('API', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');

    //name: eshop/order, action:check, data:args,
    function post(name, action, data, fn, msg) {

        //延迟显示。 避免数据很快回来造成的只显示瞬间
        SMS.Tips.loading({
            text: msg || '数据加载中...',
            delay: 500
        });

        var obj = $.extend(action, data);

        var api = new API(name, {

            data: obj,

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

    //查询
    function list(args, fn) {
        post('eshop/order', { action: 'list' }, args, fn);
    }

    //审核
    function check(args, fn) {
        post('eshop/order', { action: 'check' }, args, fn);
    }

    //合并
    function merge(args, fn) {
        post('eshop/order', { action: 'merge' }, args, fn);
    }

    //检测
    function validate(args, fn) {
        post('eshop/delivery', { action: 'check' }, args, fn);
    }

    //返审
    function uncheck(args, fn) {
        post('eshop/order', { action: 'uncheck' }, args, fn);

    }

    //拆分
    function split(args, fn) {
        post('eshop/order', { action: 'split' }, args, fn);
    }

    //删除
    function del(args, fn, msg) {
        post('eshop/order', { action: 'delete' }, args, fn, msg);
    }

    //修改
    function edit(args, fn, msg) {
        post('eshop/order', { action: 'orderEdit' }, args, fn, msg);

    }

    //批量修改
    function batchOrderEdit(args, fn, msg) {
        post('eshop/order', { action: 'batchOrderEdit' }, args, fn, msg);

    }

    function update(args, fn, msg) {
        post('eshop/order', { action: 'update' }, args, fn, msg);
    }

    return {
        list: list,
        check: check,
        uncheck: uncheck,
        merge: merge,
        split: split,
        del: del,
        update: update,
        validate: validate,
        edit: edit,
        batchOrderEdit: batchOrderEdit,
    }

});
