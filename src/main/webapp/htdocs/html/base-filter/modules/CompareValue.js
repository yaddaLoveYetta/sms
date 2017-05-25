define('DatetimePicker', function(require, module, exports) {


	function renderDateTimePicker() {

		YWTC.use('DateTimePicker', function(DateTimePicker) {

			var startTime = new DateTimePicker('#bd-FBeginDate', {
				format: 'yyyy-mm-dd',
				autoclose: true,
				todayBtn: true,
				todayHighlight: true,
				startView: 'month',
				minView: 'month',
			});

		});
	};
});