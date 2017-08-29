/**
 * 编辑处理逻辑模块
 */
define('GridBuilder', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    function getColModel() {
        var model = {};
        model.name = 'material';
        model.label = '物料';
        model.width = 80;
        model.title = true;
        model.editable = true;

        model.hidden = false;
        model.tabIndex = 1;

        model.edittype = 'custom';
        function element(value, options) {
            var el = $('.' + 'material' + 'Auto')[0];
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
            $('#initCombo').append($('.' + 'material' + 'Auto').val(''));
        };


        var triggerClass = 'ui-icon-ellipsis';

        model.editoptions = {
            custom_element: element,
            custom_value: value,
            handle: handle,
            trigger: triggerClass,
        };

        model.formatter = function (val, opt, row) {
            if (val) {
                return val;
            } else {
                return '';
            }
        };

        model.data = '';

        return model;
    }

    function getConfig(gridConfig) {
        var cNames = [];
        var cModel = [];
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
        }];

        var keyModel = {
            name: 'material',
            label: '物料',
            tabIndex: 1,
            hidden: true
        };
        cModel.push(keyModel);


        var keyNmbModel = {
            name: '物料',
            label: 'material',
            tabIndex: 2,
            hidden: true
        };
        cModel.push(keyNmbModel);


        var model = getColModel();
        cModel.push(model);


        cModel = sortModels(cModel);

        for (var m in cModel) {
            cNames.push(cModel[m].label);
        }

        gridConfig.colNames = cNames;
        gridConfig.colModel = cModel;


        return gridConfig;
    }

    function sortModels(models) {
        for (var i = 0; i < models.length; i++) {
            for (var j = i + 1; j < models.length; j++) {
                if (models[i].tabIndex > models[j].tabIndex) {
                    var tmp = models[i];
                    models[i] = models[j];
                    models[j] = tmp;
                }
            }
        }

        return models;
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
