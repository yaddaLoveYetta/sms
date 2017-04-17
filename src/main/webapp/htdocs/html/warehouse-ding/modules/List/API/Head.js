

/**
* 
* 
*/
define('List/API/Head', function (require, module, exports) {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
  
    var API = SMS.require('API');

    var cache = null;
    var headItems = null; //


    /**
    * 获取表头信息，即列的信息
    */
    function get(fn) {

        //从缓存中读取
        if (cache) { 
            console.log('getHead from cache!');
            fn && fn(cache);
            return;
        }

        var api = new API('bos/forminfo');

        api.get({
            action: 'queryByClassID',
            formClassID: '10107',
            queryType: 1,
            formatted: true,
        });

        api.on('success', function (data, json) {

            console.log('getHead finished');

            cache = data;
            fn && fn(data);

        });

        api.on('fail', function (code, msg, json) {

            var s = $.String.format('{0} (错误码: {1})', msg, code);
            SMS.Tips.error(s);

        });

        api.on('error', function () {
            SMS.Tips.error('网络繁忙，请稍候再试');

        });

    }



    function getItems(fields, key$field) {

        //表头信息
        headItems = headItems || $.Array.keep(fields, function (item, index) {

            var key = item.fieldKey;

            item = $.Object.extend({}, key$field[key], item);

            var mask = item.visible || 0;


            return {
                'text': item.caption,
                'type': item.listStyle,
                'key': key,
                'width': item.showWidth,
                'visible': !!(mask & 2), //转成 boolean
                'lookupType': item.lookupType,
            };
        });

        return headItems;

    }






    return {
        get: get,
        getItems: getItems,
    };

});






    