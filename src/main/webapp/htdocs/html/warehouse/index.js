

; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var API = SMS.require('API');

    var List = require('List');
    var Pager = require('Pager');
    var ButtonList = require('ButtonList');
   // var CascadeNavigator = require('CascadeNavigator');
    var ClassMapping = require('ClassMapping');

    var classID = MiniQuery.Url.getQueryString(window.location.href, 'classID');
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};

    //默认配置
    var defaults = {
        pageSize: 10,
        typeID: '',
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
            conditions = {classID: defaults.typeID};
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

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'add': function (item, index) {
            var index = ClassMapping.getIndex(classID);
            Iframe.open(index.first, index.second, {
                query: {
                    
                }
            });
        },
        'add-category': function (item, index) {
            if (classID == 10110) {
                Iframe.open(1, 6, {
                    query: {
                        
                    }
                });
            }
            else {
                Iframe.open(1, 6, {
                    query: {
                        classID: classID
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
                List.del(classID, list, function () {
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
            List.forbid(classID, list, 1, function () {
                refresh();
            });
        },
        'enable': function (item, index) {
            var list = List.getSelectedItems();
            if (list.length == 0) {
                SMS.Tips.error('请选择要反禁用的项');
                return;
            }
            List.forbid(classID, list, 0, function () {
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

    //CascadeNavigator.render(classID);

    //CascadeNavigator.on('change', function (list) {
    //    if (firstRender) {
    //        firstRender = !firstRender;
    //        return;
    //    }

    //    var length = list.length;
    //    if (length == 1) {
    //        conditions['parentID'] = '';
    //    }
    //    else{
    //        conditions['parentID'] = list[length - 1]['groupID'];
    //    }

    //    $(txtSimpleSearch)[0].value = '';
    //    conditions['name'] = '';

    //    refresh();
    //});
    

    List.on({
        'click:number': function (data, event) {
            var body = data.body;
            var index = ClassMapping.getIndex(classID);

            Iframe.open(index.first, index.second, {
                query: {
                    id: body.primaryValue,
                    classID: body.data.classID
                }
            });
        },
    });

    function refresh() {
        console.log('render');
        List.render({
            classID: classID,
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
                        classID: classID,
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