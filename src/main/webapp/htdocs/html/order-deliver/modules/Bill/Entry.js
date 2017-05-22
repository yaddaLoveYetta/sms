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
    };

    //jqGrid初始化
    function gridRender(template, data) {

        if (!template.formFields["1"]) {
            return;
        }

        billTemplate = template;

        SMS.use('Grid', function (Grid) {

            billGrid = new Grid('bd-grid');

            /**
             entryId
             parent
             seq
             orderId
             orderNumber
             orderSeq
             material
             specification
             lot
             dyBatchNum
             code
             price
             unit
             qty
             dyProDate
             dyManufacturer
             registrationNo
             amount
             effectiveDate
             */
                //要展示的列
            var showKeys = [];

            //可编辑的列
            var editKeys = ['material', 'lot', 'dyBatchNum', 'code', 'dyProDate', 'dyManufacturer', 'registrationNo', 'effectiveDate'];

            // gridConfig = GridBuilder.getConfig(template.formFields["1"], gridConfig, showKeys, editKeys);
            defaults = GridBuilder.getConfig({
                'fields': template.formFields["1"],
                'defaults': defaults,
                'showKeys': showKeys,
                'editKeys': editKeys,
                'operator': false,
            });


            billGrid.render(defaults, data, template, 1);

            billGrid.on('f7Selected', function (data) {

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

        var errorData = gridData["error"] || {};
        var addData = gridData["add"] || [];

        var entryData = {
            1: addData
        };

        return {
            errorData: errorData,
            entryData: entryData,
        };
    }

    function render(template, data) {
        gridRender(template, data);
    }


    function showValidInfo(errorData) {

        if (errorData) {

            var errors = '';
            // 显示错误提示
            for (var item in errorData) {
                errors = errors + '<br/>第' + item + '行[' + errorData[item].join('-') + ']是必录项';
            }

            var msgElement = document.getElementById('bd-grid-msg');
            if (!$(msgElement).hasClass('show')) {
                $(msgElement).toggleClass('show');
            }
            $(msgElement).html(errors);
        }
    }

    return {
        render: render,
        getData: getData,
        showValidInfo:showValidInfo,
    };
});