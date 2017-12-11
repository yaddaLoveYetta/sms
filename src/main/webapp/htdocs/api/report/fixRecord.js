//可以生成很复杂的动态数据，并根据提交的参数进行处理。
//具有真正模拟后台逻辑的能力。
SMS.Proxy.response(function (data, config) {


    var pageSize = data.pageSize; //20
    var pageNo = data.pageNo; //1

    var currentYearQty = 100;
    var currentMonthQty = 20;
    var todayQty = 5;
    var todayUndoneQty = 2
    var totalUndoneQty = 50;


    var startIndex = pageSize * (pageNo - 1);

    if (startIndex > totalUndoneQty) {
        startIndex = 0;
    }

    var endIndex = startIndex + pageSize;

    if (endIndex > totalUndoneQty) {
        endIndex = totalUndoneQty;
    }

    var list = $.Array().pad(startIndex, endIndex).keep(function (item, index) {

        return {
            reqNumber: 'req-' + $.Math.randomInt(10000, 20000), // 申请单号
            department: 'dep-' + $.Math.randomInt(123, 321), // 科室
            builder: '深圳市南山区科技园南区'.slice($.Math.randomInt(0, 9), 3),// 申请人
            reqDate: $.Date.format(new Date(), "yyyy-MM-dd hh:mm:ss"), // 申请时间
            status: ['已派工', '已确认'][$.Math.randomInt(0, 1)],// 状态
            dispatchTime: $.Date.format(new Date(), "yyyy-MM-dd hh:mm:ss"), // 派工时间
            repairMan: ['张山', '李四', '王老五'][$.Math.randomInt(0, 2)],// 维修人
            targetItem: ['飞船', '灯泡', '火箭', '坦克'][$.Math.randomInt(0, 3)],// 维修项目
            fixedTime: $.Date.format(new Date(), "yyyy-MM-dd hh:mm:ss"), // 完成时间
        };

    }).valueOf();

    return {

        code: 200,
        msg: 'ok',
        data: {
            workload: {
                currentYearQty: currentYearQty,
                currentMonthQty: currentMonthQty,
                todayQty: todayQty,
                todayUndoneQty: todayUndoneQty,
                todayDoneQty: todayQty - todayUndoneQty,
                totalUndoneQty: totalUndoneQty,
            },
            trendQty: {
                d1: 33,
                d2: 22,
                d3: 55,
                d4: 18,
                d5: 99,
                d6: 31,
                d7: 9,
            },
            list: list
        }

    };

});

