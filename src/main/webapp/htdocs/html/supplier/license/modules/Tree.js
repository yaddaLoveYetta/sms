/**
 * Created by yadda on 2017/5/8.
 */

define('Tree', function (require, module, exports) {

    var $ = require('$');
    var SMS = require("SMS");
    var MiniQuery = require('MiniQuery');
    var API = SMS.require('API');

    var container = document.getElementById('tree');

    function load(fn) {

        var treeNodes = [
            {"id": 1, "pId": 0, "name": "test1"},
            {"id": 11, "pId": 1, "name": "test11"},
            {"id": 12, "pId": 1, "name": "test12"},
            {"id": 111, "pId": 11, "name": "test111"},
        ];

        fn && fn(treeNodes);
    };

    function render() {


        SMS.use('DateTimePicker', function(DateTimePicker) {

            var startTime = new DateTimePicker(container, {
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour',
            });

        load(function (data) {
            SMS.use('zTree', function (zTree) {

                var totalSpace = new zTree({
                    selector: container,
                    data: data,
                });

            });
        });

    };


    return {
        render: render,
    };
});
