


define('Navigation', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var emitter = MiniQuery.Event.create();
    
    function render(index) {

        if (index == currentIndex) {
            show();
            return;
        }

        reset();

        currentIndex = index;

        show();
        bindEvents();
    }

    return {
        render: render,
        on: emitter.on.bind(emitter),
    }
});