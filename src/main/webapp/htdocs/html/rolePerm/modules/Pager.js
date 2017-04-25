/**
 * 分页器模块
 *
 */
define('Pager', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var YWTC = require('YWTC');

    function render(config) {

        config = $.Object.extend({
            current: 1, //当前激活的页码，默认为 1
            error: function (msg) {
                YWTC.Tips.error(msg, 1500);
                this.focus();
            }
        }, config, {

            container: {
                simple: '#div-pager-simple', //简易分页控件的容器
                normal: '#div-pager-normal' //标准分页控件的容器
            }
        });

        //YWTC.SimplePager.create(config);
        YWTC.Pagers.create(config);

    }

    return {
        render: render
    };

});

