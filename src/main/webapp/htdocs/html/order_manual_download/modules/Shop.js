


define('Shop', function(require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    function fill(wrapper, list) {
        KERP.Template.fill(wrapper, list, function(item, index) {
            return {
                'index': index,
                'name': item.name,
                'value': item.shopID,
                'checked': item.checked,
                'isDisabled': item.isDisabled
            };
        });

    }

    function render(list) {
        var radio = document.getElementById('div-radio-shop');
        var checkbox = document.getElementById('div-checkbox-shop');
        fill(radio, list);
        fill(checkbox, list);
    }

    var emitter = MiniQuery.Event.create();

    return {
        render: render,
        on: function(name, fn) {
            emitter.on(name, fn);
        }
    }

});