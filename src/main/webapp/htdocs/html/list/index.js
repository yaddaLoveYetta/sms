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

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
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


    var dialog = Iframe.getDialog();

    var blConfig;
    if (dialog) {
        // 对话框中不要工具栏
        blConfig = {
            'items': []
        };
    } else {
        blConfig = ButtonListOption.get(classId);
    }

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
                SMS.Tips.error('该订单医院已确认接单，不可修改接单数据');
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
            detailView();
        },
        'refresh': function (item, index) {
            refresh();
        },
        'deliver': function (item, index) {
            deliver();
        },
        'print': function (item, index) {
            print();
        }
    });

    function detailView() {

        // 单据详情
        var list = List.getSelectedItems();

        if (list.length == 0) {
            SMS.Tips.error('请选择要操作的项');
            return;
        }
        if (list.length > 1) {
            SMS.Tips.error('只能对一条记录进行操作');
            return;
        }

        var name = '';
        switch (parseInt(classId)) {
            case 2019:
                name = '采购订单';
                break;
            case 2020:
                name = '发货单';
        }

        Iframe.open({
            id: classId + '-detail-' + list[0].data[List.getPrimaryKey()],
            name: '详情-' + name,
            url: 'html/bill/index.html',
            query: {
                'classId': classId,
                'id': list[0].data[List.getPrimaryKey()],
                'type': 0,
            }
        });
    }

    function deliver() {

        var done = true;
        var list = List.getSelectedItems();

        if (list.length == 0) {
            SMS.Tips.error('请选择要操作的项', 1500);
            return;
        }
        // 判断订单类别是否符合发货条件-代销与非代销订单不能合并发货
        var saleProxy;
        $.Array.each(list, function (item, index) {

            if (index === 0) {
                saleProxy = item.data.saleProxy;
                return true;
            }

            if (item.data.saleProxy !== saleProxy) {
                SMS.Tips.error('代销订单不能与非代销订单合并发货');
                done = false;
                return false;
            }

        });

        if (!done) {
            // 不满足发货条件
            return;
        }
        // 判断订单状态是否符合发货条件
        $.Array.each(list, function (item, index) {
            if (!item.data.confirmTick) {
                // 供应商没有接单
                SMS.Tips.error(item.data.number + ' 供应商未接单，不能发货');
                done = false;
                return false;
            }
            if (!item.data.tickType) {
                // HRP没有确认接单
                SMS.Tips.error(item.data.number + ' 医院未确认接单信息，不能发货');
                done = false;
                return false;
            }
        });
        if (!done) {
            // 不满足发货条件
            return;
        }
        // 判断订单数量是否符合发货条件
        $.Array.each(list, function (item, index) {

            $.Array.each(item.data.entry[1], function (row, index) {
                if ((row.invoiceQty || 0) >= (row.confirmQty || 0)) {
                    // 接单数量已经全部发货
                    SMS.Tips.error(item.data.number + 'seq' + index + '接单数量已发货完毕，不能再发货');
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
                items += (',' + list[item].primaryValue );
            }
        }

        items = items.substr(1);

        SMS.use('Dialog', function (Dialog) {

            var dialog = new Dialog({
                title: '生成发货单',
                width: 1024,
                height: 550,
                url: $.Url.setQueryString('html/order-deliver/index.html', {
                    'classId': 2020,
                    'items': items,
                }),
                data: {},
                button: [],
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

    function print() {
        SMS.use('Dialog', function (Dialog) {

            var dialog = new Dialog({
                title: '接单信息',
                width: 700,
                height: 300,
                url: $.Url.setQueryString('html/code-print/index.html'),
                data: {
                    code: [
                        {
                            text: '1111111111111',
                            name: 'xxxx',
                            model: 'xxxxx',
                            effective:'2017-05-06',
                            batch: 'xxxxxx',
                        },
                        {
                            text: '2222222222222',
                            name: 'yyyy',
                            model: 'yyy',
                            effective:'2017-05-06',
                            batch: 'yyyyy',
                        },
                        {
                            text: '3333333333333',
                            name: 'zzzz',
                            model: 'zzzzzzz',
                            effective:'2017-05-06',
                            batch: 'zzzz',
                        }
                    ],
                },
                button: [
                    {
                        value: '取消',
                        className: 'sms-cancel-btn',
                    },
                    {
                        value: '确定',
                        className: 'sms-submit-btn',
                        callback: function () {
                            return true;
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