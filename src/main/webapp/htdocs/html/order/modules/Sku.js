


define('Sku', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var SkuSelect = require('SkuSelect');

    var wrapper = document.getElementById('order-list');
    var emitter = MiniQuery.Event.create();

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "skuList",
            begin: "#--skuList.begin--#",
            end: "#--skuList.end--#",
            outer: "{skuList}"
        },
        {
            name: "skuGroup",
            begin: "#--skuGroup.begin--#",
            end: "#--skuGroup.end--#",
            outer: "{skuGroup}"
        },
        {
            name: "skuGoodsNo",
            begin: "#--skuGoodsNo.begin--#",
            end: "#--skuGoodsNo.end--#"
        },
        {
            name: "skuOption",
            begin: "#--skuOption.begin--#",
            end: "#--skuOption.end--#"
        },
        {
            name: "skuAmount",
            begin: "#--skuAmount.begin--#",
            end: "#--skuAmount.end--#"
        },
        {
            name: "skuWarehouse",
            begin: "#--skuWarehouse.begin--#",
            end: "#--skuWarehouse.end--#"
        }
    ]);

    var skuList = [];

    var sku = {
        attr: null,
        no: null
    };

    $('#order-list').delegate('#sku-container-confirm', 'click', function () {

        $('[id^=good-item_]').removeClass('good-modify-hover');
        $('#goods-item-sku').addClass('hidden');


        console.log('sku.attr' + sku.attr);

        var tempArray = attr.split('_');
        var orderID = skuList[tempArray[0]];

        var args = {
            editType: 'EditItem',
            EditItem: {
                "orderID": skuList[tempArray[0]],
                "webOrderID": "1",
                "orderEntryID": skuList[tempArray[1]],
                "itemID": "2",
                "detailID": "0",
                "spID": "1",
                "stockID": "1"
            }
        }

        emitter.fire('editGoods', args);
    });

    $('#order-list').delegate('#sku-container-cancel', 'click', function () {

        $('[id^=good-item_]').removeClass('good-modify-hover');
        $('#goods-item-sku').addClass('hidden');
    });

    function init(data) {
        skuList = data;
    }

    function getListByType(type) {
        var item = $.Array.findItem(skuList, function (item) {
            return item.type == type;
        });

        return item ? item.list : [];

    }

    function fill(data) {

        var list = [];
        document.getElementById('goods-item-sku').innerHTML = $.String.format(samples['skuList'], {

            'skuGroup': $.Array.keep(data, function (item, index) {

                var skuHtml = '';

                switch (item.skuType) {
                    case 0:
                        skuHtml = $.String.format(samples['skuGoodsNo'], {
                            name: item.skuTitle,
                            skuName: item.skuName
                        });
                        break;
                    case 1:
                        skuHtml = $.String.format(samples['skuOption'], {
                            index: index,
                            name: item.skuTitle
                        });

                        list.push({
                            attr: $.String.format('[data-index={0}]', index),
                            skuList: getListByType(item.type),
                            type: item.skuType,
                            skuValue: item.skuValue,
                        });
                        break;
                    case 2:
                        skuHtml = $.String.format(samples['skuAmount'], {
                            index: index,
                            name: item.skuTitle
                        });
                        list.push({
                            attr: $.String.format('[data-index={0}]', index),
                            skuValue: item.skuName,
                            type: item.skuType
                        });
                        break;
                    case 3:
                        skuHtml = $.String.format(samples['skuWarehouse'], {
                            index: index,
                            name: item.skuTitle
                        });
                        list.push({
                            attr: $.String.format('[data-index={0}]', index),
                            skuValue: item.skuName,
                            type: item.skuType
                        });
                        break;
                }

                return skuHtml;

            }).join('')
        });

        $.Array.keep(list, function (item) {
            switch (item.type) {
                case 1:
                    SkuSelect.render(item.attr, item.skuList, item.skuValue);
                    break;
                case 2:
                    console.log(item.skuValue);
                    $('[data-type=amount-spinner]' + item.attr).SpinnerControl({
                        defaultVal: +item.skuValue || 1
                    });
                    break;
            }

        });


    }

    function render(data, attr, popupType, no) {

        var skuContainer = $('#goods-item-sku');
        var buyerInfoContainer = $('#buyer-info-container');

        var tr = $('#good-item_' + attr);

        tr.toggleClass('good-modify-hover');

        if (sku.attr && sku.attr != attr) {
            $('#good-item_' + sku.attr).removeClass('good-modify-hover');
        }

        if (tr.hasClass('good-modify-hover')) {

            var thead = $('#order-list thead');
            var trSepRow = $('tr.sep-row');
            console.log('tr.height()' + tr.position().top + ',' + thead.height() + ',' + tr.height());
            var top = tr.position().top - thead.height() + tr.height() - trSepRow.height() - 1;

            skuContainer.css("top", top);

            skuContainer.removeClass('hidden');
            buyerInfoContainer.addClass('hidden');
            $('[id^=buyer-info_]').removeClass('buyer-info-hover');

            if (+[1, ]) {//如果不是IE
                skuContainer[0].scrollIntoViewIfNeeded();
            }
            else {
                skuContainer[0].scrollIntoView(false);
            }


            if (popupType != 0 || sku.attr != attr || sku.no != no) {

                sku = {
                    attr: attr,
                    no: no
                }

                fill(data);
            }



        }
        else {
            skuContainer.addClass('hidden');

        }

    }

    return {
        render: render,
        init: init
    }

});