


define('OrderListData', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var API = require('API');

    var skuList = [
        {
            type: 1,
            list: [
                {
                    skuName: '白色',
                    skuValue: 1
                },
                {
                    skuName: '红色',
                    skuValue: 2
                },
                {
                    skuName: '绿色',
                    skuValue: 3
                },
                {
                    skuName: '蓝色',
                    skuValue: 4
                }
            ]
        },
        {
            type: 2,
            list: [
                {
                    skuName: '20ml',
                    skuValue: 1
                },
                {
                    skuName: '30ml',
                    skuValue: 2
                },
                {
                    skuName: '40ml',
                    skuValue: 3
                },
                {
                    skuName: '50ml',
                    skuValue: 4
                },
                {
                    skuName: '120ml',
                    skuValue: 5
                },
                {
                    skuName: '1200ml',
                    skuValue: 6
                }
            ]
        }
    ];
    //skuTitle: 名字；skuName：显示的值；skuValue：数据库的值；skuType：sku显示类型；type：用于在skuList中找出键值对
    var list = [
        {
            tid: '564515151',
            payTime: '2014-06-23 09:40',
            orderStatus: '已合并',
            buyerNick: '齐天大圣',
            deliveryState: '广东省',
            deliveryCity: '深圳市',
            deliveryDistrict: '南山区',
            deliveryAddress: '科技南十二路',
            logisticsCompanyName: '顺丰',
            logisticsCompany: 1,
            buyerMessage: '请给我寄圆通快递',
            outerMemo: '收货地址修改为：广东省深圳市南山区科技园科技男十二路',
            payAmount: 1000,
            goodsItems: [
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛',
                    price: 920.00,
                    amount: 12,
                    gift: 1,
                    absence: 1,
                    refund: 1,
                    flag: 1,
                    skuname:
                        [{
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '50ml',
                            skuValue: 4,
                            skuType: 1,
                            type: 1

                        },
                        {
                            skuTitle: '颜色',
                            skuName: '红色',
                            skuValue: 2,
                            skuType: 1,
                            type: 2

                        },
                        {
                            skuTitle: '数量',
                            skuName: '100',
                            skuValue: '100',
                            skuType: 2,
                            type: 0

                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                        ]
                },
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '哇哈哈哈哈',
                    price: 920.00,
                    amount: 12,
                    gift: 0,
                    absence: 1,
                    refund: 1,
                    flag: 0,
                    skuname: [
                        {
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '40ml',
                            skuValue: 3,
                            skuType: 1,
                            type: 1
                        },
                        {
                            skuTitle: '颜色',
                            skuName: '白色',
                            skuValue: 1,
                            skuType: 1,
                            type: 2
                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                    ]
                },
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '哇哈哈哈哈',
                    price: 920.00,
                    amount: 12,
                    gift: 0,
                    absence: 1,
                    refund: 1,
                    flag: 0,
                    skuname: [
                        {
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '50ml',
                            skuValue: 4,
                            skuType: 1,
                            type: 1
                        },
                        {
                            skuTitle: '颜色',
                            skuName: '红色',
                            skuValue: 2,
                            skuType: 1,
                            type: 2
                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                    ]
                }
            ]


        },
        {
            tid: '56451515221',
            payTime: '2014-06-23 09:40',
            orderStatus: '已合并',
            buyerNick: '齐天大圣',
            deliveryState: '广东省',
            deliveryCity: '深圳市',
            deliveryDistrict: '南山区',
            deliveryAddress: '科技南十二路',
            logisticsCompanyName: '顺丰',
            buyerMessage: '请给我寄圆通快递',
            outerMemo: '收货地址修改为：广东省深圳市南山区科技园科技男十二路',
            goodsItems: [
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛',
                    price: 920.00,
                    amount: 12,
                    gift: 1,
                    absence: 1,
                    refund: 1,
                    flag: 1,
                    skuname:
                        [{
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '50ml',
                            skuValue: 4,
                            skuType: 1,
                            type: 1
                        },
                        {
                            skuTitle: '颜色',
                            skuName: '红色',
                            skuValue: 2,
                            skuType: 1,
                            type: 2
                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                        ]
                }
            ]


        },
        {
            tid: '564515151',
            payTime: '2014-06-23 09:40',
            orderStatus: '已合并',
            buyerNick: '齐天大圣',
            deliveryState: '广东省',
            deliveryCity: '深圳市',
            deliveryDistrict: '南山区',
            deliveryAddress: '科技南十二路',
            logisticsCompanyName: '顺丰',
            buyerMessage: '请给我寄圆通快递',
            outerMemo: '收货地址修改为：广东省深圳市南山区科技园科技男十二路',
            goodsItems: [
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛雅诗兰黛',
                    price: 920.00,
                    amount: 12,
                    gift: 1,
                    absence: 1,
                    refund: 1,
                    flag: 1,
                    skuname:
                        [{
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '50ml',
                            skuValue: 4,
                            skuType: 1,
                            type: 1
                        },
                        {
                            skuTitle: '颜色',
                            skuName: '红色',
                            skuValue: 2,
                            skuType: 1,
                            type: 2
                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                        ]
                },
                {
                    picPath: '../../css/order/img/u283.png',
                    title: '哇哈哈哈哈',
                    price: 920.00,
                    amount: 12,
                    gift: 0,
                    absence: 1,
                    refund: 1,
                    flag: 0,
                    skuname: [
                        {
                            skuTitle: '商品编号',
                            skuName: 'B1251534',
                            skuValue: 'B1251534',
                            skuType: 0,
                            type: 0
                        },
                        {
                            skuTitle: '净含量',
                            skuName: '50ml',
                            skuValue: 4,
                            skuType: 1,
                            type: 1
                        },
                        {
                            skuTitle: '颜色',
                            skuName: '红色',
                            skuValue: 2,
                            skuType: 1,
                            type: 2
                        },
                        {
                            skuTitle: '仓库',
                            skuName: '1000',
                            skuType: 3,
                            type: 0
                        },
                        {
                            skuTitle: '库位',
                            skuName: '1001',
                            skuType: 3,
                            type: 0
                        }
                    ]
                }
            ]


        }

    ];

    var sortList = {
        "sq": [
         {
             "sequenceID": 5, "name": "订单时间", "value": "createTime", "shown": true
         },
         {
             "sequenceID": 6, "name": "付款时间", "value": "payTime", "shown": true
         }
        ],
        "allsq": [
            { "sequenceID": 5, "name": "订单时间", "value": "createTime", "shown": true },
            { "sequenceID": 6, "name": "付款时间", "value": "payTime", "shown": true },
            { "sequenceID": 7, "name": "买家昵称", "value": "buyerNick", "shown": false },
            { "sequenceID": 8, "name": "实收金额", "value": "payment", "shown": false },
            { "sequenceID": 9, "name": "物流公司", "value": "logisticsCompanyName", "shown": false },
            { "sequenceID": 10, "name": "收货地址", "value": "concat(deliveryStateID,deliveryCityID,deliveryDistrictID,deliveryAddress)", "shown": false }
        ]
    };

    var pager = {
        "page": 1,
        "size": 20,
        "order": "",
        "total": 230
    }

    function getDefaultFilter() {
        return [
           {
               "itemID": 59,
               "type": 0,
               "value": "1"
           }
        ];
    }

    function load(fn1, fn2, fn3, fn4, fn5) {
        console.log(getDefaultFilter());
        API.list({
            pager: {
                page: 1,
                size: 20,
                order: "orderid asc"
            },
            filter: getDefaultFilter()
        }, function (data) {

            fn1(data.orders);
            fn2(sortList);
            //fn2(sortList);
            fn3(data.pager);
            fn4(skuList);
            fn5();


        });

        //fn1(list);
        //fn2(sortList);
        //fn3(pager);
        //fn4(skuList);
    }


    return {
        load: load,
        getDefaultFilter: getDefaultFilter,
        getTestSkuList: function () {
            return list[0].goodsItems[0].skuname;
        }
    }


});
