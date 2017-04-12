


; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var Iframe = KERP.require('Iframe');


    var API = require('API');



    var ids;
    var labelIndex = 0;
    var labelValue = [0, 0, 0, 0, 0];

    //处理以弹窗形式传来的数据
    var dialog = Iframe.getDialog();
    if (dialog) {
        var data = dialog.getData();
        ids = data.ids;
        dialog.on({

        });
    }

    render();

    events();

    function render() {
        console.log('render insert label');
    }

    function events() {
        console.log('bindEvents on insert label');


        $('span[data-index]').on('click', function () {
            var self = this;
            $('span[data-index]').not(self).removeClass('flag-selected');
            $(self).toggleClass('flag-selected');

            labelIndex = self.getAttribute('data-index');

            for (var i = 0; i < 5; i++) {

                labelValue[i] = 0;
                if (i == labelIndex - 1) {
                    labelValue[i] = labelValue[i] ^ 1;

                }

            }
        });

        $('#btn-submit').on('click', function () {
            console.log('click on insert label');
            if (!labelValue[labelIndex - 1] || labelValue[labelIndex - 1] == 0) {
                return;
            }

            var args = {
                ids: ids,
                editType: 'EditFlag',
                EditFlag: { "flag": labelIndex }
            }

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

            });
        });

        $('#btn-cancel').on('click', function () {
            console.log('click on cancel label');

            dialog.remove();
        });
    }


})();