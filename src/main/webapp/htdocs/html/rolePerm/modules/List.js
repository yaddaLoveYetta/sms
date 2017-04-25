/**
 * List 模块
 * 
 */
define('List', function(require, module, exports) {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var YWTC = require('YWTC');
	var API = YWTC.require('API');
	var selectedIndex = 0;

	// 记录选中的索引
	var emitter = MiniQuery.Event.create();

	var div = document.getElementById('tbody');

	var samples = $.String.getTemplates(div.innerHTML, [ {
		name : 'tr',
		begin : '<!--',
		end : '-->'
	}, {
		name : 'td',
		begin : '#--td.begin--#',
		end : '#--td.end--#',
		outer : '{tds}'
	} ]);

	var list = [];

	function load(config, fn) {

		
		var conditions = config.conditions;
		/*
		var conditions = new Array();
		for ( var item in config.conditions) {
			if (config.conditions[item] === '') {
				continue;
			}
			var condition = {
				'andOr' : 'OR',
				'leftParenTheses' : '(',
				'fieldKey' : item,
				'logicOperator' : 'like',
				'value' : config.conditions[item],
				'rightParenTheses' : ')'
			};
			conditions.push(condition);
		}
		*/
		var api = new API('baseitem/getBaseItem');

		YWTC.Tips.loading('数据加载中..');


		api.post({
			'classID' : config.classID,
			'pageNo' : config.pageNo,
			'pageSize' : config.pageSize,
			'condition' : conditions.length > 0 ? conditions : '',
		});

		api.on({
		    'success': function (data, json) {
		        YWTC.Tips.success('数据加载成功', 1500);
				fn && fn(data, json);
			},

			'fail' : function(code, msg, json) {
				var s = $.String.format('{0} (错误码: {1})', msg, code);
				YWTC.Tips.error(s);
			},

			'error' : function() {
				YWTC.Tips.error('网络繁忙，请稍候再试');
			}
		});
	}

	
	
	function render(config, fn) {
		load(config, function(data) {
			list = data.list;
			var total = data.count;

			div.innerHTML = $.Array.keep(
					list,
					function(item, no) {
						return $.String.format(samples.tr, {
							'index' : no,
							'tds' : $.String.format(samples.td, {
								'index' : no,
								'disabled-class':item.FStatus? 'disabled' : '',
								'td-FRoleID' : item.FRoleID,
								'td-FName' : item.FName,
								'td-FNumber' : item.FNumber,
								'td-FUserType' : item.FRoleType_DspName,
								'td-FVisible' : item.FStatus == true ? "是"
										: "否"
							})
						});
					}).join('');

			fn && fn(total, config.pageSize);
			bindEvents(config.multiSelect);

		});
	}

	function selectTr(index, checked) {
		$('tr[data-index=' + index + ']').siblings().removeClass("selected");
		$('tr[data-index=' + index + ']').toggleClass('selected', checked);
		selectedIndex = index;
	}

	function getSelectedItem() {
		if (selectedIndex == -1 || selectedIndex >= list.length) {
			return null;
		}
		return list[selectedIndex];
	}

	function bindEvents() {

		$(div).delegate('[data-check=item]', 'click', function(event) {
			var chk = this;
			var dIndex = +$(this).attr("data-index");
			selectTr(dIndex, true);
			emitter.fire('row.click', [ list[dIndex] || null ]);
		});
	}

	return {
		render : render,
		selectTr : selectTr,
		getSelectedItem : getSelectedItem,
		on : emitter.on.bind(emitter)

	};

});
