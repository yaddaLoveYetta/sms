define('DatetimePicker', function (require, module, exports) {

    function render() {

        SMS.use('DateTimePicker', function (DateTimePicker) {

            var beginTime = new DateTimePicker('#beginDate', {
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour'
            });

            var endTime = new DateTimePicker('#endDate', {
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour'
            });

        });

        $('#beginDate').val($.Date.format(new Date(), 'yyyy-MM-dd 00:00:00'));

    }

    return {
        render: render
    };

});

