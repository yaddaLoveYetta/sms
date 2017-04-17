
/**
* 填充自定义控件模块
* 
*/
define('SelectorList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    var DataSelector = require('DataSelector');

    var selectors = {};

    var groupContainer = document.getElementById('bd-parentID');
    var config = {
        targetType: 1, //跳转方案
        typeID: 10107,   //过滤条件
        classID: 10110,
        hasBreadcrumbs: true,
        container: groupContainer,
        title: '所属类别'
    };
    var groupSelector = DataSelector.create(config);
    selectors['parentID'] = groupSelector;


    var spGroupContainer = document.getElementById('bd-spGroupID');
    var spConfig = {
        targetType: 1, //跳转方案
        //typeID: 10107,   //过滤条件
        classID: 10108,
        hasBreadcrumbs: true,
        container: spGroupContainer,
        title: '货位组'
    };
    var spGroupSelector = DataSelector.create(spConfig);
    selectors['spGroupID'] = spGroupSelector;

    return selectors;
});






