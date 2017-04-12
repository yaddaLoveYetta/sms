

/**
* 菜单组模块
* 
*/
define('ButtonList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var ButtonList = KERP.require('ButtonList');
    var Iframe = KERP.require('Iframe');
    var API = KERP.require('API');

    var bl = new ButtonList({
        container: '#div-button-list',
        fields: {
            text: 'text',
            child: 'items',
            callback: 'click',
            route: 'name',
        },

        autoClose: true,

        items: [
            { text: '新增', name: 'add', },
            { text: '新增类别', name: 'add-category', },
            { text: '删除', name: 'delete', },
            { text: '刷新', name: 'refresh', },
            {
                text: '禁用',
                name: 'disable',
                items: [
                    { text: '反禁用', name: 'enable', },
                ],
            },
            {
                text: '引出',
                name: 'export',
                items: [
                    { text: '引入', name: 'import', },
                    { text: '模板设置', name: 'setting',  },
                ],
            },
            {
                text: '更多',
                name: 'more',
                cssClass: 'btn-more',
                items: [
                    { text: '复制', name: 'copy', },
                ],
                
            },
        ],
    });

    //总事件，最后触发
    bl.on('click', function (item, index) {
        console.dir(item);
    });



    





    return bl;

});






    