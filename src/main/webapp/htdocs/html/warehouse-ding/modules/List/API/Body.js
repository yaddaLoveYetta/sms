

/**
* 
* 
*/
define('List/API/Body', function (require, module, exports) {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var API = SMS.require('API');

    var fields = null;


    /**
    * 获取表体信息，即行的信息
    */
    function get(config, fn) {

        var pageNo = config.pageNo;

        var api = new API('bos/baseitem', {
            query: {
                'action': 'advanceQuery'
            },
        });

        api.post({
            data: {
                'classID': 10107,
                'pageNo': pageNo,
                'pageSize': config.pageSize,
                'fieldShow': pageNo == 1,
                'userID': 1,
            },
        });

        api.on({
            'success': function (data, json) {

                if (!fields) {
                    fields = data['fieldShow'];
                }
                else {
                    data['fieldShow'] = fields;
                }

                console.log('getBody finished');
                fn && fn(data, json);
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


    function getItems(list, fields, primaryKey) {

        //表体信息
        return $.Array.keep(list, function (item, index) { //行

            return {
                'disabled': index == 0 ? item['isDisabled'] : true,
                'data': item,
                'primaryValue': item[primaryKey], //主键对应的值

                'items': $.Array.keep(fields, function (field, no) { //列

                    var key = field.key;
                   
                    //后台的一种规定，很蛋疼
                    if (field.lookupType > 0) { //lookupType 不为 0 时，说明是引用类型
                        key = key + '_DspName'; //此时要显示的字段为 key + '_DspName'
                    }

                    var value = item[key];

                    return {
                        'key': key,
                        'value': value,
                    };
                }),
            };


        });

    }



    return {
        get: get,
        getItems: getItems,
    };

});






    