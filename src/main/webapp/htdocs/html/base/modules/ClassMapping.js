﻿/**
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

    var pageMappings = {
        '1001': {
            name: '用户',
            url: 'html/base_edit/base_edit_user/index.html'
        },
        '1003': {
            name: '角色',
            url: 'html/base_edit/base_edit_role/index.html'
        },
        '1005': {
            name: '供应商',
            url: 'html/base_edit/base_edit_supplier/index.html'
        },
    }



    function getPage(classId) {
        return pageMappings[classId] ? pageMappings[classId].url || '' : null;
    }
    function getTabName(classId) {
        return pageMappings[classId] ? pageMappings[classId].name || '' : null;
    }

    return {
        getIndex: getIndex,
        getPage: getPage,
        getTabName: getTabName,
    };

});






