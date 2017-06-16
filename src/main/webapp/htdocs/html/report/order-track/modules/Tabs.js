/**
 * Created by yadda on 2017/6/16.
 */

define('Tabs', function (require, exports, module) {

    var hasbind = false;

    function bindEvents() {
        if (hasbind) {
            return;
        }

        $('#tab-container')
            .bind('easytabs:before', function () {

                alert('easytabs:before fired');
            })
            .bind('easytabs:midTransition', function () {

                alert('easytabs:midTransition fired');

            })
            .bind('easytabs:after', function () {
                alert('easytabs:after fired');
            });
    }


    function render() {
        bindEvents();
    }

    module.exports = {
        render: render,
    }
});