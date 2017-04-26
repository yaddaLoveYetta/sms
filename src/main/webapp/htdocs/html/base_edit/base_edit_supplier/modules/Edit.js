/**
 * 表头数据模块
 * 
 */
define('Edit',
	function(require, module, exports) {

		var $ = require('$');
		var MiniQuery = require('MiniQuery');
		var YWTC = require('YWTC');
		var FormEdit = require('FormEdit');
		var Grid = require('Grid');
		var GridBuilder = require('GridBuilder');
		var parkGrid = new Grid('bd-grid');
		var columns = ["FParkNumber"];
		var PeriodCarOpt = require("PeriodCarOpt");
		var itemId = '';
		var baseClassID;
		var f7Selectors;
		var parkGridConfig = {
			gridName: 'bd-grid',
			width: 500,
			height: '100%',
			fnAfterSaveCell_After: function(rowid, data) {
				$('#bd-grid').jqGrid('setCell', rowid, 'roleName',
					data.name);
			},
			fnAfterEditCell_Before: function(rowId, cellName, cellValue) {
				//				Combo.getCombo().selectByText(cellValue, false);
			}
		};

		// jqGrid初始化
		var initGrid = function(entryData, metaData) {
			parkGridConfig = GridBuilder.getConfig(
				metaData['formFields'][1], parkGridConfig, columns);
			parkGrid.render(parkGridConfig, entryData, metaData, 1);
		};

		function render(formClassID, itemID, selectors) {
			baseClassID = formClassID;
			f7Selectors = selectors;
			itemId = itemID;
			FormEdit.render(formClassID, itemId, selectors, initGrid);
		}

		function clear() {
			itemId = '';
			FormEdit.clear();
			cleanGrid();
		}

		function showValidInfo(successData, errorData) {
			for(var item in successData) {

				var msgElement = document.getElementById('bd-' + item + '-msg');
				if($(msgElement).hasClass('show')) {
					$(msgElement).toggleClass('show');
				}
				$(msgElement).html('');
			}
			if(errorData) {
				for(var item in errorData) {

					var msgElement = document.getElementById('bd-' + item + '-msg');
					if(!$(msgElement).hasClass('show')) {
						$(msgElement).toggleClass('show');
					}
					$(msgElement).html(errorData[item]);
				}
			}
		}

		function saveSuccess(data) {
			if(itemId) {
				YWTC.Tips.success('数据修改成功', 2000);
			} else {
				itemId = data['itemID'];
				YWTC.Tips.success('数据新增成功', 2000);
			}
			refresh(baseClassID, f7Selectors);
			//			window.location.reload();
		}

		function save() {
			var entry = [];
			var FCarNos = [];
			var FOliCarNos = [];
			var parkIDExisted = false;
			var oliCarNoExisted = false;
			//			"FEntryID":"3655","FCarNo":"粤A02020","FCardNum":"1212121121",
			cleanGridErrorMsg();
			var errorGridData = {};
			var gridData = parkGrid.getGridDatas();
			// 新增数据
			$.Array.each(gridData["add"], function(item, index) {
				if(!item.FCarNo || item.FCarNo == '') {
					return;
				}

				var adData = {
					data: {
						"FCarNo": item.FCarNo,
						"FCardNum": item.FCardNum
					},
					flag: '1'
				};
				entry.push(adData);
				if(!$.Array.contains(FCarNos, item.FCarNo)) {
					FCarNos.push(item.FCarNo);
				} else {
					parkIDExisted = true;
				}
				if($.trim(item.FCardNum) !== "") {
					if(!$.Array.contains(FOliCarNos, item.FCardNum)) {
						FOliCarNos.push(item.FCardNum);
					} else {
						oliCarNoExisted = true;
					}
				}
			});
			// 修改数据
			$.Array.each(gridData["update"], function(item, index) {
				//				delete item.bos_modify;
				//				delete item.operate;

				var upData = {
					data: {
						"FEntryID": item.FEntryID,
						"FCarNo": item.FCarNo,
						"FCardNum": item.FCardNum
					},
					flag: '2'
				};
				entry.push(upData);
				if(!$.Array.contains(FCarNos, item.FCarNo)) {
					FCarNos.push(item.FCarNo);
				} else {
					parkIDExisted = true;
				}
				if($.trim(item.FCardNum) !== "") {
					if(!$.Array.contains(FOliCarNos, item.FCardNum)) {
						FOliCarNos.push(item.FCardNum);
					} else {
						oliCarNoExisted = true;
					}
				}
			});
			// 删除数据
			$.Array.each(gridData["delete"], function(item, index) {

				var delData = {
					data: {
						"FEntryID": item.FEntryID,
						"FCarNo": item.FCarNo,
						"FCardNum": item.FCardNum
					},
					flag: '0'
				};
				entry.push(delData);
			});

			//车牌号正则表达式 验证	
			var re = /(^[\u4E00-\u9FA5]{1}[A-Z0-9]{6}$)|(^[A-Z]{2}[A-Z0-9]{2}[A-Z0-9\u4E00-\u9FA5]{1}[A-Z0-9]{4}$)|(^[\u4E00-\u9FA5]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$)|(^[A-Z]{2}[0-9]{5}$)|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)/;

			$.Array.each(FCarNos, function(item, index) {
				if(!re.test(item)) {
					errorGridData["grid"] = $.String.format("车牌号'{0}'输入有误", item);
					//showValidInfo(null, errorGridData);
				}
			});
			// var itemData = f7Selectors["FPayType"].getData()[0].ID|| {};
			var carPayType = f7Selectors["FPayType"].getData()[0].ID || 0;
			var checkResult = PeriodCarOpt.mustInputCheck(carPayType);
			if(!checkResult.IsCheck) {
				$.extend(errorGridData, checkResult.ErrorMsg); //合并错误信息
				//showValidInfo(null, errorGridData);
			}
			if(parkIDExisted) {
				errorGridData["grid"] = "存在相同车牌号";
				//showValidInfo(null, errorGridData);
			}
			if(FCarNos.length === 0) {
				errorGridData["grid"] = "车牌号不可为空";
				//showValidInfo(null, errorGridData);
			}
			if(oliCarNoExisted) {
				errorGridData["grid"] = "存在相同的联名卡号";
				//showValidInfo(null, errorGridData);
			}

			//if ($.trim(errorGridData["grid"]) != "") {
			//    return;
			//}
			var entryData = {
				1: entry
			};
			FormEdit.save(itemId, showValidInfo, saveSuccess, entryData,
				errorGridData);
		};

		function cleanGrid() {
			parkGrid.clear();
			var successData = {
				grid: ""
			};
			showValidInfo(successData, null);
		};

		function cleanGridErrorMsg() {
			// parkGrid.clear();
			var successData = {
				grid: ""
			};
			showValidInfo(successData, null);
		};

		function forbid(classID, operateType) {
			if(!itemId) {
				return;
			}
			FormEdit.forbid(classID, itemId, operateType);
		}

		function refresh(classID, selectors) {
			if(!itemId) {
				return;
			}
			cleanGrid();
			FormEdit.render(classID, itemId, selectors, initGrid);
		};

		FormEdit.on({
			'beforeShow': function(meteData) {

			},
			'getMetaData': function() {

			},
			'cascadePicker': function(data) {
				console.dir(data);
			},
			'afterFill': function(metaData, data) {
				PeriodCarOpt.render(data.FPayType, data);

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