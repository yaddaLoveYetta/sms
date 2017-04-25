; (function () {

    var $ = require('$');
    var SMS = require('SMS');

    var Container = require('Container');
    var List = require('List');
    var Pager = require('Pager');
    var Tree = require('Tree');
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var user = SMS.Login.get();
    var classID = 13005;
    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }
    var conditions = [];
    //默认配置
    var defaults = {
        pageSize: 10,
        pageNo: 1,
        multiSelect: false
    };
    $(document).bind('keypress', function (event) {
        if (event.keyCode == 13) {
        	refresh();
        }
        
    });
    $(txtSimpleSearch).bind('keypress', function (event) {
        if (event.keyCode == 13) {          
            refresh();
            return false;
        }
    });
    $("#optSave").bind("click", function () {
        var user = List.getSelectedItem();
        if (user.number === 'manager') {
            return;
        }
        Tree.savePermit(user, function () {
            Tree.render(user.FRoleID, user.FRoleType, function () {
            });
        });
    });

    $("#optCancel").bind("click", function () {
        var role = List.getSelectedItem();
        if (!role) {
            return;
        }
        Tree.render(role.FRoleID, role.FRoleType, function () {
        });
    });

    Container.render();

    List.on({
        'row.click': function (role) {
            if (!role) {
                return;
            }
            Tree.render(role.FRoleID, role.FRoleType, function () {
            });
        }
    });

    function refresh() {
    	  var keyworld = $(txtSimpleSearch).val();
          conditions = [];
          if ($.trim(keyworld) !== "") {
              var condition = {
                  'andOr': 'and',
                  'leftParenTheses': '((',
                  'fieldKey': 'FName',
                  'logicOperator': 'like',
                  'value': $(txtSimpleSearch).val(),
                  'rightParenTheses': ')'
              };
              conditions.push(condition);

              condition = {
                  'andOr': 'OR',
                  'leftParenTheses': '(',
                  'fieldKey': 'FNumber',
                  'logicOperator': 'like',
                  'value': $(txtSimpleSearch).val(),
                  'rightParenTheses': '))'
              };

              conditions.push(condition);
          }
        List.render({
            pageNo: 1,
            pageSize: defaults.pageSize,
            classID: classID,
            conditions: conditions,
            multiSelect: defaults.multiSelect
        }, function (total, pageSize) {
            var topData = List.getSelectedItem();
            if (!topData) {
                return;
            }
            Tree.render(topData.FRoleID, topData.FRoleType, function () {
                List.selectTr(0);
            });
            Pager.render({
                current: 1,
                total: total,
                size: pageSize,
                container: '#div-pager',
                change: function (no) {
                    List.render({
                        pageNo: no,
                        pageSize: defaults.pageSize,
                        classID: classID,
                        conditions: conditions,
                        multiSelect: defaults.multiSelect
                    });
                }
            });
        });
    };

    refresh();
})();
