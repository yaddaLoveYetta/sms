/**
 * 工单量图表
 *
 */
define('Data', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');

    function getData(fn) {

        var api = new API("report/getFixedRecord");

        //SMS.Tips.loading('数据加载中...');

        api.get({
            pageNo: 1,
            pageSize: 20,
        });

        api.on({
            'success': function (data, json) {
                //SMS.Tips.success('数据加载成功', 1500);
                fn && fn(data);
            },
            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (数据加载异常: {1})', msg, code);
                SMS.Tips.error(s);
            },
            'error': function () {
                SMS.Tips.error('数据加载异常');
            }
        });

    }

    return {
        getData: getData
    };

});

