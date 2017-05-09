/**
 * Created by yadda on 2017/5/8.
 */

define('Tree', function (require, module, exports) {

    var $ = require('$');
    var SMS = require("SMS");
    var MiniQuery = require('MiniQuery');
    var API = SMS.require('API');
    var emitter = MiniQuery.Event.create();

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


        load(function (data) {

            SMS.use('ZTree', function (zTree) {

                var tree = new zTree({
                    selector: 'container',
                    data: data,
                });

                tree.on({

                    onClick: function (event, treeId, treeNode) {
                        console.log(treeNode.id + ", " + treeNode.name);
                        emitter.fire("onClick" , [treeNode]);
                    },
                });

            });
        });

    };


    return {
        render: render,
        on: emitter.on.bind(emitter),
    };
});
