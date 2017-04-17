





define('BuyerInfo', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var ExpressCompany = require('ExpressCompany');

    var Address = require('Address');

    var wrapper = document.getElementById('order-list');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "buyerInfo",
            begin: "#--buyerInfo.begin--#",
            end: "#--buyerInfo.end--#"
        }
    ]);

    var buyerInfo = {
        index: null,
        no: null,
    };

    var item = null;

    var emitter = MiniQuery.Event.create();

    $(wrapper).delegate('#buyer-info-container-confirm', 'click', function () {

        var wrapper = '#buyer-info-container';
        var province = $('[data-index = 0]', wrapper);
        var city = $('[data-index = 1]', wrapper);
        var district = $('[data-index = 2]', wrapper);
        var detail = $('#address-detail').val();
        var company = $('#ul-express-company').children('[selected]');

        if (!province.val() || province.val() == '--请选择省份--') {
            SMS.Tips.warn('请选择省', 2000);
            return;
        }

        if (!city.val() || city.val() == '--请选择城市--') {
            SMS.Tips.warn('请选择市', 2000);
            return;
        }

        if (!district.val() || district.val() == '--请选择地区--') {
            SMS.Tips.warn('请选择区', 2000);
            return;
        }

        if (!detail) {
            SMS.Tips.warn('请输入详细地址', 2000);
            return;
        }

        if (!company.attr('data-type')) {
            SMS.Tips.warn('请选择物流公司', 2000);
            return;
        }


        var tid = item.tid;
        var orderId = item.orderID;
        var buyerNick = item.receiver;

        var selectedItems = Address.getSelectedItems();


        var args = {
            "orderID": orderId,
            "webOrderID": item.webOrderID,
            "tid": tid,
            "logisticsCompany": company.attr('data-type'),
            "deliveryStateID": selectedItems.length == 3 ? selectedItems[0][0] : -1,
            "deliveryCityID": selectedItems.length == 3 ? selectedItems[1][0] : -1,
            "deliveryDistrictID": selectedItems.length == 3 ? selectedItems[2][0] : -1,
            "receiverAddress": detail
        };


        var args2 = {
            index: buyerInfo.index,
            buyerNick: buyerNick,
            deliveryState: province.val(),
            deliveryCity: city.val(),
            deliveryDistrict: district.val(),
            deliveryAddress: detail,
            logisticsCompany: company.attr('data-type') || '',
            logisticsCompanyName: company.text() || '',
        };

        emitter.fire('save-buyer-info', [args, buyerInfo.index, args2]);


        $('[id^=buyer-info_]').removeClass('buyer-info-hover');
        $('#buyer-info-container').addClass('hidden');
    });

    $(wrapper).delegate('#buyer-info-container-cancel', 'click', function () {

        $('[id^=buyer-info_]').removeClass('buyer-info-hover');
        $('#buyer-info-container').addClass('hidden');
    });

    function init(index, args) {

        document.getElementById('buyer-info-container').innerHTML = samples['buyerInfo'];

        var args1 = {
            'id': 'div-address-picker',
            'province': args.province || -1,
            'city': args.city || -1,
            'district': args.district || -1

        };
        Address.create(args1);


        document.getElementById('address-detail').value = args.address;

        var val = document.getElementById('span-logistics-' + index).getAttribute('data-value');

        var args2 = {
            'id': 'ul-express-company',
            'ecValue': val
        };

        ExpressCompany.create(args2);
    }


    function getCssValue(val) {
        return +val.substr(0, val.length - 2);
    }

    function render(index, _item, no, popupType) {

        var skuContainer = $('#goods-item-sku');
        var buyerInfoContainer = $('#buyer-info-container');
        var sellerRemarkContainer = $('#seller-remark-container');

        var td = $('#buyer-info_' + index);
        var tdSellerRemark = $('#seller-remark_' + index);

        td.toggleClass('buyer-info-hover');
        tdSellerRemark.removeClass('buyer-info-hover');

        if (buyerInfo && buyerInfo.index != index) {
            $('#buyer-info_' + buyerInfo.index).removeClass('buyer-info-hover');
        }

        item = _item;

        if (td.hasClass('buyer-info-hover')) {

            var thead = $('#order-list thead');
            var div = td.children('div');
            var trSepRow = $('tr.sep-row');

            var t = getCssValue(div.css('padding-top'));
            var top = td.position().top - thead.height() + div.height() - trSepRow.height() + t + 1;
            buyerInfoContainer.css("top", top);

            buyerInfoContainer.removeClass('hidden');
            skuContainer.addClass('hidden');
            sellerRemarkContainer.addClass('hidden');

            $('[id^=good-item_]').removeClass('good-modify-hover');

            if (+[1, ]) {//如果不是IE
                buyerInfoContainer[0].scrollIntoViewIfNeeded();
            }
            else {
                buyerInfoContainer[0].scrollIntoView(false);
            }

            var args = {
                'province': item.deliveryStateID,
                'city': item.deliveryCityID,
                'district': item.deliveryDistrictID,
                'address': item.deliveryAddress,
                'logisticsCompany': item.logisticsCompany,
            };

            if (popupType != 1 || buyerInfo.index != index || buyerInfo.no != no) {

                buyerInfo = {
                    index: index,
                    no: no
                };

                init(index, args);
            }


        }
        else {
            buyerInfoContainer.addClass('hidden');

        }
    }

    return {
        render: render,
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }

});
