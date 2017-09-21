define("List", function (require, exports, module) {

    var $ = require('$');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var div = document.getElementById("div-list");

    var getRecordData = function (config, fn) {

        var conditions = {};
        conditions = $.extend({}, config.pageNo, config.pageSize, config.conditions);


        var api = new API("report/itemLicenseQuery");
        SMS.Tips.loading('数据加载中...');

        api.post(conditions);

        api.on({
            'success': function (data, json) {
                SMS.Tips.success('数据加载成功');
                fn && fn(data.list, data.count);
            },
            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (数据加载异常: {1})', msg, code);
                SMS.Tips.error(s);
            },
            'error': function () {
                SMS.Tips.error('数据加载异常');
            }
        });
    };

    var samples = $.String.getTemplates(div.innerHTML, [{
        name: "table",
        begin: "<!--",
        end: "-->"
    }, {
        name: 'tr',
        begin: '#--tr.begin--#',
        end: '#--tr.end--#',
        outer: '{trs}'
    }, {
        name: 'td',
        begin: '#--td.begin--#',
        end: '#--td.end--#',
        outer: '{tds}'
    }]);

    function render(config, fn) {

        getRecordData(config, function (list, total) {

            div.innerHTML = $.String.format(samples.table, {

                'trs': $.Array.keep(list, function (item, no) {

                    return $.String.format(samples.tr, {

                        'index': no,
                        'tds': $.String.format(samples.td, {
                            'index': no,
                            'itemNumber': item.itemNumber || "",
                            'itemName': item.itemName || "",
                            'itemModel': item.itemModel || "",
                            'manufacturer': item.manufacturer || "",
                            'idNumber': item.idNumber || "",
                            'idName': item.idName || "",
                            'idType': item.idType || "",
                            'authOrg': item.authOrg || "",
                            'supplier': item.supplier || 0,
                        })
                    });
                }).join('')
            });
            fn && fn(total, config.pageSize);
        });
    }

    return {
        render: render
    }
});