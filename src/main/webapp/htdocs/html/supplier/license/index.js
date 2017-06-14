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

    var sysName = classId == 3010 ? '供应商资质' : '物料证件'

    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};
    var treeFilter;

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
                icon: '../../../css/main/img/add.png',
            },
            {
                text: '删除',
                name: 'del',
                icon: '../../../css/main/img/delete.png',
            },
            {
                text: '修改',
                name: 'edit',
                icon: '../../../css/main/img/edit.png',
            },
            {
                text: '刷新',
                name: 'refresh',
                icon: '../../../css/main/img/refresh.png',
            },
            {
                text: '审核',
                name: 'check',
                icon: '../../../css/main/img/check.png',
                items: [{
                    text: '反审核',
                    name: 'unCheck',
                    icon: '../../../css/main/img/uncheck.png',
                }],
            },
            {
                text: '上传附件',
                name: 'upload',
                icon: '../../../css/main/img/upload.png',
            },
            {
                text: '发送到医院',
                name: 'send',
                icon: '../../../css/main/img/send.png',
            }]
    };


    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {

        'add': function (item, index) {
            // 增加
            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '新增-' + sysName,
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
                        SMS.Tips.success("删除成功", 1500);
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
                    title: '编辑-' + sysName,
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
                SMS.Tips.success("审核成功", 1500);
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
                SMS.Tips.success("反审核成功", 1500);
                refresh();
            });
        },
        'upload': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要操作的项', 1500);
                return;
            }
            if (list.length > 1) {
                SMS.Tips.error('一次只能对一条记录进行操作', 1500);
                return;
            }

            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '上传附件-' + sysName,
                    width: 500,
                    height: 300,
                    url: $.Url.setQueryString('html/supplier/upload/index.html'),
                    data: {
                        'classId': classId,
                        id: list[0].data.id,
                    },
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
        'send': function (item, index) {
            // 发送到HRP

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
            MessageBox.confirm('确定要将该记录发送给医院?', function (result) {
                if (result) {
                    List.send(classId, list, function () {
                        SMS.Tips.success('发送成功', 2000);
                        refresh();
                    });
                }
            });
        },
        'refresh': function (item, index) {
            refresh();
        },

    });

    function refresh() {

        conditions = {};

        var keyWorld = $(txtSimpleSearch).val()

        if ($.trim(keyWorld) !== "") {
            conditions['name'] = {
                'andOr': 'AND',
                'leftParenTheses': '((',
                'fieldKey': 'name',
                'logicOperator': 'like',
                'value': keyWorld,
                'rightParenTheses': ')'
            };
            conditions['number'] = {
                'andOr': 'OR',
                'leftParenTheses': '(',
                'fieldKey': 'number',
                'logicOperator': 'like',
                'value': keyWorld,
                'rightParenTheses': '))'
            };
        }

        if (treeFilter) {

            if (treeClassId === 1005) {
                // 供应商
                conditions['id'] = {
                    'andOr': 'AND',
                    'leftParenTheses': '(',
                    'fieldKey': 'supplier',
                    'logicOperator': '=',
                    'value': treeFilter.id,
                    'rightParenTheses': ')',
                    'needConvert': false,
                };
            } else if (treeClassId === 3030) {
                // 中标库
                conditions['id'] = {
                    'andOr': 'AND',
                    'leftParenTheses': '(',
                    'fieldKey': 'material',
                    'logicOperator': '=',
                    'value': treeFilter.id,
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

    List.on({
        'row.item.click': function (data, event) {
            // 子表行操作
            console(data);
            var type = data.operate;
            if (type == 1) {
                //删除子表行
                var primaryKey = data.body.items[data.col].value[data.entryRow].primaryKey;

                var obj = {};
                obj[primaryKey] = data.body.items[data.col].value[data.entryRow].primaryValue;

                var para = {
                    classId: classId,
                    itemId: data.body.primaryValue,
                    data: {
                        entry: {
                            "1": [
                                {
                                    flag: 0,// 删除改行
                                    data: obj
                                }
                            ],
                        }
                    }
                };

                list.edit(para, function () {
                    refresh();
                });
                
            } else if (type == 2) {
                // 下载子表行附件
            }

        },
    });
    Tree.on({

        onClick: function (data) {

            if (data.id === 0) {
                // 根节点data.id==0,不过滤
                data = null;
            }
            treeFilter = data;
            refresh();
        }
    });

    Tree.render(treeClassId);

})();