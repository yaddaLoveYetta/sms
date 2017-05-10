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

    var classId = 1019;
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
            refresh();
        }
    });


    var blConfig = {
        'items': [
            {
                text: '添加',
                name: 'add',
            },
            {
                text: '删除',
                name: 'del',
            },
            {
                text: '编辑',
                name: 'edit',
            }]
    };


    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {

        'add': function (item, index) {
            // 增加供应商证书
            var item = Tree.getSelectedNodes();
            add();
        },
        'delete': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要删除的项');
                return;
            }
            if (confirm('确定删除选择的项')) {
                List.del(classId, list, function () {
                    refresh();
                });
            }
        },
        'refresh': function (item, index) {
            refresh();
        },

    });

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


    function add() {

        SMS.use('Dialog', function (Dialog) {

            var dialog = new Dialog({
                title: '新增-供应商资质',
                width: 600,
                height: 500,
                url: $.Url.setQueryString('html/base_edit/index.html', 'classId', 1019),
                data: {},
                button: [

                ],
            });

            //默认关闭行为为不提交
            dialog.isSubmit = false;

            dialog.showModal();

        });

    }

    Tree.on({

        onClick: function (data) {
            refresh(data);
        }
    });

    Tree.render();

})();