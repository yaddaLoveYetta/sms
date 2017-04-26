/**
 * 填充自定义控件模块
 *
 */
define('SelectorList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var YWTC = require('YWTC');
    var API = YWTC.require("API");
    var DataSelector = require('DataSelector');
    var PeriodCarOpt = require("PeriodCarOpt");
    var selectors = {};

    var companyContainer = document.getElementById('bd-FCompany');
    var config = {
        targetType: 1, //跳转方案
        classID: 13001,
        hasBreadcrumbs: true,
        container: companyContainer,
        title: '物业公司',
        defaults: {
            pageSize: 8
        }
    };
    var companySelector = DataSelector.create(config);
    selectors['FCompany'] = companySelector;

    var parkContainer = document.getElementById('bd-FPark');
    var config = {
        targetType: 1, //跳转方案
        classID: 13002,
        hasBreadcrumbs: true,
        container: parkContainer,
        conditionF7Names: [{ SelectorName: "FCompany", FillterKey: "FCompany" }],   //级联查询条件 多个用逗号分割
        title: '车场',
        defaults: {
            pageSize: 8
        }
    };
    var parkSelector = DataSelector.create(config);
    selectors['FPark'] = parkSelector;


    var carTypeContainer = document.getElementById('bd-FCarType');
    var carTypeConfig = {
        targetType: 1, //跳转方案
        classID: 13007,   //过滤条件-辅助资料类别
        pageSize: 8,
        hasBreadcrumbs: true,
        conditionF7Names: [{ SelectorName: "FCompany", FillterKey: "FCompany" }, { SelectorName: "FPark", FillterKey: "FPark" }],   //级联查询条件 多个用逗号分割
        container: carTypeContainer,
        title: '车辆类别'
    };
    var carTypeSelector = DataSelector.create(carTypeConfig);
    selectors['FCarType'] = carTypeSelector;

	var payTypeContainer = document.getElementById('bd-FPayType');
	var payTypeConfig = {
		targetType : 2, //跳转方案
		typeID: 513,   //过滤条件-辅助资料类别
		pageSize : 8,
		hasBreadcrumbs : true,
		container: payTypeContainer,
		//supperConditions: [{ key: "id", logicOperator: ">", value: 51304 }],
		title : '车辆付费类型'
	};
	var payTypeSelector = DataSelector.create(payTypeConfig);
	selectors['FPayType'] = payTypeSelector;

    //设置 静态变量 用于联动操作
    DataSelector.DataSelectors = selectors;

    //改变事件捕获
    DataSelector.on({
        'bd-FCompany.DialogChange': function (data) {
            parkSelector.clearData();
            carTypeSelector.clearData();
        }, 'bd-FPark.DialogChange': function (data) {
            carTypeSelector.clearData();
        },
        'bd-FCarType.DialogChange': function (data) { //车辆类型改变
            PeriodCarOpt.render(data[0].ID);
        },
         'bd-FPayType.DialogChange': function (data) { //车辆类型改变
            PeriodCarOpt.render(data[0].ID);
        }
    });

    return selectors;
});

