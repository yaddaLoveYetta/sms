

define('DatetimePicker', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    function bindEvents() {

        KERP.use('DateTimePicker', function (DateTimePicker) {

            var dtp = new DateTimePicker('#startTime', {
                format: 'yyyy-mm-dd hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour',
            });


        });

        KERP.use('DateTimePicker', function (DateTimePicker) {

            var dtp = new DateTimePicker('#endTime', {
                format: 'yyyy-mm-dd hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour',
            });


        });


        $('#date-range-confirm').on('click', function () {

            $('#startTime').val('');
            $('#endTime').val('');

        });
    }

    function render() {
        bindEvents();
    }

    return {
        render: render
    }

});
