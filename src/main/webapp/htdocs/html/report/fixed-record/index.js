;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    var Data = require("Data");

    var Order = require("Order");
    var Trend = require("Trend");
    var List = require("List");

    //那个7日图，是近7日的申请单数量，右侧的是，截止到当天，未完成的申请单，完成的不用显示

    var div_order = document.getElementById('order');
    var div_trend = document.getElementById('trend');
    var div_list = document.getElementById('content');

    var div_loading = document.getElementById("loading");

    var samples = $.String.getTemplates(div_loading.innerHTML, [{
        name: "loading",
        begin: "<!--",
        end: "-->"
    }]);

    var order_loading = new SMS.Loading({
        selector: div_order,
        container: '#div-loading-order',
        text: '数据刷新中，请稍候...',
        sample: samples.loading,
    });
    var trend_loading = new SMS.Loading({
        selector: div_trend,
        container: '#div-loading-trend',
        text: '数据刷新中，请稍候...',
        sample: samples.loading,
    });
    var list_loading = new SMS.Loading({
        selector: div_list,
        container: '#div-loading-list',
        text: '数据刷新中，请稍候...',
        sample: samples.loading,
    });


    function refresh() {
        order_loading.show();
        trend_loading.show();
        list_loading.show();
        Data.getData(function (data) {
            order_loading.hide();
            trend_loading.hide();
            list_loading.hide();
            Order.render(data.trendQty);
            Trend.render(data.workload);
            List.render(data.list);

        });
    }

    List.on('refresh', function (args) {
        refresh();
    });

    refresh();

})();