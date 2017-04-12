

/**
* 级联路径模块
* 
*/
define('CascadePath', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    
    var emitter = MiniQuery.Event.create();

    var ol = document.getElementById('ol-path');


    var samples = $.String.getTemplates(ol.innerHTML, [
        { //这个节点是辅助用的
            begin: '<!--',
            end: '-->',
        },
        {
            name: 'item',
            begin: '#--item.begin--#',
            end: '#--item.end--#',
            outer: '{items}',
            fn: function (s) {
                return s.replace(/\n/g, '').replace(/\s{2,}/g, ' ');
            },

        },
        {
            name: 'last',
            begin: '#--last.begin--#',
            end: '#--last.end--#',
            outer: '{last}',
            fn: function (s) {
                return s.replace(/\n/g, '').replace(/\s{2,}/g, ' ');
            },
        },

    ]);


    var list = [];
    var hasBind = false;
    var lastIndex = -1;


    function render(data) {

        list = data;
        lastIndex = list.length - 1;

        ol.innerHTML = $.Array.keep(list, function (item, index) {

            var sample = item.isLeaf ? samples['last'] : samples['item'];

            return $.String.format(sample, {
                'index': index,
                'name': item.name,
            });

        }).join('');


        bindEvents();
        
    }

    function reset() {
        $(ol).find('li[data-index]>span').removeClass('on');
    }


    function bindEvents() {

        if (hasBind) {
            return;
        }

        $(ol).delegate('li[data-index]>span', 'click', function (event) {

            var span = this;
            var li = span.parentNode;

            var index = +li.getAttribute('data-index');
            var item = list[index];

            if (item.isLeaf) {
                return;
            }
            
            var $span = $(span);
            if ($span.hasClass('on')) {
                $span.removeClass('on');
                return;
            }

            $span.addClass('on');

            var pos = $(li).position();

            emitter.fire('dropdown', [item, index, pos.left]);

        });

        $(ol).delegate('li[data-index]>a', 'click', function (event) {

            var a = this;
            var li = a.parentNode;

            var index = +li.getAttribute('data-index');
            var item = list[index];

            if (item.isLeaf) {
                return;
            }

            var items = [];

            do {
                items.push(item);
                item = item.parent;
            } while (item);

            items.reverse();

            render(items);

            emitter.fire('item.click', [item, index]);

        });
        
        hasBind = true;
    }


    return {
        render: render,
        reset: reset,
        on: emitter.on.bind(emitter),

    };

});






    