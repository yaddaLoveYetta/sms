/**
 * 工单量图表
 *
 */
define('List', function (require, module, exports) {

        var $ = require('$');
        var MiniQuery = require('MiniQuery');
        var SMS = require('SMS');

        var scrollSpeed = 40;//值越大，滚动的越慢
        var div = document.getElementById('body_item');

        var list = document.getElementById('list');

        var div2 = $("<div class='table_pane'></div>")[0];

        function ScrollMarquee() {


            if (div2.offsetTop - list.scrollTop <= 0) {
                list.scrollTop -= div2.offsetHeight;
            }
            else {
                list.scrollTop++;
            }

        }

        function render(data) {
            SMS.Template.fill(div, data, function (item, index) {
                return {
                    'reqNumber': item.reqNumber,
                    'department': item.department,
                    'builder': item.builder,
                    'date': item.date,
                    'status': item.status,
                    'dispatchTime': item.dispatchTime,
                    'repairMan': item.repairMan,
                    'targetItem': item.targetItem,
                    'fixedTime': item.fixedTime,
                };
            });

            div2.innerHTML = div.innerHTML;

            $('#list').append($(div2))

            var timer = setInterval(ScrollMarquee, scrollSpeed);

            list.onmouseover = function () {
                clearInterval(timer);
            };

            list.onmouseout = function () {
                timer = setInterval(ScrollMarquee, scrollSpeed);
            };
        }

        return {
            render: render
        };

    }
);

