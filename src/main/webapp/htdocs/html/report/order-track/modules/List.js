define("List", function (require, exports, module) {

    var $ = require('$');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var div = document.getElementById("div-list");
    var heads = {
        "2020": [
            {key: 'orderSeq', value: '订单行号'},
            {key: 'oustockNo', value: '发货单号'},
            {key: 'date', value: '发货日期'},
            {key: 'metarial', value: '物料编号'},
            {key: 'metarialName', value: '物料名称'},
            {key: 'unit', value: '单位'},
            {key: 'qty', value: '发货数量'},
            {key: 'orderQty', value: '订单数量'},
            {key: 'noneOutStockQty', value: '未发货数量'},
            {key: 'logistic', value: '物流公司'},
            {key: 'logisticNo', value: '物流单号'}],
        "2021": [
            {key: 'orderSeq', value: '订单行号'},
            {key: 'oustockNo', value: '发货单号'},
            {key: 'date', value: '发货日期'},
            {key: 'metarial', value: '物料编号'},
            {key: 'metarialName', value: '物料名称'},
            {key: 'unit', value: '单位'},
            {key: 'qty', value: '发货数量'},
            {key: 'orderQty', value: '订单数量'},
            {key: 'noneOutStockQty', value: '未发货数量'},
            {key: 'logistic', value: '物流公司'},
            {key: 'logisticNo', value: '物流单号'}],
        "2022": [
            {key: 'orderSeq', value: '订单行号'},
            {key: 'oustockNo', value: '发货单号'},
            {key: 'date', value: '发货日期'},
            {key: 'metarial', value: '物料编号'},
            {key: 'metarialName', value: '物料名称'},
            {key: 'unit', value: '单位'},
            {key: 'qty', value: '发货数量'},
            {key: 'orderQty', value: '订单数量'},
            {key: 'noneOutStockQty', value: '未发货数量'},
            {key: 'logistic', value: '物流公司'},
            {key: 'logisticNo', value: '物流单号'}],
        "2023": [
            {key: 'orderSeq', value: '订单行号'},
            {key: 'oustockNo', value: '发货单号'},
            {key: 'date', value: '发货日期'},
            {key: 'metarial', value: '物料编号'},
            {key: 'metarialName', value: '物料名称'},
            {key: 'unit', value: '单位'},
            {key: 'qty', value: '发货数量'},
            {key: 'orderQty', value: '订单数量'},
            {key: 'noneOutStockQty', value: '未发货数量'},
            {key: 'logistic', value: '物流公司'},
            {key: 'logisticNo', value: '物流单号'}],
        stock: [],
    };

    var getRecordData = function (config, fn) {

        var api = new API("report/traceQuery");

        SMS.Tips.loading('数据加载中...');

        api.post(config);

        api.on({
            'success': function (data, json) {
                SMS.Tips.success('数据加载成功', 1500);
                fn && fn(data.list, data.total);
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
        name: 'th',
        begin: ' #--th.begin--#',
        end: ' #--th.end--#',
        outer: '{ths}'
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
        div.innerHTML = '';
        getRecordData(config, function (list, total) {

            div.innerHTML = $.String.format(samples.table, {

                'ths': $.Array.keep(heads[config.classId], function (item, no) {
                    return $.String.format(samples.th, {
                        th: item.value,
                    });
                }).join(''),

                'trs': $.Array.keep(list, function (rowData, no) {

                    return $.String.format(samples.tr, {

                        'index': no,
                        'tds': $.Array.keep(heads[config.classId], function (item, no) {

                            return $.String.format(samples.td, {
                                'td': rowData[item.key],
                            });

                        }).join(''),
                    });
                }).join('')
            });

            if (config.classId == 2020) {
                $('#tab-container').easytabs({
                    defaultTab: 'li:first-child'
                });
            }


            // $('#tab-container').easytabs();

            /*            $(document).ready(function () {
             $('#tab-container').easytabs();
             });*/

            //sumTdTotal();
            fn && fn(total, config.pageSize);
        });
    }

    function sumTdTotal() {
        // 求和运算
        var needtotalTds = $("#div-list table>tfoot td[needtotal='true']");
        var tbodytrLen = $("#div-list table>tbody>tr").length;
        if (needtotalTds.length <= 0 || tbodytrLen === 0) {
            $("#div-list table>tfoot").hide();
        } else {
            needtotalTds.each(function (index, item) {
                var key = $(item).attr("totalkey");
                var totalArray = $("#div-list table>tbody td[key='" + key + "']").map(function () {
                    return $(this).html();
                }).get();
                var totalValue = $.Array.sum(totalArray);
                $(item).html(totalValue.toFixed(2));
            });
        }
    }

    return {
        render: render
    }
});