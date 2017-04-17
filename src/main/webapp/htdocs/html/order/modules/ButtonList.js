


define('ButtonList', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    //----------------------------
    var ButtonList = SMS.require('ButtonList');

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
            {
                text: '审核订单',
                name: 'check',
            },
            {
                text: '合并订单',
                name: 'merge',
            },
            {
                text: '添加赠品',
                name: 'addGift',
            },
            {
                text: '一键检测',
                name: 'validate',
            },
            {
                text: '更多',
                cssClass: 'btn-more',
                items: [
                    {
                        text: '批量修改',
                        name: 'batchUpdate',
                    },
                    {
                        text: '手工录单',
                        name: 'manualOrder',
                    },
                    {
                        text: '复制订单',
                        name: 'copyOrder',
                    },
                    {
                        text: '反审订单',
                        name: 'uncheck',
                    },
                    {
                        text: '删除订单',
                        name: 'delete',
                    },
                    {
                        text: '同步订单',
                        name: 'download',
                    },
                    {
                        text: '引入/引出',
                        cssClass: 'btn-more',
                        items: [
                            {
                                text: '引入',
                                name: 'import',
                            },
                            {
                                text: '引出',
                                name: 'export',
                            },
                            {
                                text: '模板',
                                name: 'template',
                            },
                        ],
                    },
                    {
                        text: '打标签',
                        name: 'insertLabel',
                    },
                    {
                        text: '清除标签',
                        name: 'clearLabel',
                    },
                ],
            },
        ],
    });


    bl.on('click', function (item, index) {
        console.log(index);

        console.dir(item);
    });

    return bl;

});
