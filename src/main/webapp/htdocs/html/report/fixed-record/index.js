;(function () {
    //alert('hello word');

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    var Data = require("Data");

    var Order = require("Order");
    var Trend = require("Trend");
    var List = require("List");

//那个7日图，是近7日的申请单数量，右侧的是，截止到当天，未完成的申请单，完成的不用显示

    Data.getData(function (data) {
        Order.render(data.trendQty);
        Trend.render(data.workload);
        List.render(data.list);
    });
})();