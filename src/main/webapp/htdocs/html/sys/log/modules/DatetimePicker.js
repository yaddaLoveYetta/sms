/**
 * 日期时间控件初始化
 */
define('DatetimePicker', function (require, module, exports) {

    function render() {

        SMS.use('DateTimePicker', function (DateTimePicker) {

            var beginTime = new DateTimePicker('#beginTime', {
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour'
            });

            var endTime = new DateTimePicker('#endTime', {
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                todayBtn: true,
                todayHighlight: true,
                startView: 'month',
                minView: 'hour'
            });

        });
    }

    return {
        render: render
    };

});

