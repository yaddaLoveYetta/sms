

//filterType  0:带结果的单选 1:多选 2：时间 3：文本框 4范围
//shown 0: 隐藏 1：显示
//selected: 0：不是默认 1：默认
define('FilterData', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var list = {
        'showns': [
            {
                "planID": 164,
                "itemID": 44,
                "name": "成交日期",
                "filterType": "2",
                "defaultValue": "2015-01-01 00:00,2015-01-31 00:00",
                "items": [
                    {
                        "name": "今天",
                        "value": "2015-01-21 00:00,2015-01-21 23:59"
                    },
                    {
                        "name": "近三天",
                        "value": "2015-01-19 00:00,2015-01-21 23:59"
                    },
                    {
                        "name": "近七天",
                        "value": "2015-01-15 00:00,2015-01-21 23:59"
                    },
                    {
                        "name": "近一月",
                        "value": "2014-12-21 00:00,2015-01-21 23:59"
                    }
                ]
            },
            {
                "planID": 165,
                "itemID": 59,
                "name": "审核状态",
                "filterType": "1",
                "defaultValue": "1",
                "items": [
                    {
                        "name": "未审核",
                        "value": "1",
                        "select": 1
                    },
                    {
                        "name": "已审核",
                        "value": "2",
                        "select": 0
                    },
                    {
                        "name": "无需审",
                        "value": "3",
                        "select": 0
                    }
                ]
            }
        ],
        "notification": [
            {
                "name": "普通待审", "itemID": 85, "defaultValue": "320", "select": 1
            },
            {
                "name": "留言备注", "itemID": 86, "defaultValue": "72", "select": 0
            },
            {
                "name": "申请退款", "itemID": 87, "defaultValue": "85", "select": 0
            },
            {
                "name": "黑名单", "itemID": 88, "defaultValue": "32", "select": 0
            },
            {
                "name": "货到付款", "itemID": 89, "defaultValue": "15", "select": 0
            },
            {
                "name": "物流为空", "itemID": 84, "defaultValue": "28", "select": 0
            },
            {
                "name": "物流不到达", "itemID": 90, "defaultValue": "26", "select": 0
            },
            {
                "name": "物流超重", "itemID": 91, "defaultValue": "42", "select": 0
            }
        ],
        "categorys": [
            {
                "name": "订单信息",
                "items": [
                    {
                        "name": "成交日期",
                        "itemID": 44,
                        "select": 1
                    },
                    {
                        "name": "审核状态",
                        "itemID": 59,
                        "select": 1
                    },
                    {
                        "name": "交易状态",
                        "itemID": 48,
                        "select": 0
                    },
                    {
                        "name": "订单来源",
                        "itemID": 93,
                        "select": 0
                    },
                    {
                        "name": "所属网店",
                        "itemID": 49,
                        "select": 0
                    },
                    {
                        "name": "卖家旗帜",
                        "itemID": 52,
                        "select": 0
                    },
                    {
                        "name": "标签",
                        "itemID": 51,
                        "select": 0
                    },
                    {
                        "name": "交易来源",
                        "itemID": 94,
                        "select": 0
                    },
                    {
                        "name": "付款方式",
                        "itemID": 95,
                        "select": 0
                    },
                    {
                        "name": "发票抬头",
                        "itemID": 96,
                        "select": 0
                    },
                    {
                        "name": "网上订单号",
                        "itemID": 53,
                        "select": 0
                    },
                    {
                        "name": "唯一码",
                        "itemID": 50,
                        "select": 0
                    },
                    {
                        "name": "买家留言",
                        "itemID": 45,
                        "select": 0
                    },
                    {
                        "name": "卖家备注",
                        "itemID": 46,
                        "select": 0
                    }
                ]
            },
            {
                "name": "物流信息",
                "items": [
                    {
                        "name": "运送方式",
                        "itemID": 97,
                        "select": 0
                    },
                    {
                        "name": "物流公司",
                        "itemID": 60,
                        "select": 0
                    },
                    {
                        "name": "物流单号",
                        "itemID": 98,
                        "select": 0
                    }
                ]
            },
            {
                "name": "买家信息",
                "items": [
                    {
                        "name": "省",
                        "itemID": 64,
                        "select": 0,
                        "group": 1
                    },
                    {
                        "name": "市",
                        "itemID": 65,
                        "select": 0,
                        "group": 1
                    },
                    {
                        "name": "区",
                        "itemID": 66,
                        "select": 0,
                        "group": 1
                    },
                    {
                        "name": "买家昵称",
                        "itemID": 63,
                        "select": 0
                    },
                    {
                        "name": "收货人",
                        "itemID": 62,
                        "select": 0
                    },
                    {
                        "name": "收货地址",
                        "itemID": 67,
                        "select": 0
                    },
                    {
                        "name": "手机号码",
                        "itemID": 68,
                        "select": 0
                    },
                    {
                        "name": "联系电话",
                        "itemID": 69,
                        "select": 0
                    }
                ]
            },
            {
                "name": "商品信息",
                "items": [
                    {
                        "name": "商品代码",
                        "itemID": 100,
                        "select": 0
                    },
                    {
                        "name": "商品名称",
                        "itemID": 99,
                        "select": 0
                    },
                    {
                        "name": "商品条码",
                        "itemID": 108,
                        "select": 0
                    },
                    {
                        "name": "套装代码",
                        "itemID": 101,
                        "select": 0
                    },
                    {
                        "name": "套装名称",
                        "itemID": 79,
                        "select": 0
                    },
                    {
                        "name": "网上货品名",
                        "itemID": 77,
                        "select": 0
                    },
                    {
                        "name": "商家编码",
                        "itemID": 78,
                        "select": 0
                    },
                    {
                        "name": "单价",
                        "itemID": 81,
                        "select": 0
                    },
                    {
                        "name": "数量",
                        "itemID": 80,
                        "select": 0
                    },
                    {
                        "name": "实收",
                        "itemID": 47,
                        "select": 0
                    }
                ]
            },
            {
                "name": "过滤标签",
                "items": [
                    {
                        "name": "普通待审",
                        "itemID": 85,
                        "select": 1
                    },
                    {
                        "name": "留言备注",
                        "itemID": 86,
                        "select": 1
                    },
                    {
                        "name": "申请退款",
                        "itemID": 87,
                        "select": 1
                    },
                    {
                        "name": "黑名单",
                        "itemID": 88,
                        "select": 1
                    },
                    {
                        "name": "货到付款",
                        "itemID": 89,
                        "select": 1
                    },
                    {
                        "name": "物流为空",
                        "itemID": 84,
                        "select": 1
                    },
                    {
                        "name": "物流不到达",
                        "itemID": 90,
                        "select": 1
                    },
                    {
                        "name": "物流超重",
                        "itemID": 91,
                        "select": 1
                    },
                    {
                        "name": "冻结",
                        "itemID": 102,
                        "select": 1
                    },
                    {
                        "name": "未匹配商品",
                        "itemID": 92,
                        "select": 1
                    },
                    {
                        "name": "黄",
                        "itemID": 104,
                        "select": 1,
                        "pic": "yellow.jpg"
                    },
                    {
                        "name": "绿",
                        "itemID": 105,
                        "select": 1,
                        "pic": "green.jpg"
                    }
                ]
            }
        ],
        "sq": [
            {
                "name": "订单时间",
                "value": "createTime",
                "asc": false,
                "shown": true
            }
        ],
        "allsq": [
            {
                "name": "订单时间",
                "value": "createTime",
                "asc": false,
                "shown": true
            },
            {
                "name": "付款时间",
                "value": "payTime",
                "asc": false,
                "shown": false
            },
            {
                "name": "买家昵称",
                "value": "buyerNick",
                "asc": false,
                "shown": false
            }
        ]
    };


    var pathList = ['首页', '订单处理'];

    return {
        list: list,
        pathList: pathList,

    }

});

