/**
 * ClassMapping 模块
 *
 */
define('ClassMapping', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    function getIndex(classId) {
        var classMappings =
            {
                '10107': {first: 1, second: 3},
                '10103': {first: 1, second: 4},
                '10106': {first: 1, second: 5},
                '10110': {first: 1, second: 6},
            };

        return classMappings[classId];
    }

    var editPageMappings = {
        '1010': {
            name: '用户',
            url: 'html/base_edit/base_edit_user/index.html'
        },
        '1003': {
            name: '角色',
            url: 'html/base_edit/base_edit_role/index.html'
        },
        '1005': {
            name: '供应商',
            url: 'html/base_edit/base_edit_carGroup/index.html'
        },
    }

    function getEditPage(classId) {
        return editPageMappings[classId].url || '';
    }

    function getTabName(classId) {
        return editPageMappings[classId].name || '';
    }

    return {
        getIndex: getIndex,
        getEditPage: getEditPage,
        getTabName: getTabName,
    };

});






