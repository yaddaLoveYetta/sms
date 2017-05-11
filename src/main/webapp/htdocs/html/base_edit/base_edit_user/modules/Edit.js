/**
 * 表头数据模块
 *
 */
define('Edit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var FormEdit = require('FormEdit');
    var UserTypeOpt = require('UserTypeOpt');
    var f7Selectors;
    var baseClassID;
    var itemId = '';

    console.log(Grid)
    var GridBuilder = require('GridBuilder');
    var parkGrid = new Grid('bd-grid');
    var columns = ["FParkNumber"];
    var isNeedOpt = true; //Jqgrid是否可操作

    var Grid;

    SMS.use('Grid', function (Grid) {
        Grid = new Grid('bd-grid');
    });

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


    function render(formClassID, itemID, selectors) {
        f7Selectors = selectors;
        itemId = itemID;
        baseClassID = formClassID;
        FormEdit.render(formClassID, itemId, selectors, initGrid);
    }

    function clear() {
        itemId = '';
        FormEdit.clear();
    }

    function save() {
        var errorData = {};
        showValidInfo(null, errorData);
        var userTypeId = f7Selectors["FType"].getData()[0].ID;
        if (userTypeId == 50802) {
            var companyId = f7Selectors["FCompany"].getData()[0].ID;
            if ($.trim(companyId) == "") {
                errorData["FCompany"] = $.String.format("物业公司不可为空");
                //showValidInfo(null, errorData);
                //return false;
            }
        }

        var entry = [];
        var parkIDs = [];
        var parkIDExisted = false;
        /*
         'data':{
         FEntryID:0, 新增可不传
         FParkID:1,
         FParkName:'ade',
         FParkNumber:'001'
         },
         'flag':'1' 0删除, 1新增，2修改
         */
        var gridData = parkGrid.getGridDatas();
        var errorData = {};
        //新增数据
        $.Array.each(gridData["add"], function (item, index) {
            if (!item.FPark || item.FPark == '') {
                return;
            }
            var adData = {
                data: {
                    FPark: item.FPark,
                    FParkName: item.FParkName,
                    FParkNumber: item.FParkNumber
                },
                flag: '1'
            };
            entry.push(adData);
            if (!$.Array.contains(parkIDs, item.FPark)) {
                parkIDs.push(item.FPark);
            } else {
                parkIDExisted = true;
            }
        });
        //修改数据
        $.Array.each(gridData["update"], function (item, index) {
            var upData = {
                data: {
                    FEntryID: item.FEntryID,
                    FPark: item.FPark,
                    FParkName: item.FParkName,
                    FParkNumber: item.FParkNumber
                },
                flag: '2'
            };
            entry.push(upData);
            if (!$.Array.contains(parkIDs, item.FPark)) {
                parkIDs.push(item.FPark);
            } else {
                parkIDExisted = true;
            }
        });
        //删除数据
        $.Array.each(gridData["delete"], function (item, index) {
            var delData = {
                data: {
                    FEntryID: item.FEntryID,
                    FPark: item.FPark,
                    FParkName: item.FParkName,
                    FParkNumber: item.FParkNumber
                },
                flag: '0'
            };
            entry.push(delData);
        });

        var entryData = {
            1: entry
        };
        var errorData = {};
        if (parkIDExisted) {
            errorData["grid"] = "存在相同车场";
            //showValidInfo(null, errorData);
            //return;
        }

        FormEdit.save(itemId, showValidInfo, saveSuccess, entryData, errorData);

    }

    function forbid(classID, operateType) {
        if (!itemId) {
            return;
        }
        FormEdit.forbid(classID, itemId, operateType);
    }

    function refresh(classID, selectors) {
        if (!itemId) {
            return;
        }
        cleanGrid();
        FormEdit.render(classID, itemId, selectors, initGrid);
    }

    function showValidInfo(successData, errorData) {
        for (var item in successData) {

            var msgElement = document.getElementById('bd-' + item + '-msg');
            if ($(msgElement).hasClass('show')) {
                $(msgElement).toggleClass('show');
            }
            $(msgElement).html('');
        }
        if (errorData) {
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
        refresh(baseClassID, f7Selectors);
    }

    FormEdit.on({
        "FType.defaultFill": function (data) { //默认数据填充
            var userTypeId = data[0].ID;
            UserTypeOpt.render(userTypeId);
        },
        'afterFill': function (metaData, data) {
            UserTypeOpt.render(data.FType);

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