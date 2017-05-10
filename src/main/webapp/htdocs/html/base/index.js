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
                'all':item, // 保留一份完整数据
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
    }else if (classId == 2019) { // 用户
        blConfig = {
                'items': [
                {
                	  text: '接单',
                      name: 'tick',
                },
               {
                    text: '刷新',
                    name: 'refresh',
                }]
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

            if(list.length == 0) {
                SMS.Tips.error('请选择要删除的项');
                return;
            }
            MessageBox.confirm('确定删除选择的项?', function(result) {
                if(result) {
                    List.del(classID, list, function() {
                        refresh();
                    });
                }
            });

        },
        'refresh': function (item, index) {
            refresh();
        },
        'tick':function (item, index) {
        	var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择接单项');
                return;
            }
            if(list.length > 1){
            	SMS.Tips.error('只能选择一条订单接单');
            }
        	
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
                    name: '' + name,
                    url: url,
                    query: {
                        'classId': classId,
                    }
                });
            }
        },
        'more': function (item, index) {
            ButtonList.toggle(index);
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