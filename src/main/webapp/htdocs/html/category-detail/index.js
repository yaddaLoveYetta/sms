


; (function () {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var Operations = require('Operations');
    var Form = require('Form');
    var Edit = require('Edit');

    var classID = $.Url.getQueryString(window.location.href, 'classID') || '';

    render();


    function render() {

        Operations.render();
        Form.render();

        Edit.render({
            classID: 10110,
            widgets: Form.getWidgets()
        });

        bindEvents();

    }

    Form.on({
        'select.change': function (data) {
            Edit.render({
                classID: 10110,
                widgets: data.widgets
            });
        }
    });

    function bindEvents() {

        Operations.on({
            'optSave': function () {
                Edit.save();
            },
            'optNew': function () {
                Edit.add();
            },
            'optRefresh': function () {
                Edit.render({
                    classID: 10110,
                    widgets: Form.getWidgets()
                });
            }
        });

    }

    $('#db-parentID').undelegate('[data-roel="label"]');


})();