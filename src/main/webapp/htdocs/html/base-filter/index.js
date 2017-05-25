;
(function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var YWTC = require('YWTC');
	var Iframe = YWTC.require('Iframe');
	var Filter = require('Filter');

	// 获取从dialog传入iframe的数据
	var dialog = Iframe.getDialog();
	var iframeData = dialog.getData();
	var filterConditions = dialog.filterConditions;
	var $qrcode = "";
	if (iframeData) {
		console.log(iframeData)
		Filter.render(iframeData, function() {
			Filter.setDefaultFilter(filterConditions);
		});
	}

	dialog.on({
		get : function() {
			var data = Filter.getFilterObject();

			dialog.setData(data);
		}
	});

	Filter.on({
		'render.done' : function() {

		}
	});

})();