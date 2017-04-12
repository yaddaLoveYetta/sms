

/**
* 按钮模块
* 
*/
define('ButtonList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var ButtonList = KERP.require('ButtonList');

    var emitter = MiniQuery.Event.create();

    var bl = new ButtonList({
        container: '#div-button-list',
        fields: {
            text: 'name',
            child: 'buttons',
            callback: 'click',
        },

        autoClose: true,

        buttons: [
            {
                name: '新增',
                click: function (item, index) {
                    emitter.fire('optNew');
                }
            },
            {
                name: '新增类别',
            },
            {
                name: '保存',
                click: function (item, index) {
                    emitter.fire('optSave');
                }
            },
            {
                name: '刷新',
                click: function (item, index) {
                    emitter.fire('optRefresh');
                }
            },
            {
                name: '禁用',
                buttons: [
                    { name: '禁用A', },
                    { name: '禁用B', },
                ],
            },
            {
                name: '引出',
                buttons: [
                    { name: '引出A', },
                    { name: '引出B', },
                ],
            },
            {
                name: '更多',
                cssClass: 'btn-more',
                buttons: [
                    { name: '更多A', },
                    { name: '更多B', },
                    { name: '更多C', },
                ],
            },
        ],
    });

    function render() {
        bl.render();
    }


    bl.on('click', function (item, index) {

        console.dir(item);

    });

    return {
        render: render,
        on: emitter.on.bind(emitter)
    }

});






