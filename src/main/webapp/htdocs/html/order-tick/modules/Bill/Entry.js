/**
 * 单据体模块
 * Created by yadda on 2017/5/13.
 */
define('Bill/Entry', function (require, module, exports) {

    var SMS = require('SMS');
    var GridBuilder = require('/GridBuilder');// 真实路径是'Bill/Entry/GridBuilder'

    var billGrid;
    var isNeedOpt = true; // 订单详情不可编辑

    var billTemplate = {};

    var cleanGrid = function () {
        billGrid.clear();
    };

    var defaults = {
        gridName: 'bd-grid',
        width: $(window).width() - 5,
        height: 'auto',
/*        fnAfterSaveCell_After: function (rowid, data) {
            $('#bd-grid').jqGrid('setCell', rowid, 'roleName', data.name);
        },
        fnAfterEditCell_Before: function (rowId, cellName, cellValue) {
            $.Combo.getCombo().selectByText(cellValue, false);
        }*/
    };

    //jqGrid初始化
    function gridRender(template, data) {

        if (!template.formFields["1"]) {
            return;
        }

        billTemplate = template;

        SMS.use('Grid', function (Grid) {

            billGrid = new Grid('bd-grid');

            //要展示的列
            var showKeys = ['entryId', 'parent', 'seq', 'material', 'unit', 'qty', 'price',
                'confirmDate', 'deliveryDate', 'localAmount', 'confirmQty'];

            //可编辑的列
            var editKeys = ['confirmQty', 'confirmDate'];

            // gridConfig = GridBuilder.getConfig(template.formFields["1"], gridConfig, showKeys, editKeys);
            /* defaults = GridBuilder.getConfig({
             'fields': template.formFields["1"],
             'defaults': defaults,
             'showKeys': showKeys,
             'editKeys': editKeys,
             'operator': false,
             });*/

            defaults = GridBuilder.getConfig({
                'fields': template.formFields["1"],
                'defaults': defaults,
                'operator': true,
            });

            billGrid.render(defaults, data, template, 1);

            billGrid.on('f7Selected', function (data) {

/*                data.container = container;
                data.row = rowNumb;
                data.col = colNumb;
                data.colModels = colModels;*/

                //rowid,colname, data, class, properties
                billGrid.setCell(data.row, data.col, data[0].name);

                var idModel = billGrid.getColProp(data.field.key); // 真实的key-保存的内码

                if(idModel){
                    billGrid.setCell(data.row, idModel.name, data[0].ID);
                }

                /*                var itemData = {
                 'FPark': data[0].ID,
                 'FParkID': data[0].ID,
                 'FParkNumber': data[0].number,
                 'FParkName': data[0].name
                 };
                 billGrid.setRowData(data.row, itemData);*/
            });

        });
    }

    function getData() {

        var entry = [];
        /*
         'data':{
         FEntryID:0, 新增可不传
         FParkID:1,
         FParkName:'ade',
         FParkNumber:'001'
         },
         'flag':'1' 0删除, 1新增，2修改
         */
        var gridData = billGrid.getGridDatas(1); // 获取第一个表体数据

        var entryTemplate = billTemplate.formFields["1"]

        //新增数据
        $.Array.each(gridData["add"], function (item, index) {
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

        return entryData;
    }

    function render(template, data) {
        gridRender(template, data);
    }

    return {
        render: render,
        getData: getData,
    };
});