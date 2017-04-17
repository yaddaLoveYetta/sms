

/**
* 分页器模块
* 
*/
define('Pager', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    function render(config) {

        config = $.Object.extend({
            current: 1,                     //当前激活的页码，默认为 1
            error: function (msg) {
                SMS.Tips.error(msg, 1500);
                this.focus();
            }

        }, config, {

            container: '#div-pager-simple', //分页控件的容器
            
        });

        SMS.SimplePager.create(config);
    }




    return {

       render: render,
    };

});






    