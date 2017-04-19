;
(function() {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var YWTC = require('YWTC');
	var Iframe = YWTC.require('Iframe');
	var API = YWTC.require('API');

	var List = require('List');
	var Pager = require('Pager');
	var bl = require('ButtonList');
	// var CascadeNavigator = require('CascadeNavigator');
	var ClassMapping = require('ClassMapping');
	var MessageBox = YWTC.require('MessageBox');
	var PaymentDialog = require('PaymentDialog');
	var RefundmentDialog = require("RefundmentDialog");
	var txtSimpleSearch = document.getElementById('txt-simple-search');
	var conditions = {};
	var filterConditions = {};
	// 状态默认查询条件
	conditions['FStatus'] = {
		'andOr': 'AND',
		'leftParenTheses': '(',
		'fieldKey': 'FStatus',
		'logicOperator': '=',
		'value': false,
		'rightParenTheses': ')'
	};
	var classID = MiniQuery.Url.getQueryString(window.location.href, 'classID');

	// 检查登录
	if(!YWTC.Login.check(true)) {
		return;
	}

	// 列表默认配置
	var defaults = {
		pageSize: 10,
		typeID: '',
		pageNo: 1,
		hasBreadcrumbs: true,
		multiSelect: false
	};

	var firstRender = true;
	var primaryKey;

	var dialog = Iframe.getDialog();
	if(dialog) {
		var data = dialog.getData();
		defaults = $.Object.extend(defaults, data);
		if(defaults.typeID != '') {
			// conditions = {
			// classID : defaults.typeID
			// };
			conditions['classID'] = {
				'andOr': 'OR',
				'leftParenTheses': '(',
				'fieldKey': 'classID',
				'logicOperator': '=',
				'value': defaults.typeID,
				'rightParenTheses': ')'
			};
		}
		if(data.conditions) {
			$.Object.extend(conditions, data.conditions);
			// 新增查询条件
		}

		dialog.on({
			close: function() {
				//
				window.top && window.top.$("#div-tips").hide();
				getData();
			}
		});
	}

	$("#txt-simple-search").prop("placeholder", "代码/名称")
		/**
		 * 列表按钮配置
		 */
	var blConfig;
	if(classID == 13001) { // 物业公司
		blConfig = {
			'items': [{
				text: '新增',
				name: 'add',
			}, {
				text: '删除',
				name: 'delete',
			}, {
				text: '刷新',
				name: 'refresh',
			}, {
				text: '参数设置',
				name: 'setParameter',
			}, {
				text: '禁用',
				name: 'disable',
				items: [{
					text: '反禁用',
					name: 'enable',
				}],
			}, {
				text: '显示禁用项',
				name: 'showDisable',
			}]
		};
	} else if(classID == 13002) {
		blConfig = {
			'items': [{
				text: '新增',
				name: 'add',
			}, {
				text: '删除',
				name: 'delete',
			}, {
				text: '刷新',
				name: 'refresh',
			}, {
				text: '禁用',
				name: 'disable',
				items: [{
					text: '反禁用',
					name: 'enable',
				}],
			}, {
				text: '更多',
				name: 'more',
				cssClass: 'btn-more',
				items: [{
					text: '导入车场',
					name: 'import-park'
				}]
			}, {
				text: '显示禁用项',
				name: 'showDisable',
			}]
		};
	} else if(classID == 13003) {
		blConfig = {
			'items': [{
				text: '新增',
				name: 'add'
			}, {
				text: '删除',
				name: 'delete'
			}, {
				text: '刷新',
				name: 'refresh'
			}, {
				text: '禁用',
				name: 'disable',
				items: [{
					text: '反禁用',
					name: 'enable',
				}],
			}, {
				text: '显示禁用项',
				name: 'showDisable',
			}, {
				text: '二维码',
				name: 'qrcode'
			}]
		};
	} else if(classID == 13006) {
		$("#txt-simple-search").prop("placeholder", "手机号/昵称")
		blConfig = {
			'items': [{
				text: '刷新',
				name: 'refresh',
			}, {
				text: '禁用',
				name: 'disable',
				items: [{
					text: '反禁用',
					name: 'enable',
				}]
			}, {
				text: '显示禁用项',
				name: 'showDisable',
			}]
		};
	} else if(classID == 13008) {
		blConfig = {
			items: [{
				text: '新增',
				name: 'add'
			}, {
				text: '删除',
				name: 'delete'
			}, {
				text: '刷新',
				name: 'refresh'
			}, {
				text: '月租缴费',
				name: 'payment'
			}, {
				text: '月租退款',
				name: 'refundment'
			}, {
				text: '储值车缴费',
				name: 'czcpayment'
			}, {
				text: '过滤',
				name: 'filter'
			}, {
				text: '禁用',
				name: 'disable',
				items: [{
					text: '反禁用',
					name: 'enable',
				}],
			}, {
				text: '显示禁用项',
				name: 'showDisable',
			}]
		};
	}
	/**
	 * F7设置回调数据
	 */
	function getData() {
		var item = (List.getSelectedItems()[0] && List.getSelectedItems()[0].data) ? List
			.getSelectedItems()[0].data : {};

		if(item) {
			dialog.setData([{
				'ID': item[primaryKey],
				'name': item.FName,
				'number': item.FNumber,
				'itemData': item
			}]);
		}
	}

	function getTabName(classID) {

		switch(classID) {
			case '13001':
				return '物业公司';
				break;
			case '13002':
				return '车场';
				break;
			case '13003':
				return '合作商家';
				break;
			case '13004':
				return '用户';
				break;
			case '13005':
				return '角色';
				break;
			case '13005':
				return '角色';
				break;
			case '13006':
				return '移动用户';
				break;
			case '13007':
				return '车辆类别';
				break;
			case '13008':
				return '车辆信息';
				break;
			case '13009':
				return '岗亭用户';
				break;
			default:
				return '基础资料-' + classID;
		}

	}

	function getEditPage(classID) {

		switch(classID) {
			case '13001':
				return 'html/base_edit/base_edit_company/index.html';
				break;
			case '13002':
				return 'html/base_edit/base_edit_part/index.html';
				break;
			case '13003':
				return 'html/base_edit/base_edit_merchant/index.html';
				break;
			case '13004':
				return 'html/base_edit/base_edit_user/index.html';
				break;
			case '13005':
				return 'html/base_edit/base_edit_role/index.html';
				break;
			case '13006':
				return 'html/base_edit/base_edit_appuser/index.html';
				break;
			case '13007':
				return 'html/base_edit/base_edit_carType/index.html';
				break;
			case '13008':
				return 'html/base_edit/base_edit_carGroup/index.html';
				break;
			case '13009':
				return 'html/base_edit/base_edit_clientuser/index.html';
				break;
			default:
				return 'html/base_edit/base_edit_merchant/index.html';
		}

	}

	$(document).bind('keypress', function(event) {
		if(event.keyCode == 13) {
			refresh();
		}

	});
	$(txtSimpleSearch).bind('keypress', function(event) {
		if(event.keyCode == 13) {
			refresh();
			return false;
		}
	});

	var ButtonList = bl.create(blConfig);
	if(dialog) {
		var dialogBlConfig;
		if(classID == 13008) { //车辆信息新增过滤条件
			dialogBlConfig = {
				items: [{
					text: '过滤',
					name: 'filter'
				}]
			};
			ButtonList = bl.create(dialogBlConfig);
			ButtonList.render();
		}
	}
	if(!dialog) {
		ButtonList.render();
	}

	// 支持二级事件，二级事件对应 item 中的 name
	ButtonList.on('click', {

		'add': function(item, index) {

			var index = ClassMapping.getIndex(classID);
			if(index > 0) {
				Iframe.open(index.first, index.second, {
					query: {

					}
				});
			} else {
				Iframe.open({
					id: classID + '-add-' + classID,
					name: '新增-' + getTabName(classID),
					url: getEditPage(classID),
				});
			}
		},
		'add-category': function(item, index) {
			if(classID == 10110) {
				Iframe.open(1, 6, {
					query: {

					}
				});
			} else {
				Iframe.open(1, 6, {
					query: {
						classID: classID
					}
				});
			}
		},

		'delete': function(item, index) {
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要删除的项');
				return;
			}
			MessageBox.confirm('确定删除选择的项?', function(result) {
				if(result) {
					List.del(classID, list, function() {
						refresh();
					});
				}
			});
			// if (confirm('确定删除选择的项')) {
			// List.del(classID, list, function() {
			// refresh();
			// });
			// }
		},
		'showDisable': function() {
			conditions['FStatus'] = {
				'andOr': 'AND',
				'leftParenTheses': '(',
				'fieldKey': 'FStatus',
				'logicOperator': '=',
				'value': true,
				'rightParenTheses': ')'
			};

			var e = $.Event("keypress");
			e.keyCode = 13;
			$(txtSimpleSearch).trigger(e);

			// refresh();
		},
		'refresh': function(item, index) {
			conditions['FStatus'] = {
				'andOr': 'AND',
				'leftParenTheses': '(',
				'fieldKey': 'FStatus',
				'logicOperator': '=',
				'value': false,
				'rightParenTheses': ')'
			};

			var e = $.Event("keypress");
			e.keyCode = 13;
			$(txtSimpleSearch).trigger(e);
			// refresh();
		},
		'disable': function(item, index) {
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要禁用的项');
				return;
			}
			List.forbid(classID, list, 1, function() {
				refresh();
			});
		},
		'enable': function(item, index) {
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要反禁用的项');
				return;
			}
			List.forbid(classID, list, 0, function() {
				refresh();
			});
		},
		'export': function(item, index) {

		},
		'import-park': function(item, index) {
			// 导入车场
			console.log('item');
		},
		'import': function(item, index) {
			console.log(item);
		},
		'setting': function(item, index) {

		},
		'more': function(item, index) {
			ButtonList.toggle(index);
		},
		'copy': function(item, index) {

		},
		'payment': function(item, index) {
			// 缴费
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要缴费的项');
				return;
			}
			var selData = list[0];
			var payType = selData.data.FPayType || 0;
			var yebaocheArr = [51305, 51309, 51310, 51311, 51312, 51313,
				51314, 51315
			];
			// 月租车ID集合
			if($.inArray(payType, yebaocheArr) < 0) { // 所选车辆不是月租车，则提示不可进行充值操作
				YWTC.Tips.error('非月租车不可操作');
				return;
			}
			PaymentDialog.showDialog(selData, refresh);
		},
		'refundment': function() {
			//退款
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要退款的项');
				return;
			}
			var selData = list[0];
			var payType = selData.data.FPayType || 0;
			var yebaocheArr = [51305, 51309, 51310, 51311, 51312, 51313,
				51314, 51315
			];
			// 月租车ID集合
			if($.inArray(payType, yebaocheArr) < 0) { // 所选车辆不是月租车，则提示不可进行充值操作
				YWTC.Tips.error('非月租车不可操作');
				return;
			}
			var newDate = $.Date.format(new Date(), "yyyy-MM-dd");
			if(selData.FEndDate <= newDate) {
				YWTC.Tips.error('该月租车已过期');
				return;
			}
			RefundmentDialog.showDialog(selData, refresh);
		},
		'qrcode': function(item, index) { // 二维码
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要查看的项');
				return;
			}
			var selData = list[0];
			YWTC.use('Dialog', function(Dialog) {
				var dialog = new Dialog({
					title: '二维码',
					url: '../htdocs/dialog/qrcode/index.html',
					data: selData,
					button: [{
						value: '关闭',
						className: 'ywtc-submit-btn',
						autofocus: true,
						callback: function() {
							refresh();
						}
					}]
				});

				dialog.showModal();
			});
		},
		'filter': function(item, index) {
			var items = List.getfilterItems();
			YWTC.use('Dialog', function(Dialog) {
				var dialog = new Dialog({
					title: '数据过滤',
					url: '../htdocs/dialog/base-filter/index.html',
					data: items,
					filterConditions: filterConditions,
					width: 550,
					button: [{
						className: 'ywtc-cancel-btn',
						value: '取消',
						callback: function() {}
					}, {
						value: '确定',
						className: 'ywtc-submit-btn',
						autofocus: true,
						callback: function() {
							this.isSubmit = true;
							dialog.__dispatchEvent('get');
							var dialogData = dialog.getData();
							filterConditions = dialogData;
							refresh(dialogData);
						}
					}]
				});

				dialog.showModal();
			});
		},
		'setParameter': function(item, index) {
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要设置的项');
				return;
			}
			var selData = list[0];
			YWTC.use('Dialog', function(Dialog) {
				var dialog = new Dialog({
					title: '物业参数设置',
					url: '../htdocs/dialog/PropertyCompany/parameter/index.html',
					data: selData,
					width: 950,					
					button: [{
						value: '关闭',
						className: 'ywtc-submit-btn',
						autofocus: true,
						callback: function() {
							// refresh();
						}
					}]
				});

				dialog.showModal();
			});
		},'czcpayment': function(item, index) {
			var list = List.getSelectedItems();
			if(list.length == 0) {
				YWTC.Tips.error('请选择要缴费的项');
				return;
			}
			var selData = list[0];
			var payType = selData.data.FPayType || 0;
			var czcarArr = [51316, 51317, 51318, 51319]
			// 储值车ID集合
			if($.inArray(payType, czcarArr) < 0) { // 所选车辆不是储值，则提示不可进行充值操作
				YWTC.Tips.error('非储值车不可操作');
				return;
			}
			YWTC.use('Dialog', function(Dialog) {
				var dialog = new Dialog({
					title: '储值车缴费',
					url: '../htdocs/dialog/car/czcpayment/index.html',
					data: selData,
					height:100,
					button: [{
						className: 'ywtc-cancel-btn',
						value: '取消',
						callback: function() {}
					}, {
						value: '确定',
						className: 'ywtc-submit-btn',
						autofocus: true,
						callback: function() {
							return false;
						}
					}]
				});
				dialog.on({
					'beforeremove' : function() {
						var dialogData = dialog.getData();
						console.log(dialogData);
						if (dialogData.Valid) {
							refresh();
						}
					}
				});
				dialog.showModal();
			});
		}
	});

	function getPeriodCar(classId, id, fn) {
		var api = new API("baseitem/getBaseItemByID");
		api.get({
			classID: classId,
			ID: id
		});
		api.on({
			'success': function(data, json) {
				fn && fn(data, json);
			},

			'fail': function(code, msg, json) {
				var s = $.String.format('{0} (错误码: {1})', msg, code);
				YWTC.Tips.error(s);
			},

			'error': function() {
				YWTC.Tips.error('网络繁忙，请稍候再试');
			}
		});
	};
	// CascadeNavigator.render(classID);

	// CascadeNavigator.on('change', function (list) {
	// if (firstRender) {
	// firstRender = !firstRender;
	// return;
	// }

	// var length = list.length;
	// if (length == 1) {
	// conditions['parentID'] = '';
	// }
	// else{
	// conditions['parentID'] = list[length - 1]['groupID'];
	// }

	// $(txtSimpleSearch)[0].value = '';
	// conditions['name'] = '';

	// refresh();
	// });

	List.on({
		'click:FNumber': function(data, event) {

			if(dialog) {
				// 选择界面不触发
				return;
			}
			// if (classID == 13006) {
			// // APP用户不触发
			// return;
			// }

			var body = data.body;

			Iframe.open({
				id: classID + '-edit-' + body.primaryValue,
				name: '编辑-' + getTabName(classID),
				url: getEditPage(classID),
				query: {
					'id': body.primaryValue,
					'classID': classID,
				}
			});

		},
	});

	function refresh(defConditions) {
		var keyworld = $(txtSimpleSearch).val()
		conditions['FName'] = "";
		conditions['FNumber'] = "";
		if($.trim(keyworld) !== "") {
			conditions['FName'] = {
				'andOr': 'AND',
				'leftParenTheses': '((',
				'fieldKey': 'FName',
				'logicOperator': 'like',
				'value': keyworld,
				'rightParenTheses': ')'
			};
			conditions['FNumber'] = {
				'andOr': 'OR',
				'leftParenTheses': '(',
				'fieldKey': 'FNumber',
				'logicOperator': 'like',
				'value': keyworld,
				'rightParenTheses': '))'
			};
		}
		var newConditions = $.extend({}, defConditions, conditions);

		console.log('render');
		List.render({
			classID: classID,
			pageNo: 1,
			pageSize: defaults.pageSize,
			conditions: newConditions,
			multiSelect: defaults.multiSelect
		}, function(total, pageSize) {

			Pager.render({
				size: pageSize,
				total: total,
				change: function(no) {
					List.render({
						classID: classID,
						pageNo: no,
						pageSize: defaults.pageSize,
						conditions: newConditions,
						multiSelect: defaults.multiSelect
					});
				}
			});

			primaryKey = List.getPrimaryKey();

		});
	}

	refresh();

})();