;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var API = SMS.require('API');

    var List = require('List');
    var Pager = require('Pager');
    var bl = require('ButtonList');
    var ButtonListOption = require('ButtonListOption');
    var MessageBox = SMS.require('MessageBox');

    // var CascadeNavigator = require('CascadeNavigator');
    var ClassMapping = require('ClassMapping');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};
    var conditionExt = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        SMS.Login.show();
        //return;
    }

    //默认配置
    var defaults = {
        pageSize: 10,
        typeId: '',
        pageNo: 1,
        hasBreadcrumbs: true,
        multiSelect: true
    };

    var firstRender = true;
    var primaryKey;

    var dialog = Iframe.getDialog();

    if (dialog) {
        var data = dialog.getData();
        defaults = $.Object.extend(defaults, data);

        if (defaults.typeId != '') {
            conditions = {classId: defaults.typeId};
        }


        if (data.conditions) {
            // 新增查询条件
            $.Object.extend(conditions, data.conditions);
        }

        dialog.on({
            close: function () {
                //window.top && window.top.$("#div-tips").hide();
                getData();
            }
        });
    }

    function getData() {
        var item = (List.getSelectedItems()[0] && List.getSelectedItems()[0].data) ? List.getSelectedItems()[0].data : {};

        if (item) {
            dialog.setData([{
                'ID': item[primaryKey],
                'name': item.name,
                'number': item.number,
                'all': item, // 保留一份完整数据
            }]);
        }
    }


    $(txtSimpleSearch).bind('keypress', function (event) {
        if (event.keyCode == 13) {
            /*conditions['name'] = $(txtSimpleSearch).val();*/
            refresh();
        }
    });


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
        'add': function (item, index) {

            var index = ClassMapping.getIndex(classId);
            if (index > 0) {
                // 有菜单项的跳转
                Iframe.open(index.first, index.second, {
                    query: {}
                });
            } else {

                var url = ClassMapping.getPage(classId);
                var name = ClassMapping.getTabName(classId) || '';

                if (!url) {
                    // 没有配置编辑页面或不需要编辑功能
                    return;
                }
                Iframe.open({
                    id: classId + '-add-',
                    name: '新增-' + name,
                    url: url,
                    query: {
                        'classId': classId,
                    }
                });
            }

        },
        'delete': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要删除的项');
                return;
            }
            MessageBox.confirm('确定删除选择的项?', function (result) {
                if (result) {
                    List.del(classId, list, function () {
                        refresh();
                    });
                }
            });

        },
        'refresh': function (item, index) {
            refresh();
        },
        'more': function (item, index) {
            ButtonList.toggle(index);
        },
        'check': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('一次只能对一条记录进行操作');
                return;
            }
            List.review(classId, list, function () {
                SMS.Tips.success('审核成功', 2000);
                refresh();
            });


        },
        'unCheck': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('一次只能对一条记录进行操作');
                return;
            }

            List.unReview(classId, list, function () {
                SMS.Tips.success('反审核成功', 2000);
                refresh();
            });
        },
        'send': function (item, index) {
            // 发送到HRP
            if (classId != 1005) {
                //目前基础资料只有供应商可同步回HRP
                return;
            }
            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('一次只能对一条记录进行操作');
                return;
            }
            if (list[0].data.review === 0) {
                SMS.Tips.error('该记录未审核，不可发送');
                return;
            }

            List.send(classId, list, function () {
                SMS.Tips.success('发送成功', 2000);
                refresh();
            });

            /*            MessageBox.confirm('确定要将该记录发送给医院HRP系统?', function (result) {
             if (result) {

             SMS.Tips.info('研发中，敬请期待……', 1500);
             }
             });
             return;*/
        },
        'filter': function (item, index) {
            var items = List.getFilterItems();
            SMS.use('Dialog', function (Dialog) {
                var dialog = new Dialog({
                    title: '数据过滤',
                    url: 'html/base-filter/index.html',
                    data: items,
                    conditionExt: conditionExt,
                    width: 550,
                    button: [{
                        className: 'sms-cancel-btn',
                        value: '取消',
                        callback: function () {
                        }
                    }, {
                        value: '确定',
                        className: 'sms-submit-btn',
                        autofocus: true,
                        callback: function () {
                            this.isSubmit = true;
                            dialog.__dispatchEvent('get');
                            var dialogData = dialog.getData();
                            conditionExt = dialogData;
                            refresh();
                        }
                    }]
                });

                dialog.showModal();
            });
        },
    });

    List.on({
        'click:number': function (data, event) {
            // 编辑
            if (dialog) {
                // 选择界面不触发
                return;
            }

            var url = ClassMapping.getPage(classId);
            var name = ClassMapping.getTabName(classId) || '';

            if (!url) {
                // 没有配置编辑页面或不需要编辑功能
                return;
            }

            var body = data.body;

            Iframe.open({
                id: classId + '-edit-' + body.primaryValue,
                name: '编辑-' + name,
                url: url,
                query: {
                    'id': body.primaryValue,
                    'classId': classId,
                }
            });

        },
    });

    function refresh() {

        var keyworld = $(txtSimpleSearch).val()
        conditions['name'] = "";
        conditions['number'] = "";
        if ($.trim(keyworld) !== "") {
            conditions['name'] = {
                'andOr': 'AND',
                'leftParenTheses': '((',
                'fieldKey': 'name',
                'logicOperator': 'like',
                'value': keyworld,
                'rightParenTheses': ')'
            };
            conditions['number'] = {
                'andOr': 'OR',
                'leftParenTheses': '(',
                'fieldKey': 'number',
                'logicOperator': 'like',
                'value': keyworld,
                'rightParenTheses': '))'
            };
        }

        conditions = $.extend({}, conditions, conditionExt);

        console.log('render');
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

            primaryKey = List.getPrimaryKey();

        });
    }

    refresh();


})();