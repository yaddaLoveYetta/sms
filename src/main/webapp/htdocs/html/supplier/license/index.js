/**
 * Created by yadda on 2017/5/8.
 */
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
    var Tree = require('Tree');
    var ClassMapping = require('ClassMapping');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');

    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    // 供应商资质维护tree中显示供应商，物料证件维护tree中显示供应商中标库物料
    var treeClassId = classId == 3010 ? 1005 : classId == 3020 ? 3030 : 0;

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
            refresh();
        }
    });


    var blConfig = {
        'items': [
            {
                text: '新增',
                name: 'add',
            },
            {
                text: '删除',
                name: 'del',
            },
            {
                text: '修改',
                name: 'edit',
            },
            {
                text: '刷新',
                name: 'refresh',
            },
            {
                text: '审核',
                name: 'check',
                items: [{
                    text: '反审核',
                    name: 'unCheck',
                }],
            },
            {
                text: '发送到医院',
                name: 'send',
            }]
    };


    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {

        'add': function (item, index) {
            // 增加供应商证书
            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '新增-供应商资质',
                    width: 700,
                    height: 550,
                    url: $.Url.setQueryString('html/base_edit/index.html', 'classId', classId),
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

        },
        'del': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
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
        'edit': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要删除的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('只能对一条记录进行操作');
                return;
            }

            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '编辑-供应商资质',
                    width: 700,
                    height: 550,
                    url: $.Url.setQueryString('html/base_edit/index.html', {
                        'classId': classId,
                        id: list[0].data.id,
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


        },
        'check': function (item, index) {
            SMS.Tips.info('研发中，敬请期待……', 2000);

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }

            List.check(classId, list, function () {
                refresh();
            });


        },
        'unCheck': function (item, index) {
            SMS.Tips.info('研发中，敬请期待……', 2000);
        },
        'send': function (item, index) {
            // 发送到HRP

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项');
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('只能对一条记录进行操作');
                return;
            }
            //SMS.Tips.info('该功能正在研发中，敬请期待……');

            MessageBox.confirm('确定要将该记录发送给HRP系统?', function (result) {
                if (result) {

                    SMS.Tips.info('研发中，敬请期待……');
                    /*                    List.del(classId, list, function () {
                     refresh();
                     });*/
                }
            });
            return;
        },
        'refresh': function (item, index) {
            refresh();
        },

    });

    function refresh(data) {

        if (data) {

            if (treeClassId === 1005) {
                // 供应商
                conditions['id'] = {
                    'andOr': 'AND',
                    'leftParenTheses': '(',
                    'fieldKey': 'supplier',
                    'logicOperator': '=',
                    'value': data.id,
                    'rightParenTheses': ')',
                    'needConvert': false,
                };
            } else if (treeClassId === 3030) {
                // 中标库
                conditions['id'] = {
                    'andOr': 'AND',
                    'leftParenTheses': '(',
                    'fieldKey': 'materialItem',
                    'logicOperator': '=',
                    'value': data.id,
                    'rightParenTheses': ')',
                    'needConvert': false,
                };
            }
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

    Tree.on({

        onClick: function (data) {
            refresh(data);
        }
    });

    Tree.render(treeClassId);

})();