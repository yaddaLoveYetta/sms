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

    var codeType = document.getElementById('codeType');
    var codeWidth = document.getElementById('codeWidth');
    var codeHeight = document.getElementById('codeHeight');

    var codeTypes = ['code39', 'code93', 'code128'];
    var codeWidths = [1, 2, 3];
    var codeHeights = [50, 60, 70];

    var codeData = [];
    var hasBind = false;

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

        codeData = code;

        codeType.innerHTML = $.Array.keep(codeTypes, function (item, index) {
                return $.String.format('<option value={value}>{text}</option>', {
                    value: item,
                    text: item,
                });
            }
        ).join('');

        codeWidth.innerHTML = $.Array.keep(codeTypes, function (item, index) {
                return $.String.format('<option value={value}>{text}</option>', {
                    value: item,
                    text: item,
                });
            }
        ).join('');

        codeHeight.innerHTML = $.Array.keep(codeTypes, function (item, index) {
                return $.String.format('<option value={value}>{text}</option>', {
                    value: item,
                    text: item,
                });
            }
        ).join('');

        div.innerHTML = $.Array.keep(code, function (item, index) {

            return $.String.format(samples["codes"], {
                index: index,
                name: item.name || '',
                model: item.specification || '',
                effective: item.effectiveDate || '',
                batch: item.dyBatchNum || '',
            });

        }).join('');

        generateCode();

        bindEvents();

    }

    function generateCode() {

        SMS.use('BarCode', function (BarCode) {

            for (var i = 0; i < codeData.length; i++) {

                var item = codeData[i];

                $('.bc-target').eq(i).barcode(item.code, $('#codeType').find('option:selected').text(), {
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
    }

    function bindEvents() {

        if (hasBind) {
            return;
        }
        $(codeType).on('change', function () {
            generateCode();
        });

        hasBind = true;
    }

    return {
        render: render,
    }
});