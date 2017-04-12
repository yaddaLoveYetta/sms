

//编辑新增处理模块
define('Edit', function (require, module, exports) {
    var $ = require('$');
    var MiniQeury = require('MiniQuery');
    var KERP = require('KERP');

    var Tips = KERP.require('Tips');
    var FormEdit = require('FormEdit');

    var itemID = $.Url.getQueryString(window.location.href, 'id') || '';


    function render(config) {

        FormEdit.render(config.classID, itemID, config.widgets);

    }

    function save() {

        FormEdit.save(itemID, showInfo, success);

    }

    function add() {

        itemID = '';
        FormEdit.clear();

    }


    function showInfo(success, error) {

        $('[data-role="error-msg"]').each(function (index, item) {
            item.innerText = '';
        });

        if (error) {
            $.Object.each(error, function (key, value) {
                var container = document.getElementById('bd-' + key + '-msg');
                container.innerText = value;
            });
        }
    }


    function success(data) {
        if (itemID) {
            Tips.success('修改成功！', 1500);
        }
        else {
            itemID = data.itemID;
            Tips.success('新增成功！', 1500);
        }
    }


    return {
        render: render,
        add: add,
        save: save
    }

});