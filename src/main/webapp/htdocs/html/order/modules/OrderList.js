

define('OrderList', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var wrapper = document.getElementById('order-list');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "group",
            begin: "<!--",
            end: "-->",
            outer: "{group}"
        },
        {
            name: "order",
            begin: "#--order.begin--#",
            end: "#--order.end--#",
            outer: "{order}"
        },
        {
            name: "goodItem",
            begin: "#--goodItem.begin--#",
            end: "#--goodItem.end--#",
            outer: "{goodItem}"
        },
        {
            name: "skuName",
            begin: "#--skuName.begin--#",
            end: "#--skuName.end--#",
            outer: "{skuName}"
        },
        {
            name: "gift",
            begin: "#--gift.begin--#",
            end: "#--gift.end--#",
            outer: "{gift}"
        },
        {
            name: "absence",
            begin: "#--absence.begin--#",
            end: "#--absence.end--#",
            outer: "{absence}"
        },
        {
            name: "refund",
            begin: "#--refund.begin--#",
            end: "#--refund.end--#",
            outer: "{refund}"
        },
        {
            name: "flag",
            begin: "#--flag.begin--#",
            end: "#--flag.end--#",
            outer: "{flag}"
        },
        {
            name: "logistics-remark",
            begin: "#--logistics-remark.begin--#",
            end: "#--logistics-remark.end--#",
            outer: "{logistics-remark}"
        },
        {
            name: "logistics",
            begin: "#--logistics.begin--#",
            end: "#--logistics.end--#",
            outer: "{logistics}"
        },
        {
            name: "remark",
            begin: "#--remark.begin--#",
            end: "#--remark.end--#",
            outer: "{remark}"
        }
    ]);

    var list = [];

    var no = 1;

    var popupType = -1;//记录弹出框类型 -1:初始值 0：修改商品 1：修改收货信息

    var emitter = MiniQuery.Event.create();


    function getIndexFromId(id) {
        return id.substr(id.indexOf('_') + 1);
    }

    function bindEvents() {

        $('#order-list').delegate('#check-all', 'click', function () {

            $(this).toggleClass('checkbox-all-selected');

            if ($(this).hasClass('checkbox-all-selected')) {

                $('[data-btn-type=check]').addClass('checkbox-item-selected');

            }
            else {
                $('[data-btn-type=check]').removeClass('checkbox-item-selected');
            }

        }).delegate('[data-btn-type=check]', 'click', function () {
            $(this).toggleClass('checkbox-item-selected');

            if (!$(this).hasClass('checkbox-item-selected')) {

                $('#check-all').removeClass('checkbox-all-selected');

            }

        }).delegate('[data-good-index]', 'click', function () {

            var btn = this;
            var attr = btn.getAttribute('data-good-Index');

            var tempArray = attr.split('_');

            emitter.fire('sku-fill', [list[tempArray[0]].goodsItems[tempArray[2]].skuname || [], attr, popupType, no]);

            popupType = 0;

        }).delegate('[data-type=modify-logistics]', 'click', function () {

            var btn = this;
            var index = btn.getAttribute('data-index');

            emitter.fire('buyer-info-show', [index, list[index], no, popupType]);

            popupType = 1;

        }).delegate('[data-type=modify-remark]', 'click', function () {

            var btn = this;
            var index = btn.getAttribute('data-index');

            emitter.fire('seller-remark-show', [index, list[index], no, popupType]);

            popupType = 1;

        }).delegate('[data-type=del]', 'click', function () {

            var btn = this;
            var orderId = btn.getAttribute('order-id');

            emitter.fire('delete', [orderId]);

        }).delegate('[data-type=freeze]', 'click', function () {

            var btn = this;
            var orderId = btn.getAttribute('order-id');
            var webOrderID = btn.getAttribute('web-order-id');

            emitter.fire('freeze', [orderId, webOrderID]);

        }).delegate('[data-type=split]', 'click', function () {


            //var btn = this;
            //var orderId = btn.getAttribute('order-id');

            //emitter.fire('split', [orderId]);

            SMS.use('Dialog', function (Dialog) {
                var dialog = new Dialog({
                    id: 'dialog-split',
                    title: '拆分订单',
                    url: './html/order_split/index.html', // ./ 表示相对于网站根目录
                    width: 900,
                    height: 600
                });

                //绑定各种事件
                dialog.on({
                    show: function () {
                        console.log('on show');
                    },

                    iframeload: function () {
                        console.log('on iframeload');
                    },

                    close: function () {
                        console.log('on close');
                    },

                    remove: function () {
                        console.log('on remove');
                    }
                });

                dialog.showModal();

            });

        }).delegate('[data-type=check]', 'click', function () {

            var btn = this;
            var orderId = btn.getAttribute('order-id');

            emitter.fire('check', [orderId]);


        }).delegate('#div-select-all', 'mouseover', function () {
            $('#div-select-type').show();

        }).delegate('#div-select-all', 'mouseout', function () {
            $('#div-select-type').hide();

        }).delegate('#div-select-type', 'mouseover', function () {
            $('#div-select-type').show();

        }).delegate('#div-select-type', 'mouseout', function () {
            $('#div-select-type').hide();

        }).delegate('li[data-select-type]', 'click', function () {

            var items = ['所有订单', '当前页订单', '取消全选'];
            var li = this;
            var index = +li.getAttribute('data-select-type');
            var item = items[index]; //取得数据
            $('#span-select-type').text(item);
            $('#div-select-type').hide();
        });

        //var div = document.getElementById('div-select-all');
        //console.dir(div);
        //$(div).hover(function () {
        //    console.log('select hover');
        //    $('#div-select-type').show();
        //}, function () {
        //    $('#div-select-type').hide();
        //});

    }

    function getLogisticsClass(type) {
        switch (type) {
            case undefined: return '';
            default: return 'bgcolor-blue';
        }
    }

    function updateList(index, obj) {

        $.extend(list[index], obj);
    }

    function fillBuyerInfo(obj, orderIndex) {

        return $.String.format(samples['logistics'], {
            index: orderIndex,
            buyernick: obj.receiver,
            deliveryStateName: obj.deliveryState,
            deliveryCityName: obj.deliveryCity,
            deliveryDistrictName: obj.deliveryDistrict,
            deliveryAddress: obj.deliveryAddress,
            logisticsCompany: obj.logisticsCompany || '',
            logisticsCompanyName: obj.logisticsCompanyName || '',
            logisticsClass: getLogisticsClass(obj.logisticsCompany),
            canReach: '不',
            isOverload: '重',
        });
    }

    function fillRemark(obj, orderIndex) {

        return $.String.format(samples['remark'], {
            index: orderIndex,
            buyerMessage: obj.buyerMessage,
            outerMemo: obj.outerMemo
        });
    }

    function fillGoodsInfo(obj, orderIndex, length) {

        var list = $.Array.keep(obj.goodsItems, function (item, index) {

            return $.extend({}, item, {
                'amount': item['qty'],
                'gift': item['isFree'],
                'refund': item['refundStatus'],
            });

        });

        return $.Array.keep(list, function (item, index) {

            return $.String.format(samples['goodItem'], {
                picPath: item.picPath || '../../css/order/img/empty.jpg',
                title: item.title,
                price: item.price,
                amount: item.amount,
                gift: item.gift ? samples['gift'] : '',
                absence: item.absence ? samples['absence'] : '',
                refund: item.refund != 'NO_REFUND' ? samples['refund'] : '',
                flag: item.flag ? samples['flag'] : '',
                goodsIndex: orderIndex + '_' + (index + length) + '_' + index,
                rowIndex: index,
                //skuName: $.Array.keep(item.skuName || [], function (sku, skuIndex) {

                //    return $.String.format(samples['skuName'], {
                //        skuTitle: sku.skuTitle,
                //        skuValue: sku.skuName,

                //    })

                //}).join('') || '',
                skuName: item.skuName || '',
                'logistics-remark': index == 0 ? $.String.format(samples['logistics-remark'], {
                    index: orderIndex,
                    rowspan: list.length * 2 - 1,
                    logistics: fillBuyerInfo(obj, orderIndex),
                    remark: fillRemark(obj, orderIndex),
                    orderID: item.orderID,
                    webOrderID: item.tid,
                }) : '',
                'sep-row-class': index == 0 ? 'no-border' : '',
                discountAmount: item.discountAmount,
                canReach: '不',
                isOverload: '重',
            });
        }).join('');

    }

    bindEvents();

    function render(data, _no) {

        no = _no;

        list = $.Array.keep(data || [], function (item, index) {

            return $.extend({}, item, {
                'goodsItems': item['entries'],
                'payTime': item['createTime'],
            });

        });

        var length = 0;

        if (list.length > 0) {
            $('#have-data').show();
            $('#no-data').hide();


            wrapper.innerHTML = $.String.format(samples['group'], {

                'order': list.length > 0 ? $.Array.keep(list, function (item, index) {

                    if (index > 0) {
                        length += list[index - 1].goodsItems.length;

                    }

                    return $.String.format(samples['order'], {
                        index: index,
                        orderID: item.orderID,
                        'hidden-class': item.goodsItems.length <= 1 ? 'hidden' : '',
                        webOrderID: item.tid,
                        payTime: item.payTime,
                        orderStatus: item.orderStatusName + '&nbsp;&nbsp;&nbsp;' + item.transStatusName,
                        goodItem: fillGoodsInfo(item, index, length),
                        payAmount: item.payAmount,
                        isFreeze: item.isFreeze ? '冻结' : ''
                    });

                }).join('') : samples['no-data']
            });

            $('#order-list img').on('error', function () {
                var img = this;
                img.src = '../../css/order/img/empty.jpg';
                img.onerror = null;
            });
        }
        else {
            $('#have-data').hide();
            $('#no-data').show();

            wrapper.innerHTML = "";
        }

    }
    
    return {
        render: render,
        fillBuyerInfo: fillBuyerInfo,
        fillRemark:fillRemark,
        updateList: updateList,
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    };

});