


define('Sort', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var wrapper = document.getElementById('sort-panel');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "group",
            begin: "<!--",
            end: "-->",
            outer: "{group}"
        },
        {
            name: "sortPanel",
            begin: "#--sortPanel.begin--#",
            end: "#--sortPanel.end--#",
            outer: "{sortPanel}"
        },
        {
            name: "add-sorting-options",
            begin: "#--add-sorting-options.begin--#",
            end: "#--add-sorting-options.end--#"
        }
    ]);

    var list = [];

    var modelList = [];
    var sortingOptionsList = [];

    var emitter = MiniQuery.Event.create();

    function toggleDire(dataObj, docObj) {
        if (dataObj.dire == 'asc') {
            dataObj.dire = '';
            $(docObj).removeClass('down').removeClass('up').removeClass('selected');
        }
        else if (dataObj.dire == 'desc') {
            dataObj.dire = 'asc';
            $(docObj).removeClass('down').addClass('up').addClass('selected');
        }
        else {
            dataObj.dire = 'desc';
            $(docObj).removeClass('up').addClass('down').addClass('selected');
        }
    }

    $(wrapper).delegate('[data-field]', 'click', function () {

        var btn = this;
        var field = btn.getAttribute('data-field');

        var index = $.Array.findIndex(list, function (item) {
            return item.fieldName == field;
        });

        if (index > -1) {
            toggleDire(list[index], btn);
        }

        emitter.fire('sort', []);

    });


    var required = false;
    var isSortingOptionsHide = false;

    $(wrapper).delegate('#add-icon', 'click', function () {

        $('#add-sorting').toggleClass('hidden');

        if (!$('#add-sorting').hasClass('hidden')) {

            document.getElementById('add-sorting-options').innerHTML = $.map(sortingOptionsList || [], function (item, index) {

                return $.String.format(samples['add-sorting-options'], {
                    'name': item.name,
                    'index': index,
                    'active-class': item.shown ? 'item-selected' : ''

                });

            }).join('');
        }

        if (!required) {


            $('#add-sort').on('mouseover', function () {
                isSortingOptionsHide = false;
            });

            $('#add-sort').on('mouseout', function () {
                isSortingOptionsHide = true;
            });

            $(document).on('click', function () {

                if (isSortingOptionsHide) {
                    $('#add-sorting').addClass('hidden');
                }
            });

            required = true;
        }
    });

    $(wrapper).delegate('[data-option-index]', 'click', function () {

        var btn = this;
        $(btn).toggleClass('item-selected');

    });

    function renderOptions() {
        
    }

    function loadSortingOptions(data) {
        sortingOptionsList = data;
    }

    function loadModelList(data) {
        modelList = data;
    }

    function findDisplayByField(fieldName) {

        return $.Array.findItem(modelList, function (model, index) {
            return model.fieldName == fieldName;
        }).displayName;

    }

    function render(data) {

        list = $.Array.keep(data, function (item, index) {
            return {
                displayName: item.name,
                fieldName: item.value,
                dire: item.dire
            }


        });

        wrapper.innerHTML = $.String.format(samples['group'], {
            'sortPanel': $.Array.keep(list, function (item, index) {

                return $.String.format(samples['sortPanel'], {
                    fieldName: item.fieldName,
                    displayName: item.displayName
                });

            }).join('')
        })
    }

    return {
        render: render,
        loadModelList: loadModelList,
        loadSortingOptions: loadSortingOptions,
        getListString: function () {
            return $.Array.map(list, function (item, index) {
                if (item.dire)
                    return item.fieldName + ' ' + item.dire;
                else
                    return null;
            }).join(',');
        },
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }


});