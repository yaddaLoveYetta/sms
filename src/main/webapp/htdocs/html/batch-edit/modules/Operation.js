



define('Operation', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var iframe = SMS.require('Iframe');

    var dialog = iframe.getDialog();
    var emitter = MiniQuery.Event.create();

    var hasBind = false;


    function render() {

        bindEvents();

    }


    function bindEvents() {

        $('#div-box').delegate('#btn-submit', 'click', function () {
            emitter.fire('submit');

        }).delegate('#btn-cancel', 'click', function () {
            dialog.remove();
        });

    }


    return {
        render: render,
        //on:emitter.on.bind(emitter)
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }
});
