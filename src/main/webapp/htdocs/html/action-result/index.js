

(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var Iframe = KERP.require('Iframe');

    //获取从dialog传入iframe的数据
    var dialog = Iframe.getDialog();
    var iframeData = dialog.getData();

    var wrapper = document.getElementById('div-body');
    var emitter = MiniQuery.Event.create();

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "group",
            begin: "<!--",
            end: "-->",
            outer: "{group}"
        },
        {
            name: "results",
            begin: "#--results.begin--#",
            end: "#--results.end--#",
            outer: "{results}"
        }
    ]);

    var colTitles = iframeData.colTitle.split(',');
    var detail = $.Array.keep(iframeData.detail, function (item) {
        return $.extent({}, item, { 'colTitle': colTitles.length > 0 ? colTitles[0] : '' });

    });


    wrapper.innerHTML = $.String.format(samples['group'], {
        'success': iframeData.success,
        'fail': iframeData.fail,
        'results': iframeData.detail.length > 0 ? $.Array.keep(detail, function (item, index) {

            return $.String.format(samples['results'], {
                key: item.key,
                msg: item.msg,
                colTitle: item.colTitle,
            });

        }).join('') : ''
    });


    $('#div-body').on('#btn-submit', 'click', function () {
        emitter.fire('submit');

    }).on('#btn-cancel', 'click', function () {
        dialog.remove();
    });


    return {
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }
});