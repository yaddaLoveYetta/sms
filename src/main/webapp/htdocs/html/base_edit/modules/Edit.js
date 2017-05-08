﻿/**
 * 单据数据管理
 *
 */
define('Edit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var FormEdit = require('FormEdit');
    var f7Selectors;
    var classId;
    var itemId = '';

    // 带表体单据控件
    var Grid = require('Grid');
    var GridBuilder = require('GridBuilder');
    var parkGrid = new Grid('bd-grid');
    var columns = ["FParkNumber"];
    var isNeedOpt = true; //Jqgrid是否可操作

    var cleanGrid = function () {
        parkGrid.clear();
        var successData = {
            grid: ""
        };
        showValidInfo(successData, null);
    };

    var parkGridConfig = {
        gridName: 'bd-grid',
        width: 500,
        height: '100%',
        fnAfterSaveCell_After: function (rowid, data) {
            $('#bd-grid').jqGrid('setCell', rowid, 'roleName', data.name);
        },
        fnAfterEditCell_Before: function (rowId, cellName, cellValue) {
            Combo.getCombo().selectByText(cellValue, false);
        }
    };

    //jqGrid初始化
    var initGrid = function (entryData, metaData) {
        parkGridConfig = GridBuilder.getConfig(metaData['formFields'][1], parkGridConfig, columns, isNeedOpt);
        parkGrid.render(parkGridConfig, entryData, metaData, 1);
        parkGrid.on('f7Selected', function (data) {
            var itemData = {
                'FPark': data[0].ID,
                'FParkID': data[0].ID,
                'FParkNumber': data[0].number,
                'FParkName': data[0].name
            };
            parkGrid.setRowData(data.row, itemData);
        });
    };
    /**
     * 表格数据填充
     * @param entryData 数据
     * @param metaData 元数据
     */
    var fillGrid = function (entryData, metaData) {

        parkGridConfig = GridBuilder.getConfig(metaData['formFields'][1], parkGridConfig, columns, isNeedOpt);
        parkGrid.render(parkGridConfig, entryData, metaData, 1);
        parkGrid.on('f7Selected', function (data) {
            var itemData = {
                'FPark': data[0].ID,
                'FParkID': data[0].ID,
                'FParkNumber': data[0].number,
                'FParkName': data[0].name
            };
            parkGrid.setRowData(data.row, itemData);
        });

    }

    function render(formClassId, itemID) {

        itemId = itemID;
        classId = formClassId;
        FormEdit.render(formClassId, itemId, initGrid, initSelectors);
    }

    function clear() {
        itemId = '';
        FormEdit.clear();
    }

    function save() {

        var errorData = {};
        var entryData = {};

        if (classId == 1001) {
            // 用户信息特殊校验逻辑
            // 供应商用户必须关联供应商
            if ((f7Selectors['type'].getData()[0].ID || '') == 'B3sMo22ZLkWApjO/oEeDOxACEAI=' && (f7Selectors['supplier'].getData()[0].ID || '') == '') {
                errorData["supplier"] = $.String.format("供应商不可为空");
            }
        }

        FormEdit.save(itemId, showValidInfo, saveSuccess, entryData, errorData);

    }

    function forbid(classID, operateType) {
        if (!itemId) {
            return;
        }
        FormEdit.forbid(classID, itemId, operateType);
    }

    function refresh(classID) {
        if (!itemId) {
            return;
        }
        //    cleanGrid();
        FormEdit.render(classID, itemId, initGrid);
    }

    function showValidInfo(successData, errorData) {

        for (var item in successData) {
            // 去掉错误提示
            var msgElement = document.getElementById('bd-' + item + '-msg');
            if ($(msgElement).hasClass('show')) {
                $(msgElement).toggleClass('show');
            }
            $(msgElement).html('');
        }
        if (errorData) {
            // 显示错误提示
            for (var item in errorData) {

                var msgElement = document.getElementById('bd-' + item + '-msg');
                if (!$(msgElement).hasClass('show')) {
                    $(msgElement).toggleClass('show');
                }
                $(msgElement).html(errorData[item]);
            }
        }
    }

    function saveSuccess(data) {
        if (itemId) {
            SMS.Tips.success('数据修改成功', 2000);
        } else {
            itemId = data['itemID'];
            SMS.Tips.success('数据新增成功', 2000);
        }
        refresh(classId, f7Selectors);
    }

    /**
     * F7控件初始化时设置个性化条件
     * @param classId 业务类别
     * @param key 标识字段的key
     */
    function initSelectors(classId, key) {

        var config = {};
        if (classId == 1001 && key == 'role') {
            // 用户角色依赖于用户类别
            config = {
                conditionF7Names: [{
                    type: "selector",
                    target: 'type',
                    filterKey: "type",
                    valueRule: {
                        'QpXq24FxxE6c3lvHMPyYCxACEAI=': 'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=',
                        'B3sMo22ZLkWApjO/oEeDOxACEAI=': 'f1sGInqJq0aUNY5MmpKM8RAEEAQ='
                    }
                }],   //级联查询条件 多个用逗号分割
            };
        }
        return config;
    }

    FormEdit.on({
        "FType.defaultFill": function (data) { //默认数据填充
            var userTypeId = data[0].ID;
            // UserTypeOpt.render(userTypeId);
        },
        'afterFill': function (classId, metaData, data) {

            if (classId == 1001) {
                // 用户编辑填充页面数据后-对于用户类别是系统用户的用户-锁定关联供应商控件不可用
                if (FormEdit.getSelectors('type').getData()[0]['ID'] == 1) {
                    FormEdit.getSelectors('supplier').lock();
                }
            }
        }
    });

    FormEdit.on({
        '1001-bd-type.DialogChange': function (data, selectors) {
            // 用户编辑时-用户类别变化-将角色及关联供应商清空
            // key 事件触发控件,data 当前编辑的控件数据，selectors所有的F7控件
            selectors['role'].clearData();
            selectors['supplier'].clearData();

            var element = '#bd-supplier';

            if (data[0].ID == 'QpXq24FxxE6c3lvHMPyYCxACEAI=') {
                // 系统用户类别时锁定关联供应商不可用
                selectors['supplier'].lock();

                if ($(element).parents("td").siblings().find(".must-mark")) {
                    $(element).parents("td").siblings().find(".must-mark").remove();
                }

            } else {
                // 供应商用户类别时关联供应商可用且必录
                selectors['supplier'].unlock();

                //如果是必填需要添加 红色 * 号
                if ($(element).parents("td").siblings().find(".must-mark").length <= 0) { //如果不存在
                    var html = $(element).parents("td").siblings().html();
                    $(element).parents("td").siblings().html("<span class=\"must-mark\">*</span>" + html);
                }
            }

        },
        'afterInitSelectors': function (selectors) {
            f7Selectors = selectors;
        }
    });

    return {
        render: render,
        clear: clear,
        save: save,
        forbid: forbid,
        refresh: refresh,
        cleanGrid: cleanGrid,
        getf7Selectors: function (name) {
            return f7Selectors[name];
        }
    };

});