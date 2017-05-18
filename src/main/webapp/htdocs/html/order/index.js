/**
 * Created by yadda on 2017/5/8.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var List = require('List');
    var Pager = require('Pager');
    var bl = require('ButtonList');
    var ButtonListOption = require('ButtonListOption');
    var MessageBox = SMS.require('MessageBox');
    var Iframe = SMS.require('Iframe');

    var classId = 2019;
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    //默认配置
    var defaults = {
        pageSize: 10,
        typeId: '',
        pageNo: 1,
        hasBreadcrumbs: true,
        multiSelect: true
    };

    $(txtSimpleSearch).bind('keypress', function (event) {
        if (event.keyCode == 13) {
            /*conditions['name'] = $(txtSimpleSearch).val();*/

            conditions['number'] = {
                'andOr': 'AND',
                'leftParenTheses': '(',
                'fieldKey': 'number',
                'logicOperator': 'like',
                'value': $(txtSimpleSearch).val(),
                'rightParenTheses': ')',
            };


            refresh();
        }
    });


    var blConfig = {
        'items': [
            {
                text: '接单',
                name: 'tick',
            },
            {
                text: '刷新',
                name: 'refresh',
            },
            {
                text: '详情',
                name: 'detail',
            },
            {
                text: '生成发货单',
                name: 'deliver',
            }
        ]
    };


    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {

        'tick': function () {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('只能对一条记录进行操作');
                return;
            }

            if (list[0].data.tickType) {
                SMS.Tips.error('该订单HRP已确认接单，不可修改接单数据');
                return;
            }

            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '接单信息',
                    width: 700,
                    height: 300,
                    url: $.Url.setQueryString('html/order-tick/index.html', {
                        'classId': classId,
                        'orderId': list[0].data.id,
                    }),
                    data: {},
                    button: [
                        {
                            value: '取消',
                            className: 'sms-cancel-btn',
                        },
                        {
                            value: '确定',
                            className: 'sms-submit-btn',
                            callback: function () {
                                dialog.__dispatchEvent('get');
                                var data = dialog.getData();
                                console.log(data);
                                tick(data, function (data) {
                                    SMS.Tips.success("接单成功", 1500);
                                    return true;
                                });

                                return false; // 可能不成功，默认不关闭对话框
                            }
                        }
                    ],
                });

                //默认关闭行为为不提交
                dialog.isSubmit = false;

                dialog.showModal();

                dialog.on({
                    remove: function () {
                        refresh();
                    }
                });

            });
        },
        'detail': function (item, index) {
            // 订单详情
            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('只能对一条记录进行操作');
                return;
            }

            Iframe.open({
                id: classId + '-add-' + list[0].data[List.getPrimaryKey()],
                name: '详情-采购订单',
                url: 'html/order-details/index.html',
                query: {
                    'classId': classId,
                    'id': list[0].data[List.getPrimaryKey()],
                }
            });


        },
        'refresh': function (item, index) {
            refresh();
        },
        'deliver': function (item, index) {

            var done = true;
            //SMS.Tips.info('功能研发中，敬请期待……');

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项', 1500);
                return;
            }
            // 判断订单状态是否符合发货条件
            $.Array.each(list, function (item, index) {
                if (!item.data.confirmTick) {
                    // 供应商没有接单
                    SMS.Tips.error(item.data.number + ' 还未接单，不能发货');
                    done = false;
                    return false;
                }
                if (!item.data.tickType) {
                    // HRP没有确认接单
                    SMS.Tips.error(item.data.number + ' HRP未确认接单，不能发货');
                    done = false;
                    return false;
                }
            });
            // 判断订单数量是否符合发货条件
            $.Array.each(list, function (item, index) {

                $.Array.each(item.data.entry[1], function (row, index) {
                    if (row.invoiceQty || 0 >= row.confirmQty || 0) {
                        // 接单数量已经全部发货
                        SMS.Tips.error(item.data.number + 'seq' + row + '接单数量已发货完毕，不能再发货');
                        done = false;
                        return false;
                    }
                });
            });

            if (!done) {
                // 不满足发货条件
                return;
            }
            var items = ''; // 发货订单主键集合，多个逗号分隔

            for (var item in list) {
                if (list[item]) {
                    items += (',' + list[item].primaryValue);
                }
            }

            items = items.substr(1);

            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '生成发货单',
                    width: $(window).width(),
                    height: $(window).height(),
                    url: $.Url.setQueryString('html/order-deliver/index.html', {
                        'classId': 2020,
                        'items': items,
                    }),
                    data: {},
                    button: [
                        {
                            value: '取消',
                            className: 'sms-cancel-btn',
                        },
                        {
                            value: '确定',
                            className: 'sms-submit-btn',
                            callback: function () {
                                dialog.__dispatchEvent('get');
                                var data = dialog.getData();
                                console.log(data);
                                tick(data, function (data) {
                                    SMS.Tips.success("接单成功", 1500);
                                    return true;
                                });

                                return false; // 可能不成功，默认不关闭对话框
                            }
                        }
                    ],
                });

                //默认关闭行为为不提交
                dialog.isSubmit = false;

                dialog.showModal();

                dialog.on({
                    remove: function () {
                        refresh();
                    }
                });

            });


        }
    });

    //生成发货单
    function deliver() {

    }

    //保存接单数据
    function tick(data, fn) {

        var api = new API('order/tick');
        api.post({
            id: data.id,
            entry: data.entry,
        });

        api.on({
            'success': function (data, json) {
                fn && fn(data);
            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');
            }
        });
    }

    function refresh(data) {

        if (data) {
            conditions['id'] = {
                'andOr': 'AND',
                'leftParenTheses': '(',
                'fieldKey': 'supplier',
                'logicOperator': '=',
                'value': data.id,
                'rightParenTheses': ')',
                'needConvert': false,
            };
        }

        List.render({
            classId: classId,
            pageNo: 1,
            pageSize: defaults.pageSize,
            conditions: conditions,
            multiSelect: defaults.multiSelect
        }, function (total, pageSize) {

            Pager.render({
                size: pageSize,
                total: total,
                change: function (no) {
                    List.render({
                        classId: classId,
                        pageNo: no,
                        pageSize: defaults.pageSize,
                        conditions: conditions,
                        multiSelect: defaults.multiSelect
                    });
                }
            });
        });
    }

    refresh();

})();