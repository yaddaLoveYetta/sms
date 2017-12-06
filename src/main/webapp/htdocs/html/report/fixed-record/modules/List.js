/**
 * 工单量图表
 *
 */
define('List', function (require, module, exports) {

        var $ = require('$');
        var MiniQuery = require('MiniQuery');
        var SMS = require('SMS');

        var div = document.getElementById('body_item');

        function render(data) {
            SMS.Template.fill(div, data, function (item, index) {
                return {
                    'reqNumber':item.reqNumber,
                    'department':item.department,
                    'builder':item.builder,
                    'date':item.date,
                    'status':item.status,
                    'dispatchTime':item.dispatchTime,
                    'repairMan':item.repairMan,
                    'targetItem':item.targetItem,
                    'fixedTime':item.fixedTime,
                };
            });
        }

        return {
            render: render
        };

    }
);

