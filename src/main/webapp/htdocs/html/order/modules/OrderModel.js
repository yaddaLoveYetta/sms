


define('OrderModel', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var list = [
        {
            displayName: '订单ID',
            fieldName: 'orderID'
        },
        {
            displayName: '付款时间',
            fieldName: 'payTime'
        },
        {
            displayName: '昵称',
            fieldName: 'buyerNick'
        }
    ];

    function load(fn) {
        fn(list);
    }

    return {
        load: load
    }

});