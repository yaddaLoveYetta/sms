

//按钮组数据
define('List', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    function getItems(emitter) {

        var items = [
                {
                    text: '新增',
                    click: function () {
                        emitter.fire('optNew');
                    }
                },
                {
                    text: '新增类别',
                    click: function () {

                    }
                },
                {
                    text: '保存',
                    click: function () {
                        emitter.fire('optSave');
                    }
                },
                {
                    text: '刷新',
                    click: function () {
                        emitter.fire('optRefresh');
                    }
                },
                {
                    text: '禁用',
                    click: function () {

                    },
                    items: [
                        {
                            text: '反禁用',
                            click: function () {

                            }
                        }
                    ]
                },
                {
                    text: '引出',
                    click: function () {

                    },
                    items: [
                        {
                            text: '引入',
                            click: function () {

                            }
                        },
                        {
                            text: '引出模板设置',
                            click: function () {

                            }
                        }
                    ]
                },
                {
                    text: '更多',
                    cssClass: 'btn-more',
                    click: function (item, index) {
                        bl.toggle(index);
                    },
                    items: [
                        {
                            text: '复制',
                            click: function () {

                            }
                        }
                    ]
                }

        ];

        return items;
    }

    return {
        getItems: getItems
    }
});