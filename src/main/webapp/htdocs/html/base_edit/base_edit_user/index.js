;(function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var SMS = require('SMS');

	var Iframe = SMS.require('Iframe');

	//var selectors = require('SelectorList');

	var bl = require('ButtonList');
	var Edit = require('Edit');
	var DatetimePicker = require('DatetimePicker');
	var itemId = MiniQuery.Url.getQueryString(window.location.href, 'id');
    var formClassId =MiniQuery.Url.getQueryString(window.location.href, 'classId');

	var ButtonList = bl.create(itemId);

	// 基础资料类别ID
	// 支持二级事件，二级事件对应 item 中的 name
	ButtonList.on('click',{
		'optNew' : function() {
			Edit.clear();
		},
		'optAddType' : function() {
			Iframe.open(1, 6, {
				query : {

				}
			});
		},
		'optSave' : function() {
			Edit.save();
			//Edit.refresh(formClassID, selectors);
		},
		'optRefresh' : function() {
			Edit.refresh(formClassId, selectors);
		},
		'optDisable' : function() {
			Edit.forbid(formClassId, 1);
		},
		'optUndisable' : function() {
			Edit.forbid(formClassId, 0);
		}
	});

	DatetimePicker.render();
	ButtonList.render();
	//Edit.render(formClassId, itemId, selectors);
    Edit.render(formClassId, itemId);

})();
