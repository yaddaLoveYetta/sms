/**
 * 单据体模块
 * Created by yadda on 2017/5/13.
 */
define('Bill/Entry', function (require, module, exports) {

    var SMS = require('SMS');
    var GridBuilder = require('/GridBuilder');// 真实路径是'Bill/Entry/GridBuilder'

    var Grid;
    var isNeedOpt = true;
    var cleanGrid = function () {
        Grid.clear();
    };

    var gridConfig = {
        gridName: 'bd-grid',
        width: 'auto',
        height: 'auto',
        fnAfterSaveCell_After: function (rowid, data) {
            $('#bd-grid').jqGrid('setCell', rowid, 'roleName', data.name);
        },
        fnAfterEditCell_Before: function (rowId, cellName, cellValue) {
            Combo.getCombo().selectByText(cellValue, false);
        }
    };

    //jqGrid初始化
    var initGrid = function (entryData, metaData) {
        gridConfig = GridBuilder.getConfig(metaData['formFields'][1], gridConfig, columns, isNeedOpt);
        parkGrid.render(gridConfig, entryData, metaData, 1);
        parkGrid.on('f7Selected', function (data) {
            var itemData = {
                'FPark': data[0].ID,
                'FParkID': data[0].ID,
                'FParkNumber': data[0].number,
                'FParkName': data[0].name
            };
            Grid.setRowData(data.row, itemData);
        });
    };


    function gridRender(template, data) {

        SMS.use('Grid', function (Grid) {

            Grid = new Grid('bd-grid');

            gridConfig = GridBuilder.getConfig(template.formFields["1"], gridConfig, '', isNeedOpt);
            Grid.render(gridConfig, data, template, 1);

            Grid.on('f7Selected', function (data) {
                var itemData = {
                    'FPark': data[0].ID,
                    'FParkID': data[0].ID,
                    'FParkNumber': data[0].number,
                    'FParkName': data[0].name
                };
                parkGrid.setRowData(data.row, itemData);
            });

        });
    }

    function render(template, data) {
        gridRender(template, data);
    }

    return {
        render: render,
    };
});