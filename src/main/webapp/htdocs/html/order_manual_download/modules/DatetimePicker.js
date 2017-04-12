

define('DatetimePicker', function(require, module, exports) {


    function render() {

        KERP.use('DateTimePicker', function(DateTimePicker) {

            var startTime = new DateTimePicker('#startTime', {
                format: 'yyyy-mm-dd hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour',
            });

            var endTime = new DateTimePicker('#endTime', {
                format: 'yyyy-mm-dd hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour',
            });
        });

    }


    return {
        render: render
    };

});

