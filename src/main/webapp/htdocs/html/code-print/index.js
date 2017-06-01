/**
 * Created by yadda on 2017/6/1.
 */
;(function () {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var ButtonList = require('ButtonList');
    var Iframe = SMS.require('Iframe');

    var code = [];// 条形码字符内容

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    var dialog = Iframe.getDialog();

    if (dialog) {

        var data = dialog.getData();

        code = data.code;

    } else {
        return;
    }

    // 支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'optPrint': function () {
            alert('optPrint');
        },
        'optRefresh': function () {
            Edit.refresh(formClassId);
        }
    });


})();