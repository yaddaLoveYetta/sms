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
                text: '发货',
                name: 'outStock',
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

            if (list[0].tickType) {
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
        'outStock': function (item, index) {
            SMS.Tips.info('功能研发中，敬请期待……');
        }
    });

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