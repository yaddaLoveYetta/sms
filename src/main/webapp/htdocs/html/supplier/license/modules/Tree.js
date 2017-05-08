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

        return treeNodes;
    };

    function render() {

        load(function (data) {
            SMS.use('zTree', function (zTree) {

                var totalSpace = new zTree('container');

            });
        });

    };


    return {
        render: render,
    };
});
