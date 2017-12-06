/**
 * 工单量图表
 *
 */
define('Trend', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var chart = echarts.init(document.getElementById('trend'));

    function render(data) {

        data = $.Object.toArray(data, true);
        var dateList = data.map(function (item) {
            return item[0];
        });
        var valueList = data.map(function (item) {
            return item[1];
        });

        var option = {
            title: {
                text: '工单看板',
            },
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {},
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: dateList
            },
            series: [
                {
                    name: '数量',
                    type: 'bar',
                    data: valueList
                }
            ]
        };


        chart.clear();
        // chart.hideLoading();
        chart.setOption(option);
    }

    return {
        render: render
    };

});

