/**
 * Created by yadda on 2017/6/15.
 */

//可以生成很复杂的动态数据，并根据提交的参数进行处理。
//具有真正模拟后台逻辑的能力。
SMS.Proxy.response(function (data, config) {

    var pageSize = data.pageSize;
    var pageNo = data.pageNo;

    var total = 101;

    if (pageNo * pageSize > total) {
        pageSize = total - pageNo * pageSize;
    }

    var list = $.Array().pad(0, pageSize).keep(function (item, index) {

        return {
            'materialNumber': '10.01.00' + index,
            'materialName': 'A' + index,
            'model': 'aa.bb.cc.dd.ee.xx.yy.zz'.slice(0, $.Math.randomInt(3, 23)),
            'unit': '只',
            'orderQty': $.Math.randomInt(30, 200),
            'outStockQty': $.Math.randomInt(10, 100),
            'ReturnQty': $.Math.randomInt(10, 100),
            'stockQty': $.Math.randomInt(10, 100),
        };

    }).valueOf();

    return {

        code: 200,
        msg: 'ok',
        data: {
            total: total,
            list: list
        }

    };

});