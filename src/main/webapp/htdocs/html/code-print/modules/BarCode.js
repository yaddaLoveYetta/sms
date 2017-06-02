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

        /**
         code: codeItem.clone().barcode(item.text, "ean13", {
                    barWidth: 2,
                    barHeight: 60,
                    moduleSize: 5,
                    showHRI: true,
                    bgColor: '#FFFFFF',
                    color: '#000000',
                    fontSize: 12,
                    output: 'css',
                    addQuietZone: false,

                    addQuietZone: false,      //是否添加空白区（内边距）
                    bgColor: "#FFFFFF",  //条码背景颜色
                    color: "#000000",    //条码颜色
                    fontSize: 12,   //条码字体大小
                    marginHRI: 5,  //条码字体距条码中心的高度
                    barWidth: "1",  //单条条码宽度
                    barHeight: "60"  //单体条码高度
                })
         */

        div.innerHTML = $.Array.keep(code, function (item, index) {

            return $.String.format(samples["codes"], {
                index: index,
                name: item.name || '',
                model: item.model || '',
                effective: item.effective || '',
                batch: item.batch || '',
            });

        }).join('');


        SMS.use('BarCode', function (BarCode) {

            for (var i = 0; i < code.length; i++) {

                var item = code[i];

                $('.bc-target').eq(i).barcode(item.text, "ean39", {
                    barWidth: 2,
                    barHeight: 60,
                    moduleSize: 5,
                    showHRI: true,
                    bgColor: '#FFFFFF',
                    color: '#000000',
                    fontSize: 12,
                    output: 'css',
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