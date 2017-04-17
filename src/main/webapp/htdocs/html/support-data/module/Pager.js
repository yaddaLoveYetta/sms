



define('Pager', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var div = document.getElementById('div-pager-simple');

    function render(config) {

        config = $.Object.extend({}, config, {
            container: '#div-pager-simple',
            current: 1,
            hideIfLessThen: 2
        });

        SMS.SimplePager.create(config);
    }


    return {
        render: render
    };
});