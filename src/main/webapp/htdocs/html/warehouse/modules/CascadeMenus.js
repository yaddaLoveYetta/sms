

/**
* 级联菜单模块
* 
*/
define('CascadeMenus', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var Tree = require('CascadeMenus/Tree');


    var div = document.getElementById('div-menus');
    var samples = require('CascadeMenus/Samples')(div.innerHTML);



    var list = [];
    var currentIndexes = [];
    var currentId = -1;

    var hasBind = false;
    var hasShown = false;

    var emitter = MiniQuery.Event.create();


    var tree = require('CascadeMenus/Data');
    var nodes = Tree.toArray(tree);


    function render(indexes, id, left) {

        if (indexes.length > 0 && indexes.join('') == currentIndexes.join('')) {
            show();
            return;
        }
        
        id = id || 0;
        currentId = id;
        var node = nodes[id];

        var groups = Tree.getGroups(node, indexes);
        var topNos = Tree.getTopNos(indexes);

        list = groups;
        currentIndexes = indexes;


        div.innerHTML = $.Array.keep(groups, function (group, no) {

            return $.String.format(samples.group, {
                'index': no,
                'topN': topNos[no],

                'items': $.Array.keep(group, function (item, index) {

                    return $.String.format(samples.item, {
                        'index': index,
                        'name': item.name,
                        'actived': item.actived ? 'actived' : '',
                    });

                }).join('')
            });

        }).join('');

        if (typeof left == 'number') {
            div.style.left = left + 'px';
        }
        

        bindEvents();
        show();
    }


    function bindEvents() {

        if (hasBind) {
            return;
        }

        $(document).on('click', function (event) {
            if (hasShown) {
                hide();
            }
        });


        $(div).delegate('li[data-index]', 'mouseover', function (event) {

            //隐藏有个动画延迟，为避免在隐藏的动画过程中又 mousever 而导致的重新显示
            if (!hasShown) { 
                return;
            }

            var li = this;
            var ol = li.parentNode;

            var no = +ol.getAttribute('data-index');    //组号
            var index = +li.getAttribute('data-index'); //项号

            var group = list[no];
            var item = group[index];

            if (item.isLeaf) { //叶子结点
                return;
            }

            var indexes = currentIndexes.slice(0, no);
            indexes[no] = index;

            render(indexes, currentId);


        });


        $(div).delegate('li[data-index]', 'click', function (event) {

            hide();

            var li = this;
            var ol = li.parentNode;

            var no = +ol.getAttribute('data-index');    //组号
            var index = +li.getAttribute('data-index'); //项号

            var group = list[no];
            var item = group[index];


            emitter.fire('item.click', [item, no, index]);


        });

        
        hasBind = true;
    }


    function show() {

        $(div).fadeIn(function () {
            hasShown = true; //先用动画延迟显示后，再指示已显示
        });

    }

    function hide() {

        hasShown = false; //相当于个信号，先锁定，再动画延迟隐藏
        $(div).fadeOut(function () {
            emitter.fire('hide');
        });
    }


    return {
        render: render,
        on: emitter.on.bind(emitter),
    };

});






    