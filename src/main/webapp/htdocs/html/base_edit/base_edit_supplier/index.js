;
(function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var YWTC = require('YWTC');

	var Iframe = YWTC.require('Iframe');

	var selectors = require('SelectorList');
	var bl = require('ButtonList');
	var Edit = require('Edit');
	var DatetimePicker = require('DatetimePicker');

	var itemID = MiniQuery.Url.getQueryString(window.location.href, 'id');
	var ButtonList = bl.create(itemID);
	var formClassID = 13008;
	// 基础资料类别ID

	// 异步加载 NumberField 依赖的文件，完成后创建一个数值型输入框。
	YWTC.use('NumberField', function(NumberField) {

		var fCarNum = new NumberField('#bd-FCarNum', {
			min: 0,
			empty: 0,
			padded: false
		});

		var monthFee = new NumberField("#bd-FMonthFee", {
			min: 0,
			empty: "",
			currencySign: "￥",
			padded: false
		});
		var monthFee = new NumberField("#bd-FBalance", {
			min: 0,
			empty: "",
			currencySign: "￥",
			padded: false
		});

	});

	ButtonList.on('click', {
		'optNew': function() {
			Edit.clear();
		},
		'optAddType': function() {
			Iframe.open(1, 6, {
				query: {

				}
			});
		},
		'optSave': function() {
			Edit.save();
			//			Edit.refresh(formClassID, selectors);
		},
		'optRefresh': function() {

			Edit.refresh(formClassID, selectors);
		},
		'optDisable': function() {
			Edit.forbid(formClassID, 1);
		},
		'optUndisable': function() {
			Edit.forbid(formClassID, 0);
		}
	});

	DatetimePicker.render();
	ButtonList.render();
	Edit.render(formClassID, itemID, selectors);

})();