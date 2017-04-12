


//表单初始化模块
define('Form', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var DataSelector = require('DataSelector');
    var ClassList = require('ClassList');

    var emitter = MiniQuery.Event.create();
    var widgets = {};
    var divClass = document.getElementById('div-class');
    var divParent = document.getElementById('bd-parentID');
    var classID = $.Url.getQueryString(window.location.href, 'classID') || '';
    var itemID = $.Url.getQueryString(window.location.href, 'id') || '';
    var parentSelector;
    var first = true;


    var samples = $.String.getTemplates(divClass.innerHTML, [
        {
            name: 'select',
            begin: '<!--',
            end: '-->'
        },
        {
            name: 'options',
            begin: '#--options.begin--#',
            end: '#--options.end--#',
            outer: '{option}'
        }
    ]);


    function render() {
        var classList = ClassList.list;

        fillSelect(classList, classID);

        if (itemID != '') {
            //处理编辑逻辑
            parentSelector = DataSelector.create({
                container: divParent,
                targetType: 1,
                hasBreadcrumbs: false,
                title: '上级分类选取',
                classID: 10110,
                typeID: classID
            });

            widgets = {
                parentID: parentSelector
            };

            first = false;
        }
        else {
            //处理新增逻辑
            var select = document.getElementById('bd-classID');

            if (classID != '' && classID != 10110) {
                //处理非基础资料类别进来的新增
                select.value = classID;
                select.setAttribute('disabled', 'disabled');

                parentSelector = DataSelector.create({
                    container: divParent,
                    targetType: 1,
                    hasBreadcrumbs: false,
                    title: '上级分类选取',
                    classID: 10110,
                    typeID: classID
                });

                widgets = {
                    parentID: parentSelector
                };

            }
        }

        bindEvents();
    }



    function fillSelect(list, value) {

        divClass.innerHTML = $.String.format(samples.select, {
            'option': $.Array.keep(list, function (item, index) {
                return $.String.format(samples.options, {
                    'ID': item.number,
                    'name': item.name
                });
            }).join('')
        });


    }

    function bindEvents() {
        var select = document.getElementById('bd-classID');
        

        $(select).bind('change', function () {

            var self = this;
            

            if (self.value == '') {
                return;
            }
            else {

                if (!first) {
                    parentSelector.destroy();
                }

                parentSelector = DataSelector.create({
                    container: divParent,
                    targetType: 1,
                    hasBreadcrumbs: false,
                    title: '上级分类选取',
                    typeID: self.value,
                    classID: 10110
                });

                widgets = {
                    parentID: parentSelector
                };

                first = false;

                emitter.fire('select.change', [{
                    widgets: widgets
                }]);
            }
        });
    }

    function getWidgets() {

        return widgets;

    }

    return {
        render: render,
        getWidgets: getWidgets,
        on: emitter.on.bind(emitter)
    }


});