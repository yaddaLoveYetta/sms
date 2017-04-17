


(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    var API = require('API');
    var FilterData = require('FilterData');
    var OrderModel = require('OrderModel');
    var OrderListData = require('OrderListData');
    var Filter = require('Filter');
    var Sorting = require('Sorting');
    //var Sort = require('Sort');
    var OrderList = require('OrderList');
    var OrderPagination = require('OrderPagination');
    var ButtonList = require('ButtonList');
    var BuyerInfo = require('BuyerInfo');
    var SellerRemark = require('SellerRemark');
    var Sku = require('Sku');
    var ProgressBar = require('ProgressBar');

    function getPager() {
        return {
            "page": 1,
            "size": OrderPagination.getSize(),
            "order": ''
        }
    }

    var list = [];

    OrderListData.load(

        function (data) {
            list = data;
            OrderList.render(data);
        }, function (data) {
            sortRender(data);
        }, function (data) {
            OrderPagination.render(data);
        }, function (data) {
            Sku.init(data);
        }, function () {
            SMS.Tips.success('数据加载完成', 2000);
        }

    );

    function sortRender(data) {

        Sorting.render({
            id: 'sort-panel',
            sq: data.sq,
            allsq: data.allsq,
            sorting: function (sortString) {
                console.log(sortString);
            },
            saveOptions: function (sortingList) {

                //Sorting.sortingRender(data.sq);

            }
        });
    }

    //ButtonList.render();

    //Filter.on({
    //    'search': function (selectedItems) {

    //        var args = {
    //            filter: selectedItems,
    //            pager: { "page": 1, "size": OrderPagination.getSize(), "order": Sort.getListString() }
    //        };

    //        API.list(args, function (data) {

    //            SMS.Tips.success('数据加载完成', 2000);
    //            OrderList.render(data.orders);
    //            OrderPagination.render(data.pager);
    //        });

    //    }
    //});

    //Sort.on({
    //    'sort': function () {

    //        var list = Filter.getSelectedOptions();

    //        var args = {
    //            filter: list,
    //            pager: {
    //                "page": 1,
    //                "size": OrderPagination.getSize(),
    //                "order": Sort.getListString()
    //            }
    //        };



    //        API.list(args, function (data) {
    //            SMS.Tips.success('数据加载完成', 2000);
    //            OrderList.render(data.orders);
    //            OrderPagination.render(data.pager);
    //        });
    //    }
    //});

    ButtonList.render();

    ButtonList.on({
        'click:batchUpdate': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                };

                SMS.use('Dialog', function (Dialog) {
                    var dialog = new Dialog({
                        id: 'batch-edit',
                        title: '批量修改',
                        url: './html/batch-edit/index.html', // ./ 表示相对于网站根目录
                        width: 480,
                        height: 300,

                        //需要传递的数据
                        data: args,
                    });

                    //绑定各种事件
                    dialog.on({
                    });

                    dialog.showModal();

                });

            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:check': function (item, index) {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                    checker: 11
                };

                API.check(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {

                    //    var result = item.result == 'success' ? '审核成功' : item.result;
                    //    return item.tid + ':' + result;

                    //}).join('\n');

                    //SMS.Tips.success(msg, 2000);

                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({ value: 10 });
                });

            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:insertLabel': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });

                var args = {
                    ids: checkItems,
                };

                SMS.use('Dialog', function (Dialog) {
                    var dialog = new Dialog({
                        id: 'dialog-split',
                        title: '添加标签',
                        url: './html/insert-label/index.html', // ./ 表示相对于网站根目录
                        width: 850,
                        height: 300,

                        //需要传递的数据
                        data: args,
                    });

                    //绑定各种事件
                    dialog.on({
                    });

                    dialog.showModal();

                });
            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:addGift': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:delete': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                    editType: 'DeleteOrder'
                };

                API.batchOrderEdit(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {
                    //    var result = item.result == 'success' ? '删除成功' : item.result;

                    //    return item.tid + ':' + result;

                    //}).join('\n');



                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({
                        value: 10
                    });


                });
            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:download': function () {
            SMS.use('Dialog', function (Dialog) {
                var dialog = new Dialog({
                    id: 'download',
                    title: '手工同步订单',
                    url: './html/order_manual_download/index.html', // ./ 表示相对于网站根目录
                    width: 800,
                    height: 500
                });

                dialog.showModal();

            });
        },
        'click:validate': function () {//检测
            //检测类型:0-一键检测，1-超重检测,2-到达检测

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                    type: 0
                };

                API.validate(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {

                    //    var result = item.result == 'success' ? '检测成功' : item.result;
                    //    return item.tid + ':' + result;

                    //}).join('\n');

                    //SMS.Tips.success(msg, 2000);

                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({
                        value: 15
                    });

                });


            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },

        'click:merge': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                };

                API.merge(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {

                    //    return item.tid + ':' + item.result;

                    //}).join('\n');

                    //SMS.Tips.success(msg, 2000);

                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({
                        value: 20
                    });

                });


            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:uncheck': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                };

                API.uncheck(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {
                    //    var result = item.result == 'success' ? '反审核成功' : item.result;

                    //    return item.tid + ':' + result;

                    //}).join('\n');

                    //SMS.Tips.success(msg, 2000);

                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({
                        value: 20
                    });

                });

            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
        'click:clearLabel': function () {

            checkItems = $('[data-btn-type=check].checkbox-item-selected');

            if (checkItems && checkItems.length > 0) {

                checkItems = $.Array.keep(checkItems, function (checkItem) {
                    return $(checkItem).attr('order-id');
                });


                var args = {
                    ids: checkItems,
                    editType: 'EditFlag',
                    EditFlag: { "flag": "0" }
                };

                API.batchOrderEdit(args, function (data) {

                    //var msg = $.Array.keep(data.results, function (item) {
                    //    var result = item.result == 'success' ? '清除标识成功' : item.result;

                    //    return item.tid + ':' + result;

                    //}).join('\n');

                    //SMS.Tips.success(msg, 2000);

                    //OrderList.render(data.orders);
                    //OrderPagination.render(data.pager);

                    ProgressBar.render({
                        value: 20
                    });

                });
            }
            else {
                SMS.Tips.warn('请选择订单', 2000);
            }
        },
    });

    ButtonList.on({
        'del': function (list) {

            var args = {
                ids: list,
            };

            API.del(args, function (data) {

                var msg = $.Array.keep(data.results, function (item) {
                    var result = item.result == 'success' ? '删除成功' : item.result;

                    return item.tid + ':' + result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            });
        },
        'orderEdit': function (args) {

            //var args = {
            //    ids: list,
            //    editType: 'EditFlag',
            //    EditFlag: { "flag": "2" }
            //};

            API.batchOrderEdit(args, function (data) {

                var msg = $.Array.keep(data.results, function (item) {
                    //var result = item.result == 'success' ? '标识成功' : item.result;

                    return item.tid + ':' + item.result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            });
        },
        'clearLabelAll': function (list) {
            var list = Filter.getSelectedOptions();

            var args = {
                filter: list,
                editType: 'EditFlag',
                EditFlag: { "flag": "0" }
            };

            API.batchOrderEdit(args, function (data) {

                var msg = $.Array.keep(data.results, function (item) {
                    var result = item.result == 'success' ? '清除标识成功' : item.result;

                    return item.tid + ':' + result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            });
        },
    });

    OrderPagination.on({
        'change': function (no, size) {

            var list = Filter.getSelectedOptions();
            var args = {
                filter: list,
                pager: {
                    page: no,
                    size: size,
                    order: ''
                }
            };

            API.list(args, function (data) {

                SMS.Tips.success('数据加载成功', 2000);
                OrderList.render(data.orders, no);

            });
        }
    });

    OrderList.on({
        'sku-fill': function (skuList, attr, popupType, no) {

            //Sku.render(skuList);
            Sku.render(OrderListData.getTestSkuList(), attr, popupType, no);
        },
        'delete': function (orderId) {

            var args = {
                ids: [orderId],
                editType: 'DeleteOrder'
            };

            API.batchOrderEdit(args, function (data) {

                //var msg = $.Array.keep(data.results, function (item) {
                //    var result = item.result == 'success' ? '删除成功' : item.result;

                //    return item.tid + ':' + result;

                //}).join('\n');


                SMS.Tips.success('订单删除成功', 2000);


                OrderList.render(list);
                //OrderPagination.render(data.pager);

            });
        },
        'split': function (orderId) {

            var options = Filter.getSelectedOptions();

            var args = {
                ids: [orderId],
                filter: options,
                pager: getPager()
            };

            API.split(args, function (data) {
                var msg = $.Array.keep(data.results, function (item) {

                    return item.tid + '：' + item.result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            }, '订单拆分中，请稍候...');

        },
        'check': function (orderId) {

            var options = Filter.getSelectedOptions();

            var args = {
                ids: [orderId],
                checker: 11,
                filter: options,
                pager: getPager()
            };

            API.check(args, function (data) {
                var msg = $.Array.keep(data.results, function (item) {

                    return item.tid + '：' + item.result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            }, '订单审核中，请稍候...');

        },
        'freeze': function (orderId, webOrderID) {

            var args = {
                editType: 'freezeOrder',
                freezeOrder: {
                    "orderID": orderId,
                    "webOrderID": webOrderID,
                    "isFreeze": "1"
                }
            };

            API.edit(args, function (data) {
                var msg = $.Array.keep(data.results, function (item) {

                    return item.tid + '：' + item.result;

                }).join('\n');

                SMS.Tips.success(msg, 2000);

                OrderList.render(data.orders);
                OrderPagination.render(data.pager);

            }, '订单冻结中，请稍候...');

        },
        'buyer-info-show': function (index, item, no, popupType) {
            BuyerInfo.render(index, item, no, popupType);
        },
        'seller-remark-show': function (index, item, no, popupType) {
            SellerRemark.render(index, item, no, popupType);

        }
    });

    BuyerInfo.on({
        'save-buyer-info': function (args, index, args2) {

            var arg = {
                editType: 'EditAddr',
                EditAddr: args
            };

            API.edit(arg, function (data) {


                //var msg = data.orderID + '：' + data.result;

                SMS.Tips.success('保存成功', 2000);

                document.getElementById('buyer-info_' + index).innerHTML = OrderList.fillBuyerInfo(args2, index);

                OrderList.updateList(index, $.extend({}, args, args2));

            }, '信息修改中，请稍候...');
        }
    });

    SellerRemark.on({
        'save-seller-remark': function (arg, index, arg2) {

            var args = {
                editType: 'EditSaleMemo',
                EditSaleMemo: arg
            };

            API.edit(args, function (data) {


                //var msg = data.orderID + '：' + data.result;

                SMS.Tips.success('保存成功', 2000);

                document.getElementById('seller-remark_' + index).innerHTML = OrderList.fillRemark(arg2, index);

                OrderList.updateList(index, arg2);

            }, '信息修改中，请稍候...');
        }
    });


    Filter.render({
        id: 'filter-panel',
        shown: 3,
        list: FilterData.list,
        pathList: FilterData.pathList,
        search: function (selectedItems) {
            console.log(selectedItems);
            load(selectedItems);
        },
        changeOptions: function (selectedItems) {
            load(selectedItems);
        }
    });

    function load(selectedItems) {
        console.log(selectedItems)

        var args = {
            filter: selectedItems,
            pager: { "page": 1, "size": OrderPagination.getSize(), "order": Sorting.getListString() }
        };

        API.list(args, function (data) {

            SMS.Tips.success('数据加载完成', 2000);
            OrderList.render(data.orders);
            OrderPagination.render(data.pager);
        });

    }


    //ProgressBar.render({
    //    value: 60,
    //});


    //function updateProgressbarValue(value) {
    //    ProgressBar.render({
    //        value: value,
    //    });

    //    if (value <= 100) {
    //        setTimeout(updateProgressbarValue, 10);
    //    }
    //    else {
    //        alert('100');

    //    }
    //}

})();