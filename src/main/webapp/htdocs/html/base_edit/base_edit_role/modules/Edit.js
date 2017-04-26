/**
 * 表头数据模块
 * 
 */
define('Edit',
		function (require, module, exports) {

		    var $ = require('$');
		    var MiniQuery = require('MiniQuery');
		    var YWTC = require('YWTC');

		    var FormEdit = require('FormEdit');
		    var RoleTypeOpt = require('RoleTypeOpt');
		    var f7Selectors;
		    var baseClassID;
		    var itemId = '';

		    function render(formClassID, itemID, selectors) {
		        f7Selectors = selectors;
		        itemId = itemID;
		        baseClassID=formClassID;
		        FormEdit.render(formClassID, itemId, selectors);
		    }

		    function clear() {
		        itemId = '';
		        FormEdit.clear();
		    }

		    function save() {
		        var errorData = {};
		        //showValidInfo(null, errorData);
		        var userTypeId = f7Selectors["FRoleType"].getData()[0].ID;
		        if (userTypeId == 50702) {
		            var companyId = f7Selectors["FCompany"].getData()[0].ID;
		            if ($.trim(companyId) == "") {
		                errorData["FCompany"] = $.String.format("物业公司不可为空");
		                //showValidInfo(null, errorData);
		                //return false;
		            }
		        }

		        FormEdit.save(itemId, showValidInfo, saveSuccess, null, errorData);
		    }

		    function forbid(classID, operateType) {
		        if (!itemId) {
		            return;
		        }
		        FormEdit.forbid(classID, itemId, operateType);
		    }

		    function refresh(classID, selectors) {
		        if (!itemId) {
		            return;
		        }
		        FormEdit.render(classID, itemId, selectors);
		    }

		    function showValidInfo(successData, errorData) {
		        for (var item in successData) {

		            var msgElement = document.getElementById('bd-' + item
							+ '-msg');
		            if ($(msgElement).hasClass('show')) {
		                $(msgElement).toggleClass('show');
		            }
		            $(msgElement).html('');
		        }
		        if (errorData) {
		            for (var item in errorData) {

		                var msgElement = document.getElementById('bd-' + item
								+ '-msg');
		                if (!$(msgElement).hasClass('show')) {
		                    $(msgElement).toggleClass('show');
		                }
		                $(msgElement).html(errorData[item]);
		            }
		        }
		    }

		    function saveSuccess(data) {
		        if (itemId) {
		            YWTC.Tips.success('数据修改成功', 2000);
		        } else {
		            itemId = data['itemID'];
		            YWTC.Tips.success('数据新增成功', 2000);
		        }
				refresh(baseClassID, f7Selectors);
		    }

		    FormEdit.on({
		        "FRoleType.defaultFill": function (data) {//默认数据填充
		            var roleTypeId = data[0].ID;
		            RoleTypeOpt.render(roleTypeId);
		        },
		        "beforeShow": function () {
		        },
		        'afterFill': function (metaData, data) {
		            RoleTypeOpt.render(data.FRoleType);

		        }
		    });

		    return {
		        render: render,
		        clear: clear,
		        save: save,
		        forbid: forbid,
		        refresh: refresh
		    };

		});
