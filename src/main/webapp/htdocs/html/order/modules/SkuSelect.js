

define('SkuSelect', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var itemSelected = 'item-selected';
    var wrapper = document.getElementById('order-list');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "sku-selected",
            begin: "#--sku-selected.begin--#",
            end: "#--sku-selected.end--#"
        }
    ]);

    function bindEvents(attr, list) {

        $(wrapper).delegate('[data-type=sku-selected]' + attr + ' li', 'click', function () {

            var val = this.getAttribute('data-type');
            fill(attr, list, val);
        });
    }

    function fill(attr, list, skuValue) {

        $('[data-type=sku-selected]' + attr).html($.Array.keep(list, function (item) {

            return $.String.format(samples['sku-selected'], {
                'selectedValue': item.skuValue,
                'selectedClass': skuValue == item.skuValue ? itemSelected : '',
                'name': item.skuName
            });
        }).join(''));

    }

    function render(attr, data, skuValue) {

        data = data || [];
        attr = attr || [];

        fill(attr, data, skuValue);
        bindEvents(attr, data);

    }

    return {
        render: render
    }

});