

/**
* 表体数据模块
* 
*/
define('Grid', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var FormEdit = require('FormEdit');

    var userCombo;
    var curRow, curCol;
    var deleteRows = [];
    var addRows = [];

    var THISPAGE = {
        newId: 2
    };

    function render(classID) {
        FormEdit.searchItems(classID, 'userNumber', loadComboData);
    }

    function loadComboData(data) {

        if (!data || !data['items']) return;

        initCombo(data['items']);

    }

    function fill(entryData, metaData) {
        if (!entryData) {
            return;
        }

        var entryKeys = Object.keys(entryData);
        var entryCount = entryKeys.length;

        if (entryCount == 1) {
            initGrid(entryData[1]);
        }
        else {
            initGrid();
        }

    }

    function initGrid(data) {
        if (!data) {
            data = [];
        }
        if (data.length < 1) {
            data.push({ id: 'num_1' });
        }
        else {
            THISPAGE.newId = data.length + 1;
        };

        jQuery('#bd-grid').jqGrid(
        {
            data: data,
            datatype: 'local',
            colNames: ['', '用户', '助记码', '职员姓名'],
            colModel: [
                        { name: 'operate', label: ' ', width: 40, fixed: true, formatter: bdTableOper, align: "center" },
                        {
                            name: 'user',
                            label: '用户',
                            width: 100,
                            title: false,
                            classes: 'ui-ellipsis',
                            formatter: userFmt,
                            editable: true,
                            edittype: 'custom',
                            editoptions: {
                                custom_element: userElem,
                                custom_value: userValue,
                                handle: userHandle,
                                trigger: 'ui-icon-ellipsis'
                            }
                        },
                        { name: 'userNumber_NmbName', width: 50, editable: false, title: false },
                        { name: 'userName', width: 50, editable: false, title: false },
            ],
            width: 450,
            cmTemplate: { sortable: false },
            rownumbers: true,//添加左侧行号
            cellEdit: true,
            altRows: true,
            shrinkToFit: true,
            forceFit: true,
            onselectrow: false,//???
            cellsubmit: 'clientArray',//???
            loadonce: true,

            afterEditCell: function (rowid, cellname, value, iRow, iCol) {
                curRow = iRow;
                curCol = iCol;

                if (cellname === 'user') {
                    $("#" + iRow + "_user", "#bd-grid").val(value);
                    userCombo.selectByText(value);
                    //this.curID = rowid;
                };
            },

            afterSaveCell: function (rowid, name, val, iRow, iCol) {
                if (name == 'user') {
                    var userInfo = $('#' + rowid).data('userInfo');
                    if (userInfo) {
                        $("#bd-grid").jqGrid('setRowData', rowid,
                            {
                                userNumber: userInfo.number,
                                userNumber_NmbName: userInfo.helpCode,
                                userName: userInfo.name,
                                ID: userInfo.userID,
                            });
                    }
                };
            },

            loadComplete: function (data) {
                console.log(data);
            },
        });

        //添加行
        $('.grid-wrap').on('click', '.ui-icon-plus', function (e) {
            var rowId = $(this).parent().data('id');
            var newId = $('#bd-grid tbody tr').length;
            var datarow = { id: 'num_' + THISPAGE.newId };
            var su = $("#bd-grid").jqGrid('addRowData', 'num_' + THISPAGE.newId, datarow, 'after', rowId);
            if (su) {
                $(this).parents('td').removeAttr('class');
                $(this).parents('tr').removeClass('selected-row ui-state-hover');
                $("#bd-grid").jqGrid('resetSelection');
                THISPAGE.newId++;
            }
        });
        //删除行
        $('.grid-wrap').on('click', '.ui-icon-trash', function (e) {
            if ($('#bd-grid tbody tr').length === 2) {
                return false;
            }
            var rowId = $(this).parent().data('id');
            var su = $("#bd-grid").jqGrid('delRowData', rowId);
            if (su) {
                deleteRow.push(rowId);
            };
        });

        //取消分录编辑状态
        $(document).bind('click.cancel', function (e) {
            if (!$(e.target).closest(".ui-jqgrid-bdiv").length > 0 && curRow !== null && curCol !== null) {
                $("#bd-grid").jqGrid("saveCell", curRow, curCol);
                curRow = null;
                curCol = null;
            };
        });

        $('.grid-wrap').on('click', '.ui-icon-ellipsis', function (e) {
            alert('test');
        });

        function userFmt(val, opt, row) {
            if (val) {
                return val;
            } else if (row.userNumber) {
                return row.userNumber_NmbName + ' ' + row.userName;
            } else {
                return '&#160;';
            }

        };

        function userElem(value, options) {
            var el = $('.userAuto')[0];
            return el;
        };

        function userValue(elem, operation, value) {
            if (operation === 'get') {
                //console.log($('.goodsAuto').getCombo().getValue())
                if ($('.userAuto').getCombo().getValue() !== '') {
                    return $(elem).val();
                } else {
                    var parentTr = $(elem).parents('tr');
                    parentTr.removeData('userInfo');
                    return '';
                }
            } else if (operation === 'set') {
                $('input', elem).val(value);
            }
        };

        function userHandle() {
            $('#initCombo').append($('.userAuto').val('').unbind("focus.once"));
        };
    }

    function getGridData() {
        var gridData = [];
        var ids = $('#bd-grid').jqGrid('getDataIDs');

        cancelGridEdit();
        for (var i = 0, len = ids.length; i < len; i++) {
            var id = ids[i], itemData;
            var row = $('#bd-grid').jqGrid('getRowData', id);
            if (row.name == '') {
                continue;	//跳过无效分录
            };
            itemData = {
                id: row.ID,
                number: row.userNumber,
                name: row.userName
            };
            //if (oper == 'edit') {
            //    itemData.id = $.inArray(Number(id), linksIds) != -1 ? id : 0;
            //} else {
            itemData.id = 0;
            //}
            gridData.push(itemData);
        };

        return gridData;
    }

    function cancelGridEdit() {
        if (curRow && curCol) {
            $('#bd-grid').jqGrid("saveCell", curRow, curCol);
            curRow = null;
            curCol = null;
        }
    }

    function bdTableOper(val, opt, row) {
        var html_con = '<div class="operating" data-id="' + opt.rowId + '"><span class="ui-icon ui-icon-plus" title="新增行"></span><span class="ui-icon ui-icon-trash" title="删除行"></span></div>';
        return html_con;
    };

    function initCombo(data) {
        userCombo = $('.userAuto').combo({
            //data: parent.SYSTEM.goodsInfo/*'/basedata/inventory.do?action=list'*/,
            data: data,
            /*			ajaxOptions: {
                            formatData: function(data){
                                return data.data.items;
                            }
                        },*/
            formatText: function (data) {
                return (data.helpCode ? data.helpCode : '')
                    + '  ' + data.name;
            },
            value: 'id',
            defaultSelected: -1,
            editable: true,
            //extraListHtml: '<a href="javascript:void(0);" id="quickAddGoods" class="quick-add-link"><i class="ui-icon-add"></i>新增商品</a>',
            maxListWidth: 500,
            cache: false,
            forceSelection: false,
            maxFilter: 20,
            trigger: false,
            listHeight: 182,
            listWrapCls: 'ui-droplist-wrap',
            callback: {
                onChange: function (data) {
                    var parentTr = this.input.parents('tr');
                    if (data) {
                        parentTr.data('userInfo', data);
                    }
                },
                onListClick: function () {

                }
            },
            queryDelay: 0,
            inputCls: 'edit_subject',
            wrapCls: 'edit_subject_wrap',
            focusCls: '',
            disabledCls: '',
            activeCls: ''
        }).getCombo();
    }

    return {
        render: render,
        fill: fill,
        getGridData: getGridData,
    }

});






