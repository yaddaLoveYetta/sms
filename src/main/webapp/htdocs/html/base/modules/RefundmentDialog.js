define("RefundmentDialog", function(require, module, exports) {
	var $ = require('$');
	var API = YWTC.require('API');

	var config = {
		title: '月保退款',
		url: '../htdocs/dialog/car/refundment/index.html'
	}

	function showDialog(data, cfig, fn) {
		if($.isFunction(cfig)) {
			fn = cfig;
			cfig = {};
		}
		var nConfig = $.extend({}, config, cfig);
		YWTC.use('Dialog', function(Dialog) {
			var dialog = new Dialog({
				title: nConfig.title,
				url: config.url,
				width: 380,
				data: data,
				button: [{
					className: 'ywtc-cancel-btn',
					value: '取消'
				}, {
					value: '确认',
					className: 'ywtc-submit-btn',
					autofocus: true,
					callback: function() {
						this.isSubmit = true;
						return false;
					}
				}]
			});

			dialog.on({
				'beforeremove': function() {
					var dialogData = dialog.getData();
					console.log(dialogData);
					if(dialogData.Valid) {
						fn && fn();
					}
				}
			});
			dialog.showModal();
		});
	}
	return {
		showDialog: showDialog
	}
})