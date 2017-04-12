

; (function () {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var DataSelector = require('DataSelector');
    var Operations = require('Operations');
    var Edit = require('Edit');

    var divDept = document.getElementById('bd-parentID');

    var formClassID = 10103;

    Operations.render();

    var deptSelector = DataSelector.create({
        container: divDept,
        targetType: 1,
        hasBreadcrumbs: false,
        title: '上级部门选取',
        classID: 10103,
    });

    Edit.render({
        classID: formClassID,
        widgets: {
            parentID: deptSelector
        }
    });

    Operations.on({
        'optSave': function () {
            Edit.save();
        },
        'optNew': function () {
            Edit.add();
        }
    });

})();