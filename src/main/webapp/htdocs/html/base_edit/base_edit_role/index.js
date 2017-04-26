; (function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var YWTC = require('YWTC');

	var Iframe = YWTC.require('Iframe');

	var selectors = require('SelectorList');
	var Edit = require('Edit');

	var bl = require('ButtonList');

	var itemID = MiniQuery.Url.getQueryString(window.location.href, 'id');
	var ButtonList = bl.create(itemID);
	var formClassID = 13005;
	// 基础资料类别ID

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
			Edit.refresh(formClassID, selectors);
		},
		'optRefresh' : function() {
			Edit.refresh(formClassID, selectors);
		},
		'optDisable' : function() {
			Edit.forbid(formClassID, 1);
		},
		'optUndisable' : function() {
			Edit.forbid(formClassID, 0);
		}
	});

	ButtonList.render();
	Edit.render(formClassID, itemID, selectors);

})();
