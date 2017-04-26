

define('DatetimePicker', function(require, module, exports) {


    function render() {

        YWTC.use('DateTimePicker', function(DateTimePicker) {

            var startTime = new DateTimePicker('#bd-FBeginDate', {
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'month',
            });

            var endTime = new DateTimePicker('#bd-FEndDate', {
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'month',
            });

            var FPeriodBeginTime = new DateTimePicker('#bd-FPeriodBeginTime', {
                format: 'hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'hour',
                minView: 'hour'
            });

            var FPeriodEndTime = new DateTimePicker('#bd-FPeriodEndTime', {
                format: 'hh:ii',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'hour',
                minView: 'hour'
            });
        });

    }


    return {
        render: render
    };

});

