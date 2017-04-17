


//功能按钮模块
define('Operations', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var ButtonList = SMS.require('ButtonList');
    var List = require('List');

    var emitter = MiniQuery.Event.create();

    var items = List.getItems(emitter);

    var bl = new ButtonList({
        container: '#div-button-list',
        fields: {
            text: 'text',
            child: 'items',
            callback: 'click',
        },

        autoClose: true,

        items: items
    });

    bl.on('click', function (item, index) {

    });

    function render() {
        bl.render();
    }

    return {
        render: render,
        on: emitter.on.bind(emitter)
    }

});