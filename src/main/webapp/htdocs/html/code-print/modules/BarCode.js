/**
 * Created by yadda on 2017/6/1.
 */

define('BarCode', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var target = $('#code');
    var item = $("<div class='bcTarget'></div>");

    var _default = {
        barWidth: 1,
        barHeight: 50,
        moduleSize: 5,
        showHRI: false,
        bgColor: '#FFFFFF',
        color: '#000000',
        fontSize: 10,
        output: 'css',
    };

    function render(code) {

        for (var i = 0; i < code.length; i++) {
            item.clone().appendTo(target).barcode("1234567890128", "ean13", {
                barWidth: 1,
                barHeight: 50,
                moduleSize: 5,
                showHRI: true,
                bgColor: '#FFFFFF',
                color: '#000000',
                fontSize: 12,
                output: 'css',
            });
        }
    }

    return {
        render: render,
    }
});