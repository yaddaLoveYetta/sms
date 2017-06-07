/**
 * 单据详情/编辑页面
 * Created by yadda on 2017/5/12.
 */

;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var $API = require('API');
    var API = SMS.require('API');
    var MessageBox = SMS.require('MessageBox');
    var ButtonListOption = require('ButtonListOption');

    var bl = require('ButtonList');

    var Head = require('Head');
    var Entry = require('Entry');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var id = MiniQuery.Url.getQueryString(window.location.href, 'id');
    var type = MiniQuery.Url.getQueryString(window.location.href, 'type');

    type = parseInt(type || 0);// 0:1:2-查看/新增/编辑-查看时所有字段锁定，新增/编辑时根据模板控制-默认查看

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    var dialog = Iframe.getDialog();

    var blConfig;
    if (dialog) {
        // 对话框中不要工具栏
        blConfig = {
            'items': []
        };
    } else {
        blConfig = ButtonListOption.get(classId, type);
    }

    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    ButtonList.on('click', {
        'optRefresh': function () {
            refresh();
        },
        'optSave': function () {
            Bill.save(function (data) {

            });
        },
        'optPrint': function () {
            codePrint();
        }

    });
// 个体码打印
    function codePrint() {


        var api = new API('invoice/getCode');
        api.post({
            items: id,
        });

        api.on({
            'success': function (data, json) {
                showCode(data);
            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');
            }
        });

        function showCode(data) {
            // 内部函数
            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '个体码打印',
                    width: 700,
                    height: 450,
                    url: $.Url.setQueryString('html/code-print/index.html'),
                    data: {
                        code: data,
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


    }

    //保存
    function save(fn) {

        var valid = true;
        billData = Head.getData();
        var entry = Entry.getData();

        if (billData.errorData && !$.Object.isEmpty(billData.errorData)) {
            Head.showValidInfo(billData.successData, billData.errorData);
            valid = false;
        }

        if (entry.errorData && !$.Object.isEmpty(entry.errorData)) {
            Entry.showValidInfo(entry.errorData);
            valid = false;
        }

        if (!valid) {
            return;
        }

        if (entry.entryData) {
            billData.successData['entry'] = entry.entryData;
        }

        submit(itemId, billData.successData, function (data) {
            itemId = data.id;// 新增成功后记录id，界面变修改逻辑
            if (itemId) {
                SMS.Tips.success("修改发货单成功", 1500);
            }
            SMS.Tips.success("新增发货单成功", 1500);

            fn && fn(data);
        });
    }

    function submit(itemId, data, fn) {

        var action = 'template/addItem';
        if (itemId) {
            action = 'template/editItem';
        }
        var api = new API(action);

        api.post({
            classId: classId,
            id: itemId,
            data: data,
        });

        api.on('success', function (data, json) {

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


    function refresh() {

        SMS.Tips.loading("数据加载中...");
        $API.get({
            classId: classId,
            id: id,
        }, function (data) {
            // 填充数据
            console.log(data);
            Head.render(data, data.data.headData, type);
            Entry.render(data.template, data.data.entryData, type);
            SMS.Tips.success("数据加载成功", 1500);
        });
    }

    refresh();

})();