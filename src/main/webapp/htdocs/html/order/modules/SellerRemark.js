



define('SellerRemark', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    var wrapper = document.getElementById('order-list');

    var buyerInfo = {
        index: null,
        no: null,
    };

    var emitter = MiniQuery.Event.create();

    $(wrapper).delegate('#seller-remark-container-confirm', 'click', function () {

        var remark = $('#seller-remark').val();


        var arg = {
            "orderID": item.orderID,
            "webOrderID": item.webOrderID,
            "saleMemo": remark
        };


        var arg2 = {
            "outerMemo": remark
        };

        emitter.fire('save-seller-remark', [arg, buyerInfo.index, arg2]);


        $('[id^=seller-remark_]').removeClass('buyer-info-hover');
        $('#seller-remark-container').addClass('hidden');

    }).delegate('#seller-remark-container-cancel', 'click', function () {

        $('[id^=seller-remark_]').removeClass('buyer-info-hover');
        $('#seller-remark-container').addClass('hidden');
    });

    function getCssValue(val) {
        return +val.substr(0, val.length - 2);
    }

    function render(index, _item, no, popupType) {

        var skuContainer = $('#goods-item-sku');
        var buyerInfoContainer = $('#buyer-info-container');
        var sellerRemarkContainer = $('#seller-remark-container');
        
        var td = $('#seller-remark_' + index);
        var tdBuyerInfo = $('#buyer-info_' + index);

        td.toggleClass('buyer-info-hover');
        tdBuyerInfo.removeClass('buyer-info-hover');

        if (buyerInfo && buyerInfo.index != index) {
            $('#seller-remark_' + buyerInfo.index).removeClass('buyer-info-hover');
        }

        item = _item;
        if (td.hasClass('buyer-info-hover')) {

            var thead = $('#order-list thead');
            var div = td.children('div');
            var trSepRow = $('tr.sep-row');

            var t = getCssValue(div.css('padding-top'));
            var top = td.position().top - thead.height() + div.height() - trSepRow.height() + t + 1;
            sellerRemarkContainer.css("top", top);

            sellerRemarkContainer.removeClass('hidden');
            skuContainer.addClass('hidden');
            buyerInfoContainer.addClass('hidden');

            $('[id^=good-item_]').removeClass('good-modify-hover');

            if (+[1, ]) {//如果不是IE
                sellerRemarkContainer[0].scrollIntoViewIfNeeded();
            }
            else {
                sellerRemarkContainer[0].scrollIntoView(false);
            }

            if (popupType != 1 || buyerInfo.index != index || buyerInfo.no != no) {

                buyerInfo = {
                    index: index,
                    no: no
                };

                document.getElementById('buyer-info-container').innerHTML = '';

                document.getElementById('seller-remark').value = item.outerMemo;

            }


        }
        else {
            sellerRemarkContainer.addClass('hidden');

        }
    }

    return {
        render: render,
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }

});
