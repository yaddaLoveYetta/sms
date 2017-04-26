;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var API = SMS.require('API');

    var List = require('List');
    var Pager = require('Pager');
    var bl = require('ButtonList');
    var MessageBox = SMS.require('MessageBox');

    // var CascadeNavigator = require('CascadeNavigator');
    var ClassMapping = require('ClassMapping');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};

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

        if (defaults.typeID != '') {
            conditions = {classId: defaults.typeId};
        }

        dialog.on({
            close: function () {
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
                'number': item.number
            }]);
        }
    }


    $(txtSimpleSearch).bind('keypress', function (event) {
        if (event.keyCode == 13) {
            conditions['name'] = $(txtSimpleSearch).val();
            refresh();
        }
    });


    var blConfig;
    if (classId == 1001) { // 用户
        blConfig = {
            'items': [{
                text: '新增',
                name: 'add',
            }, {
                text: '删除',
                name: 'delete',
            }, {
                text: '刷新',
                name: 'refresh',
            }]
        };
    } else if (classId == 1002) {
        blConfig = {
            'items': [{
                text: '这是一个按钮',
                name: 'add',
            }, {
                text: '这是另一个按钮',
                name: 'delete',
            }, {
                text: '刷新',
                name: 'refresh',
            }, {
                text: '更多',
                name: 'more',
                cssClass: 'btn-more',
                items: [{
                    text: '导出',
                    name: 'export'
                },
                    {
                        text: '导入',
                        name: 'import'
                    }
                ]
            }]
        };
    }

    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'add': function (item, index) {
            var index = ClassMapping.getIndex(classId);
            Iframe.open(index.first, index.second, {
                query: {}
            });
        },
        'add-category': function (item, index) {
            if (classId == 10110) {
                Iframe.open(1, 6, {
                    query: {}
                });
            }
            else {
                Iframe.open(1, 6, {
                    query: {
                        classID: classId
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
            if (confirm('确定删除选择的项')) {
                List.del(classId, list, function () {
                    refresh();
                });
            }
        },
        'refresh': function (item, index) {
            refresh();
        },
        'disable': function (item, index) {
            var list = List.getSelectedItems();
            if (list.length == 0) {
                SMS.Tips.error('请选择要禁用的项');
                return;
            }
            List.forbid(classId, list, 1, function () {
                refresh();
            });
        },
        'enable': function (item, index) {
            var list = List.getSelectedItems();
            if (list.length == 0) {
                SMS.Tips.error('请选择要反禁用的项');
                return;
            }
            List.forbid(classId, list, 0, function () {
                refresh();
            });
        },
        'export': function (item, index) {

        },
        'import': function (item, index) {

        },
        'setting': function (item, index) {

        },
        'more': function (item, index) {
            ButtonList.toggle(index);
        },
        'copy': function (item, index) {

        },
    });

    List.on({
        'click:number': function (data, event) {
            // 编辑
            if (dialog) {
                // 选择界面不触发
                return;
            }

            var url = ClassMapping.getEditPage(classId);
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