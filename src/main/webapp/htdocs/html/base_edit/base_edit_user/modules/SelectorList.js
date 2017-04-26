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

    var container = document.getElementById('bd-type');

    var config = {
        targetType: 1, //跳转方案
        classID: 1002,
        hasBreadcrumbs: true,
        container: container,
        title: '用户类别',
        defaults: {
            pageSize: 8
        }
    };
    var typeSelector = DataSelector.create(config);

    selectors['type'] = typeSelector;

    var roleContainer = document.getElementById('bd-role');
    var config = {
        targetType: 1, //跳转方案
        classID: 1003,
        hasBreadcrumbs: true,
        container: roleContainer,
        conditionF7Names: [{SelectorName: "FCompany", FillterKey: "FCompany"}],   //级联查询条件 多个用逗号分割
        title: '角色',
        defaults: {
            pageSize: 8
        }
    };
    var roleSelector = DataSelector.create(config);
    selectors['role'] = roleSelector;


    var supplierContainer = document.getElementById('bd-supplier');
    var config = {
        targetType: 1, //跳转方案
        classID: 1005,
        hasBreadcrumbs: true,
        container: supplierContainer,
        title: '供应商',
        defaults: {
            pageSize: 8
        }
    };
    var supplierSelector = DataSelector.create(config);
    selectors['supplier'] = supplierSelector;

    //conditionF7Names: [{ SelectorName: "FCompany", FillterKey: "FCompany" }],   //级联查询条件 多个用逗号分割


    //设置 静态变量 用于联动操作
    DataSelector.DataSelectors = selectors;
    //改变事件捕获
    DataSelector.on({
        'bd-type.DialogChange': function (data) {
            roleSelector.clearData();
            supplierSelector.clearData();
            var typeId = data[0].ID;
            UserTypeOpt.render(typeId);
        },
        'bd-FCompany.DialogChange': function (data) {
            $("#FCompanyId").val(data[0].ID);
            roleSelector.clearData();
            Edit.cleanGrid();
        }
    });
    return selectors;
});

