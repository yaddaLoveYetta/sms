

/**
* 级联菜单模块
* 
*/
define('CascadeNavigator', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var API = KERP.require('API');
    var CascadeNavigator = KERP.require('CascadeNavigator');
    var nav = null;
    var topClassID = 10110;

    function load(classID, fn) {

        var api = new API('bos/baseitem');
        //classID = classID == 10110 ? 0 : classID;

        api.get({
            action: 'queryItemType',
            data: {
                'classID': classID == topClassID ? 0 : classID,
                'parentID': -1,
                'pageNo': 1,
                'pageSize': 9999
            },
        });

        api.on('success', function (data, json) {

            var list = [];

            var tree = {
                name: '全部',
                items: list,
            };

            // data 是一个平行结构的节点集合，根据它的 parentID 来构造成一棵树
            var id$item = data;

            $.Object.each(id$item, function (id, item) {

                var parentId = item.parentID;

                if ((classID != topClassID && parentId < 0) || (classID == topClassID && parentId == 0)) { //顶级节点
                    list.push(item);
                    return;
                }

                //非顶级节点
                var parent = id$item[parentId];
                var items = parent.items = parent.items || [];
                items.push(item);

            });
            fn && fn(tree);
        });


        api.on({
            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                KERP.Tips.error(s, 2000);
                fn && fn({
                    name: s
                });
            },
            'error': function () {
                KERP.Tips.error('网络繁忙，请稍候再试', 2000);
                fn && fn({
                    name: '(网络繁忙，请稍候再试)'
                });
            }
        });


    }

    function render(classID) {

        load(classID, function (tree) {

            var nav = create();
            nav.render(tree);
        });

    }

    function create() {

        nav = nav || new CascadeNavigator({
            containers: {
                path: '#div-cascade-path',
                menus: '#div-cascade-menus'
            },
            fields: {
                text: 'name',
                child: 'items',
            },
        });

        return nav;
    }



    return {
        render: render,
        on: function () {
            var nav = create();
            var args = [].slice.call(arguments, 0);
            nav.on.apply(nav, args);
        }
    };

});






