;(function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var SMS = require('SMS');
	var Iframe = SMS.require('Iframe');
	var bl = require('ButtonList');
	var Edit = require('Edit');
	var DatetimePicker = require('DatetimePicker');
	var itemId = MiniQuery.Url.getQueryString(window.location.href, 'id');
    var formClassId =MiniQuery.Url.getQueryString(window.location.href, 'classId');

	var ButtonList = bl.create(itemId);

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    var dialog = Iframe.getDialog();

    if (dialog) {

        console.log('abcdefg');
        var data = dialog.getData();

        dialog.on({
            close: function () {
                //window.top && window.top.$("#div-tips").hide();
                getData();
            }
        });
    }

	// 基础资料类别ID
	// 支持二级事件，二级事件对应 item 中的 name
	ButtonList.on('click',{
		'optNew' : function() {
			Edit.clear();
		},
		'optSave' : function() {
			Edit.save();
		},
		'optRefresh' : function() {
			Edit.refresh(formClassId);
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
    Edit.render(formClassId, itemId);

})();
