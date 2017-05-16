/**
 * 编辑处理逻辑模块
 */
define('Bill/Entry/GridBuilder', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    function getColModel(field, isNeedOpt) {
        var model = {};
        model.name = field.key;
        model.label = field.name;
        model.width = field.showWidth;
        model.title = true;
        model.editable = isNeedOpt;// ((field.enableMask & 1) == 1 || (field.enableMask & 2) == 2);
        model.hidden = ((field.display & 1) != 1);
        model.tabIndex = field.index;

        if (field.ctrlType == 6) {
            //if (field.FLookUpType == 1) {
            //
            model.name = field.key + '_DspName';
            model.edittype = 'custom';
            function element(value, options) {
                var el = $('.' + field.key + 'Auto')[0];
                return el;
            };
            function value(elem, operation, value) {
                if (operation === 'get') {
                    return "";
                } else if (operation === 'set') {
                    $('input', elem).val(value);
                }
            };

            function handle() {
                $('#initCombo').append($('.' + field.key + 'Auto').val(''));
            };

            var triggerClass = 'ui-icon-ellipsis';

            model.editoptions = {
                custom_element: element,
                custom_value: value,
                handle: handle,
                trigger: triggerClass,
            };

            model.formatter = function (val, opt, row) {
                //				if (row[field.FKey + '_DspName']) {
                //					return row[field.FKey + '_DspName'];
                //				} else

                if (val) {
                    return val;
                } else {
                    return '';
                }
            };

            model.data = field;
        }

        return model;
    }

    function getConfig(fields, gridConfig, names, isNeedOpt) {

        var cNames = [];
        var cModel = [];
        if (isNeedOpt) {
            cModel = [{
                name: 'bos_modify',
                label: 'bos_modify',
                hidden: true
            }, {
                name: 'operate',
                label: ' ',
                width: 40,
                fixed: true,
                formatter: function (val, opt, row) {
                    var html_con = '<div class="operating" data-id="' + opt.rowId + '"><span class="ui-icon ui-icon-plus" title="新增行"></span><span class="ui-icon ui-icon-trash" title="删除行"></span></div>';
                    return html_con;
                },
                align: "center",
                editable: false, // 不能新增删除行
                hidden: true
            }];
        }

        //要展示的字段
/*        var keys = ['entryId', 'parent', 'seq', 'material', 'unit', 'qty', 'price',
            'confirmDate', 'deliveryDate', 'discountRate', 'taxRate', 'taxPrice',
            'actualTaxPrice', 'discountAmount', 'tax', 'localAmount', 'confirmQty'];*/
        var keys = ['entryId', 'parent', 'seq', 'material', 'unit', 'qty', 'price',
            'confirmDate', 'deliveryDate', 'localAmount', 'confirmQty'];

        for (var key in keys) {

            var field = fields[keys[key]];

            if (!field) {
                continue;
            }

            isNeedOpt = false;
            if ($.Array.contains(['localAmount', 'confirmQty'], field.key)) {
                isNeedOpt = true;
            }
            var model = getColModel(field, isNeedOpt);
            cModel.push(model);
        }

        cModel = sortModels(cModel);

        for (var m in cModel) {
            cNames.push(cModel[m].label);
        }

        gridConfig.colNames = cNames;
        gridConfig.colModel = cModel;

        gridConfig.fnAfterEditCell = function (rowid, cellname, value, iRow, iCol) {

            var rowdata = $("#" + gridConfig.gridName).getRowData(rowid);
            rowdata[cellname] = value;
            console.log(rowdata);
            $("#" + iRow + "_" + cellname).val(value);
            $('#initCombo').data('selectedRow', rowid);
            $('#initCombo').data('selectedVal' + rowid, rowdata);
            gridConfig.fnAfterEditCell_Before && gridConfig.fnAfterEditCell_Before(rowid, cellname, value);
            $("#" + iRow + "_" + name_dsp, "#" + gridConfig.gridName).val(value);

        };
        gridConfig.fnAfterSaveCell = function (rowid, cellname, val, iRow, iCol) {
            var gridData = $('#initCombo').data('selectedVal' + rowid);
            $("#" + gridConfig.gridName).jqGrid('setRowData', rowid, gridData);
        };

        gridConfig.fnLoadComplete = function (data) {
            //var rows = data['rows'];
            //var len = rows.length;
            //for (var i = 0; i < len; i++) {
            //    var tempId = i + 1, row = rows[i];
            //    if ($.isEmptyObject(rows[i])) {
            //        break;
            //    };
            //    $('#' + tempId).data('rowInfo', row);
            //};
        };

        return gridConfig;
    }

    function sortModels(models) {

        return $.Array.sort(models, function (models1, models2) {
            return models1.tabIndex > models2.tabIndex;
        });

        /*        for (var i = 0; i < models.length; i++) {
         for (var j = i + 1; j < models.length; j++) {
         if (models[i].tabIndex > models[j].tabIndex) {
         var tmp = models[i];
         models[i] = models[j];
         models[j] = tmp;
         }
         }
         }
         return models;*/
    }

    function getImagePath(path) {
        var images = ['../../../css/bd/goods/img/u199.jpg', '../../../css/bd/goods/img/u353.jpg', '../../../css/bd/goods/img/u376.jpg', '../../../css/bd/goods/img/u468.jpg'];

        if ($.Array.contains(images, path)) {
            return path;
        }

        return $.Array.randomItem(images);
    }

    return {
        getConfig: getConfig,

    };

});
