

; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');

    var List = require('List');
    var Pager = require('Pager');
    var ButtonList = require('ButtonList');
    var CascadeNavigator = require('CascadeNavigator');


    //默认配置
    var defaults = {
        pageSize: 30,
        typeID: '',
        pageNo: 1,
        hasBreadcrumbs: true,
        multiSelect: true
    };

    var primaryKey;
    var dialog = Iframe.getDialog();
    if (dialog) {
        var data = dialog.getData();
        defaults = $.Object.extend(defaults, data);

        dialog.on({
            close: function () {
                getData();
            }
        });
    }

    function getData(){
        var item = (List.getSelectedItems()[0] && List.getSelectedItems()[0].data) ? List.getSelectedItems()[0].data : {};

        if (item) {
            dialog.setData([{
                'ID': item[primaryKey],
                'name': item.name,
                'number': item.number
            }]);
        }
    }


    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'add': function (item, index) {
            Iframe.open(1, 3, {
                query: {

                }
            });
        },
        'add-category': function (item, index) {
            getData();
        },

        'delete': function (item, index) {
            var api = new API('bos/baseitem');
            api.get({
                action: 'delete',
                data: {
                    'classID': 10101,
                    'items': [
                        { 'itemID': 1 },
                        { 'itemID': 2 },
                    ]
                }
            });

        },
        'refresh': function (item, index) {

        },
        'disable': function (item, index) {
            var list = List.getSelectedItems();
            console.dir(list);
        },
        'enable': function (item, index) {

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

    CascadeNavigator.render();

    CascadeNavigator.on('change', function (list) {
        console.dir(list);
    });
    

    List.render({
        pageNo: 1,
        pageSize: 2,
        multiSelect: defaults.multiSelect
    }, function (total, pageSize) {

        Pager.render({
            size: pageSize,
            total: total,
            change: function (no) {
                List.render({
                    pageNo: no,
                    pageSize: pageSize,
                    multiSelect: defaults.multiSelect
                });
            }
        });

        primaryKey = List.getPrimaryKey();
    });


    List.on({
        'click:number': function (data, event) {
            var body = data.body;

            Iframe.open(1, 3, {
                query: {
                    id: body.primaryValue,
                }
            });
        },
    });

    
    

})();