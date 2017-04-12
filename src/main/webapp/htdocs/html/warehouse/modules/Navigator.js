

/**
* 分类导航模块
* 
*/
define('Navigator', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    var div = document.getElementById('div-list');


    var samples = $.String.getTemplates(div.innerHTML, [
        {
            name: 'table',
            begin: '<!--',
            end: '-->'
        },
        {
            name: 'tr',
            begin: '#--tr.begin--#',
            end: '#--tr.end--#',
            outer: '{trs}'
        }
    ]);



    var list = [
        {
            name: '广东省',
            items: [
                {
                    name: '深圳市',
                    items: [
                        { name: '南山区' },
                        { name: '宝安区' },
                        { name: '福田区' },
                        { name: '罗湖区' },
                        { name: '龙岗区' },
                    ],
                },
                {
                    name: '广州市',
                    items: [
                        { name: '天河区' },
                        { name: '白云区' },
                        { name: '黄埔区' },
                        { name: '番禺区' },
                        { name: '花都区' },
                        { name: '南沙区' },
                        { name: '萝岗区' },
                    ],
                },
            ]
        },
        {
            name: '江西省',
            items: [
                {
                    name: '南昌市',
                    items: [
                        { name: '东湖区' },
                        { name: '西湖区' },
                        { name: '青云谱区' },
                    ],
                },
                {
                    name: '吉安市',
                    items: [
                        { name: '吉州区' },
                        { name: '青原区' },
                        { name: '吉安县' },
                        { name: '峡江县' },
                    ],
                },
            ]
        },

    ];

    var hasBind = false;


    function load(pageNo, pageSize, fn) {

        KERP.Tips.loading('数据加载中...');

        KERP.API.get('warehouse/list', {
            pageNo: pageNo || 1,
            pageSize: pageSize,

        }, function (data, json) {

            KERP.Tips.success('加载成功', 1500);
            fn && fn(data.list, data.total);

        });


    }



    function render(data) {
        
        list = data;

        div.innerHTML = $.String.format(samples.table, {
            'trs': $.Array.keep(list, function (item, index) {

                return $.String.format(samples.tr, item);

            }).join('')
        });
        
        if (!hasBind) {
            bindEvents();
            hasBind = true;
        }
    }

    function bindEvents() {

        $(div).delegate('[data-check="all"]', 'click', function () {

            var chk = this;
            var checked = chk.checked;
            console.log(checked);

            $('[data-check="item"]').each(function () {
                var chk = this;
                chk.checked = checked;
            });
        });
    }



    return {
        load: load,
        render: render,
    };

});






    