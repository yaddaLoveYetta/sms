﻿/**
 * 填充自定义控件模块
 *
 */
define('SelectorList', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Edit = require("Edit");

    var DataSelector = require('DataSelector');
    var UserTypeOpt = require("UserTypeOpt");

    var selectors = {};

    var container = document.getElementById('bd-FCompany');
    var config = {
        targetType: 1, //跳转方案
        classID: 13001,
        hasBreadcrumbs: true,
        container: container,
        title: '物业公司',
        defaults: {
            pageSize: 8
        }
    };
    var companySelector = DataSelector.create(config);
    selectors['FCompany'] = companySelector;

    var roleContainer = document.getElementById('bd-FRoleID');
    var config = {
        targetType: 1, //跳转方案
        classID: 13005,
        hasBreadcrumbs: true,
        container: roleContainer,
        conditionF7Names: [{ SelectorName: "FCompany", FillterKey: "FCompany" }, { SelectorName: "FType", FillterKey: "FRoleType", ValueRule: { 50801: 50701, 50802: 50702 } }],   //级联查询条件 多个用逗号分割
        title: '角色',
        defaults: {
            pageSize: 8
        }
    };
    var roleSelector = DataSelector.create(config);
    selectors['FRoleID'] = roleSelector;


    var userTypeContainer = document.getElementById('bd-FType');
    var userTypeConfig = {
        targetType: 2, //跳转方案
        typeID: 508,   //过滤条件-辅助资料类别
        pageSize: 8,
        hasBreadcrumbs: true,
        container: userTypeContainer,
        title: '用户类别'
    };
    var orderTypeSelector = DataSelector.create(userTypeConfig);
    selectors['FType'] = orderTypeSelector;


    //conditionF7Names: [{ SelectorName: "FCompany", FillterKey: "FCompany" }],   //级联查询条件 多个用逗号分割


    //设置 静态变量 用于联动操作
    DataSelector.DataSelectors = selectors;
    //改变事件捕获
    DataSelector.on({
        'bd-FType.DialogChange': function (data) {
            companySelector.clearData();
            roleSelector.clearData();
            var typeId = data[0].ID;
            UserTypeOpt.render(typeId);
        }, 'bd-FCompany.DialogChange': function (data) {
            $("#FCompanyId").val(data[0].ID);
            roleSelector.clearData();
            Edit.cleanGrid();
        }
    });
    return selectors;
});

