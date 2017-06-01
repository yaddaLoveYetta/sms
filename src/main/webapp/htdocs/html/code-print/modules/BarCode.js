/**
 * Created by yadda on 2017/6/1.
 */

define('BarCode', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var target = $('#code');
    var codeItem = $("<div class='bc-target'></div>");

    var div = document.getElementById("code");
    var samples = require("Samples")(div);

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

        /*        for (var i = 0; i < code.length; i++) {
         codeItem.clone().appendTo(target).barcode("1234567890128", "ean13", {
         barWidth: 1,
         barHeight: 50,
         moduleSize: 5,
         showHRI: true,
         bgColor: '#FFFFFF',
         color: '#000000',
         fontSize: 12,
         output: 'css',
         });
         }*/

        div.innerHTML = $.Array.keep(code, function (item, index) {

            return $.String.format(samples["codes"], {
                name: item.name || '',
                model: item.model || '',
                batch: item.batch || '',
                code: codeItem.clone().barcode(item.text, "ean13", {
                    barWidth: 1,
                    barHeight: 50,
                    moduleSize: 5,
                    showHRI: true,
                    bgColor: '#FFFFFF',
                    color: '#000000',
                    fontSize: 12,
                    output: 'css',
                })
            });

        }).join('');

    }

    return {
        render: render,
    }
});