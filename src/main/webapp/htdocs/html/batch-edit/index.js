


; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var Iframe = KERP.require('Iframe');
    var ProgressBar = require('ProgressBar');


    var API = require('API');
    var logistics = require('Logistics');
    var operation = require('Operation');


    var express = {
        name: '',
        ecValue: -1,
    };


    var ids;
    //处理以弹窗形式传来的数据
    var dialog = Iframe.getDialog();
    if (dialog) {
        var data = dialog.getData();
        ids = data.ids;
        dialog.on({

        });
    }

    function render() {
        logistics.render(express);
        operation.render();
    }

    render();

    $('li[data-type-index]').on('click', function () {
        var self = this;
        $('li[data-type-index]').removeClass('selected');
        $(self).addClass('selected');

        var index = self.getAttribute('data-type-index');

        $('[id^=div-tab-]').addClass('hidden');
        var div = document.getElementById('div-tab-' + index);
        $(div).removeClass('hidden');
    });

    operation.on({

        'submit': function () {
            var args = {
                ids: ids,
                editType: 'Editlogistics',
                Editlogistics: { "logisticsCompany": "2" }
            };


            API.batchOrderEdit(args, function (data) {

                KERP.Dialog.use(function (Dialog) {
                    var dialog = new Dialog({
                        title: '消息',
                        content: '正在处理，请稍等...',
                    });

                    dialog.showModal();
                });
                //KERP.use('Dialog', function (Dialog) {
                //    var dialog = new Dialog({
                //        id: 'action-result',
                //        title: '操作结果',
                //        url: './html/action-result/index.html', // ./ 表示相对于网站根目录
                //        width: 900,
                //        height: 500,

                //        //需要传递的数据
                //        data: {
                //            success: data.success,
                //            fail: data.fail,
                //            colTitle: data.colTitle,
                //            detail: data.detail,
                //        },
                //    });

                //    //绑定各种事件
                //    dialog.on({
                //    });

                //    dialog.showModal();

                //});

            },
            function (msg) {
                console.log(msg);
                KERP.Tips.warn('接口出错', 2000);

            }
            );

        },

    });

})();