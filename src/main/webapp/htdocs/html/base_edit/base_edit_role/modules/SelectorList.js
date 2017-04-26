/**
 * 填充自定义控件模块
 *
 */
define('SelectorList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var YWTC = require('YWTC');
    var RoleTypeOpt = require('RoleTypeOpt');
    var DataSelector = require('DataSelector');

    var selectors = {};

    var companyContainer = document.getElementById('bd-FCompany');
    var companyConfig = {
        targetType: 1, //跳转方案
        classID: 13001,
        hasBreadcrumbs: true,
        container: companyContainer,
        title: '物业公司',
        defaults: {
            pageSize: 8,
        }
    };
    var companySelector = DataSelector.create(companyConfig);
    selectors['FCompany'] = companySelector;


    var userTypeContainer = document.getElementById('bd-FRoleType');
    var userTypeConfig = {
        targetType: 2, //跳转方案
        typeID: 507,   //过滤条件-辅助资料类别
        pageSize: 8,
        hasBreadcrumbs: true,
        container: userTypeContainer,
        title: '角色类别'
    };
    var orderTypeSelector = DataSelector.create(userTypeConfig);
    selectors['FRoleType'] = orderTypeSelector;


    //设置 静态变量 用于联动操作
    DataSelector.DataSelectors = selectors;
    //改变事件捕获
    DataSelector.on({
        'bd-FRoleType.DialogChange': function (data) {
            companySelector.clearData();
            var roleTypeId = data[0].ID;
            RoleTypeOpt.render(roleTypeId);
        }
    });

    return selectors;
});

