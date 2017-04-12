






$(document).dblclick(function () {


    KERP.use('DateTimePicker', function (DateTimePicker) {
     

        var dtp = new DateTimePicker('#txt', {
            format: 'yyyy-mm-dd',
            autoclose: false,
            minView: 'month',
            todayBtn: true,
            todayHighlight: true
        });


    });
});