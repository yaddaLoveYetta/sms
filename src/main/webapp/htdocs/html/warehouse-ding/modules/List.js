

/**
* List 模块
* 
*/
define('List', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var API = require('/API'); //完整名称为 List/API


    var div = document.getElementById('div-list');
    var samples = require('/Samples')(div); //完整名称为 List/Samples

    var primaryKey = '';
    var list = {};
    var hasBind = false;
    var emitter = MiniQuery.Event.create();
    var index$selected = {}; //记录选中的索引


    function load(pageNo, pageSize, fn) {

        KERP.Tips.loading('数据加载中...');

        API.get({
            pageNo: pageNo,
            pageSize: pageSize,

        }, function (data) {

            KERP.Tips.success('数据加载成功', 1500);

            var total = data.body.total;
            fn && fn(data, total);

        });


    }


    function getHtml(type, data) {

        if (typeof data == 'boolean') {
            data = data ? '是' : '否';
        }

        return type == 0 ? data :

            type == 1 ? $.String.format(samples['item.a'], {
                text: data,
            }) :

            type == 2 ? $.String.format(samples['item.dropdown'], {
                text: data,
            }) :

            data;
    }


    function render(config, fn) {

        load(config.pageNo, config.pageSize, function (data, total) {

            list = data;

            primaryKey = list.primaryKey;

            console.log(primaryKey);

            var headItems = data.head.items;
            var bodyItems = data.body.items;

            div.innerHTML = $.String.format(samples['table'], {

                'checkbox': data.checkbox ? samples['th.checkbox'] : '',

                'ths': $.Array.keep(headItems, function (field, index) {
                    return $.String.format(samples['th'], {
                        'index': index,
                        'th': field.text,
                        'width': field.width,
                    });

                }).join(''),

                'trs': $.Array.keep(bodyItems, function (item, no) { //行

                    return $.String.format(samples['tr'], {
                        'index': no,
                        'disabled-class': item.disabled ? 'disabled' : '',

                        'checkbox': data.checkbox ? $.String.format(samples['td.checkbox'], {
                            'index': no,

                        }) : '',

                        'tds': $.Array.keep(item.items, function (item, index) { //列

                            var field = headItems[index]; //当前列的表头信息

                            return $.String.format(samples['td'], {
                                'index': index,
                                'td': getHtml(field.type, item.value),
                                'number-class': field.key == 'number' ? 'number' : '',
                            });

                        }).join('')

                    });

                }).join('')
            });


            if (!hasBind) {
                bindEvents(config.multiSelect);
                hasBind = true;
            }

            fn && fn(total, config.pageSize);

        });



    }


    function check(chk, checked) {

        checked = chk.checked = typeof checked == 'boolean' ? checked : chk.checked;

        var tr = chk.parentNode.parentNode;
        $(tr).toggleClass('selected', checked);

        var index = +chk.getAttribute('data-index'); //行号
        index$selected[index] = checked;
    }


    function bindEvents(multiSelect) {

        if (multiSelect) {
            $(div).delegate('[data-check="all"]', 'click', function (event) {

                var chk = this;
                var checked = chk.checked;

                $('[data-check="item"]').each(function () {
                    var chk = this;
                    check(chk, checked);
                });

            }).delegate('[data-check="item"]', 'click', function (event) {
                var chk = this;
                check(chk);

            });
        }
        else {
            $('[data-check="all"]').hide();

            $(div).delegate('[data-check=item]', 'click', function (event) {
                var item = this;
                var item = this;
                var checked = item.checked;
                $('[data-check="item"]').each(function () {
                    var item = this;
                    check(item, false);
                });
                check(item, checked);
            });


            //$('[data-check="item"]').each(function () {
            //    var item = this;
            //    $(item).bind('click', function () {
            //        var item = this;
            //        var checked = item.checked;
            //        $('[data-check="item"]').each(function () {
            //            var item = this;
            //            item.checked = false;
            //        });
            //        item.checked = checked;
            //    });
            //});
        }



        $(div).delegate('td[data-index]', 'click', function (event) {
            var td = this;
            var tr = td.parentNode;

            var index = +td.getAttribute('data-index'); //列号
            var no = +tr.getAttribute('data-index'); //行号

            var headItems = list.head.items;
            var bodyItems = list.body.items;

            var field = headItems[index];
            var item = bodyItems[no];

            var args = [
                {
                    'row': no,
                    'cell': index,
                    'head': field,
                    'body': item,
                    'item': item.items[index],
                },
                event,
            ];

            emitter.fire('click:' + no + '-' + index, args);
            emitter.fire('click:' + field.key, args);
            emitter.fire('cell.click', args);

        });

        $(div).delegate('tr[data-index]', 'click', function (event) {
            var tr = this;
            var no = +tr.getAttribute('data-index'); //行号
            var bodyItems = list.body.items;

            var args = [
                {
                    row: no,
                    body: bodyItems[no],
                },
                event,
            ];

            emitter.fire('click:' + no, args);
            emitter.fire('row.click', args);

        });
    }


    function getSelectedItems() {

        var a = [];

        $.Object.each(index$selected, function (index, selected) {
            if (!selected) {
                return;
            }

            var item = list.body.items[index];

            a.push(item);
        });

        return a;
    }

    function getPrimaryKey() {
        return primaryKey;
    }


    return {
        load: load,
        render: render,
        on: emitter.on.bind(emitter),
        getSelectedItems: getSelectedItems,
        getPrimaryKey: getPrimaryKey
    };

});






