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

    var codeTypeDiv=document.getElementById('div-code-style');


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

        div.innerHTML = $.Array.keep(code, function (item, index) {

            return $.String.format(samples["codes"], {
                index: index,
                name: item.name || '',
                model: item.model || '',
                effective: item.effective || '',
                batch: item.batch || '',
            });

        }).join('');

        var type=$('code');

        SMS.use('BarCode', function (BarCode) {

            for (var i = 0; i < code.length; i++) {

                var item = code[i];

                $('.bc-target').eq(i).barcode(item.text, "code128", {
                    barWidth: 2,
                    barHeight: 60,
                    moduleSize: 5,
                    showHRI: true,
                    bgColor: '#FFFFFF',
                    color: '#000000',
                    fontSize: 12,
                    output: 'css',
                    addQuietZone: false,
                });
            }

        });

/*
        $('.bc-target').barcode("1234567890128", "ean13", {
            barWidth: 2,
            barHeight: 60,
            moduleSize: 5,
            showHRI: true,
            bgColor: '#FFFFFF',
            color: '#000000',
            fontSize: 12,
            output: 'css',
        });*/

    }

    return {
        render: render,
    }
});