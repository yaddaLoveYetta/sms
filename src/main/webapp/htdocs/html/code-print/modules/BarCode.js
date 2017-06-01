/**
 * Created by yadda on 2017/6/1.
 */

define('BarCode', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var target = $('#code');
    var item = $("<div class='bcTarget'></div>");

    function render(code) {

        for (var i = 0; i < code.length; i++) {
            item.clone().appendTo(target).barcode("1234567890128", "ean13");
        }
    }

    return {
        render: render,
    }
});