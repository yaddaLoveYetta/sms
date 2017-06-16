/**
 * Created by yadda on 2017/6/16.
 */

define('Tabs', function (require, module, exports) {

    var MiniQuery = require("MiniQuery");
    var emitter = MiniQuery.Event.create();

    var hasbind = false;

    function bindEvents() {
        if (hasbind) {
            return;
        }

        $('#tab-container')
            .bind('easytabs:before', function (event, $clicked, $targetPanel, settings) {

                alert('easytabs:before fired' + $clicked.eq(0).parent().attr('id'));
            })
            .bind('easytabs:midTransition', function (event, $clicked, $targetPanel, settings) {

                alert('easytabs:midTransition fired' + $clicked.eq(0).parent().attr('id'));

            })
            .bind('easytabs:after', function (event, $clicked, $targetPanel, settings) {
                alert('easytabs:after fired' + $clicked.eq(0).parent().attr('id'));
            });
    }


    function render() {
        bindEvents();
    }

    module.exports = {
        render: render,
    }
});