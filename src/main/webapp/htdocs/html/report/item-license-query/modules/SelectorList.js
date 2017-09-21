/**
 * 填充自定义控件模块
 *
 */
define('SelectorList', function(require, module, exports) {

	var $ = require('$');
	var MiniQuery = require('MiniQuery');
	var SMS = require('SMS');

	var DataSelector = require('DataSelector');

	var selectors = {};

	var idTypeContainer = document.getElementById('idType');
	var config = {
		targetType : 1, //跳转方案
		classID : 1023,
		hasBreadcrumbs : true,
		container : idTypeContainer,
		title : '证件类别',
		defaults : {
			pageSize : 8
		}
	};
	var idTypeSelector = DataSelector.create(config);
	selectors['idType'] = idTypeSelector;
	
	var supplierContainer = document.getElementById('supplier');
	var config = {
		targetType : 1, //跳转方案
		classID : 1005,
		hasBreadcrumbs : true,
		container: supplierContainer,
		title : '供应商',
		defaults : {
			pageSize : 8
		}
	};
	var supplierSelector = DataSelector.create(config);
	selectors['supplier'] = supplierSelector;

    //设置 静态变量 用于联动操作
	DataSelector.DataSelectors = selectors;

	return selectors;
});

