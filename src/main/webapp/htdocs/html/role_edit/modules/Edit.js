
/**
* 表头数据模块
* 
*/
define('Edit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var FormEdit = require('FormEdit');
    
    var curRow, curCol;
    var deleteRow = [];
    var userCombo;

    var THISPAGE = {
        newId: 2
    };
    
    var itemId = '';

    function render(formClassID, itemID, fn) {

        itemId = itemID;

        FormEdit.render(formClassID, itemId, null, fn);
        //FormEdit.searchItems(formClassID, 'userNumber', loadComboData);

    }

    function clear() {
        itemId = '';
        FormEdit.clear();
    }

    function save() {
        FormEdit.save(itemId, showValidInfo, saveSuccess);
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
        //FormEdit.render(classID, itemId, selectors)
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
            alert('修改数据成功');
        }
        else {
            itemId = data['itemID'];
            alert('新增数据成功');
        }
    }

    return {
        render: render,
        clear: clear,
        save: save,
        forbid: forbid,
        refresh: refresh,
    }

});


