/**
 * 时段月租车操作模块
 */
define(
	'PeriodCarOpt',
	function(require, module, exports) {
		var $ = require('$');
		var YWTC = require('YWTC');
		var API = YWTC.require("API");
		var classId = 13007;
		var editData = {};
		var isFirstLoad = false;
		var isUpdate = false;
		var monthCar = [51305, 51313, 51314, 51315]; // 月租车
		var NotNeedTimeCar = [51301, 51302, 51303, 51304, 51307, 51308]; // 临时车：不需要开始时间、结束时间、
		// 时段月租开始时间、时段月租结束时间
		var NeedTimeCar = [51306]; // 免费车：开始时间、结束时间 必填
		var NeedAllTimeCar = [51309, 51310, 51311, 51312]; // 时段月租车：开始时间、结束时间、

		var czcArrays = [51316, 51317, 51318, 51319]; //储值车
		// 时段月租车所用Key
		var bindIndex = "#bd-";
		var periodBeginTime = "FPeriodBeginTime";
		var periodEndTime = "FPeriodEndTime";
		var beginTime = "FBeginDate";
		var endTime = "FEndDate";
		// //其他车所用Key
		// var periodBeginTime1 = "bd-FPeriodBeginTime1";
		// var periodEndTime1 = "bd-FPeriodEndTime1";

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

		function mastInputClass(mastInput, element) {
			// 必填处理
			if(mastInput) {
				// 如果是必填需要添加 红色 * 号
				if($(element).parents("td").siblings().find(".must-mark").length <= 0) { // 如果不存在
					var html = $(element).parents("td").siblings().html();
					$(element).parents("td").siblings().html(
						"<span class=\"must-mark\">*</span>" + html);
				}
			} else {
				if($(element).parents("td").siblings().find(".must-mark")) {
					$(element).parents("td").siblings().find(".must-mark")
						.remove();
				}
			}
		}

		function disabledElement(isDisabled, element) {
			if(isDisabled) {
				$(element).prop("disabled", "disabled");
			} else {
				$(element).removeProp("disabled");
			}
		}

		function render(id, data) {
			if(!isFirstLoad) {
				editData = data;
				if(data || !$.Object.isEmpty(data)) {
					isUpdate = true;
				}
				isFirstLoad = true;
			}

			// getPeriodCar(classId, id, function (data) {
			$("#addTips").hide();
			var payType = id;
			$("#bd-FMonthFee").parents("tr").show();
			if(payType) {
				if($.inArray(payType, czcArrays) >= 0) { //储值车
					mastInputClass(false, $(bindIndex + periodBeginTime));
					mastInputClass(false, $(bindIndex + periodEndTime));
					mastInputClass(false, $(bindIndex + beginTime));
					mastInputClass(false, $(bindIndex + endTime));
					mastInputClass(false, $("#bd-FCarNum"));
					mastInputClass(false, $("#bd-FMonthFee"));
					//					mastInputClass(true, $("#bd-FBalance"));

					$(bindIndex + periodBeginTime).parents("tr").hide();
					$(bindIndex + periodEndTime).parents("tr").hide();
					$(bindIndex + beginTime).parents("tr").hide();
					$(bindIndex + endTime).parents("tr").hide();
					$("#bd-FCarNum").parents("tr").hide();
					$("#bd-FMonthFee").parents("tr").hide();
					$("#bd-FBalance").parents("tr").show();
				}
				if($.inArray(payType, NeedAllTimeCar) >= 0) { // 时段月租车
					$("#addTips").show();
					mastInputClass(true, $(bindIndex + periodBeginTime));
					mastInputClass(true, $(bindIndex + periodEndTime));
					mastInputClass(false, $(bindIndex + beginTime));
					mastInputClass(false, $(bindIndex + endTime));
					mastInputClass(false, $("#bd-FBalance"));
					$(bindIndex + periodBeginTime).parents("tr").show();
					$(bindIndex + periodEndTime).parents("tr").show();
					$(bindIndex + beginTime).parents("tr").hide();
					$(bindIndex + endTime).parents("tr").hide();
					$("#bd-FBalance").parents("tr").hide();
					if(isUpdate) {
						disabledElement(true, $(bindIndex + beginTime));
						disabledElement(true, $(bindIndex + endTime));
						$(bindIndex + beginTime).parents("tr").show();
						$(bindIndex + endTime).parents("tr").show();
//						$(bindIndex + beginTime).val(editData.FBeginDate || "");
//						$(bindIndex + endTime).val(editData.FEndDate || "");
					}
				}
				if($.inArray(payType, NeedTimeCar) >= 0) { // 免费车
					mastInputClass(false, $("#bd-FBalance"));
					mastInputClass(false, $(bindIndex + periodBeginTime));
					mastInputClass(false, $(bindIndex + periodEndTime));
					mastInputClass(true, $(bindIndex + beginTime));
					mastInputClass(true, $(bindIndex + endTime));
					$("#bd-FBalance").parents("tr").hide();
					$(bindIndex + periodBeginTime).parents("tr").hide();
					$(bindIndex + periodEndTime).parents("tr").hide();
					$(bindIndex + beginTime).parents("tr").show();
					$(bindIndex + endTime).parents("tr").show();
					if(isUpdate) {
						if(payType == 51306) { // 免费车开放编辑开始结束时间
							disabledElement(false, $(bindIndex + beginTime));
							disabledElement(false, $(bindIndex + endTime));
						} else {
							disabledElement(true, $(bindIndex + beginTime));
							disabledElement(true, $(bindIndex + endTime));
							$(bindIndex + beginTime).val(editData.FBeginDate || "");
							$(bindIndex + endTime).val(editData.FEndDate || "");
						}

					}
				}
				if($.inArray(payType, NotNeedTimeCar) >= 0 || $.inArray(payType, monthCar) >= 0) { // 临时车,月租车
					mastInputClass(false, $(bindIndex + periodBeginTime));
					mastInputClass(false, $(bindIndex + periodEndTime));
					mastInputClass(false, $(bindIndex + beginTime));
					mastInputClass(false, $(bindIndex + endTime));
					mastInputClass(false, $("#bd-FBalance"));
					$(bindIndex + periodBeginTime).parents("tr").hide();
					$(bindIndex + periodEndTime).parents("tr").hide();
					$(bindIndex + beginTime).parents("tr").hide();
					$(bindIndex + endTime).parents("tr").hide();
					$("#bd-FBalance").parents("tr").hide();
					if($.inArray(payType, monthCar) >= 0) {
						$("#addTips").show();
						if(isUpdate) {
							disabledElement(true, $(bindIndex + beginTime));
							disabledElement(true, $(bindIndex + endTime));
							$(bindIndex + beginTime).parents("tr").show();
							$(bindIndex + endTime).parents("tr").show();
						}
					}
				}
			}
			if($.inArray(payType, NotNeedTimeCar) >= 0 || $.inArray(payType, NeedTimeCar) >= 0) {
				mastInputClass(false, $("bd-FMonthFee"));
				$("#bd-FMonthFee").parents("tr").hide();
			}
			// if (isUpdate) {
			// $("#addTips").hide();
			// } else {
			// $("#addTips").show();
			// }
			// });
		};

		function mustInputCheck(payType, fn) {
			var check = {
				IsCheck: true,
				ErrorMsg: {}
			}
			if($.inArray(payType, czcArrays) >= 0) { //储值车
				return check;
			}
			if($.inArray(payType, NeedAllTimeCar) >= 0) { // 时段月租车
				mastInputClass(true, $(bindIndex + periodBeginTime));
				mastInputClass(true, $(bindIndex + periodEndTime));
				mastInputClass(true, $(bindIndex + beginTime));
				mastInputClass(true, $(bindIndex + endTime));
				if($.trim($(bindIndex + periodBeginTime).val()) == "") {
					check.IsCheck = false;
					check.ErrorMsg[periodBeginTime] = "时段月租开始时间不可为空";
				}
				if($.trim($(bindIndex + periodEndTime).val()) == "") {
					check.IsCheck = false;
					check.ErrorMsg[periodEndTime] = "时段月租结束时间不可为空";
				}
				// if ($.trim($(bindIndex + beginTime).val()) == "") {
				// check.IsCheck = false;
				// check.ErrorMsg[beginTime] = "开始时间不可为空";
				// }
				// if ($.trim($(bindIndex + endTime).val()) == "") {
				// check.IsCheck = false;
				// check.ErrorMsg[endTime] = "结束时间不可为空";
				// }
			}
			if($.inArray(payType, NeedTimeCar) >= 0) { // 月租车
				mastInputClass(true, $(bindIndex + beginTime));
				mastInputClass(true, $(bindIndex + endTime));
				mastInputClass(false, $(bindIndex + periodBeginTime));
				mastInputClass(false, $(bindIndex + periodEndTime));
				$(bindIndex + periodBeginTime).val("");
				$(bindIndex + periodEndTime).val("");

				if($.trim($(bindIndex + beginTime).val()) == "") {
					check.IsCheck = false;
					check.ErrorMsg[beginTime] = "开始时间不可为空";
				}
				if($.trim($(bindIndex + endTime).val()) == "") {
					check.IsCheck = false;
					check.ErrorMsg[endTime] = "结束时间不可为空";
				}
			}
			if($.inArray(payType, NotNeedTimeCar) >= 0) { // 临时车
				$(bindIndex + periodBeginTime).val("");
				$(bindIndex + periodEndTime).val("");
				$(bindIndex + beginTime).val("");
				$(bindIndex + endTime).val("");
			}

			return check;
		}

		return {
			render: render,
			mustInputCheck: mustInputCheck
		};
	});