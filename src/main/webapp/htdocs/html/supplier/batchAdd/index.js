/**
 * Created by yadda on 2017/8/18.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var MessageBox = SMS.require('MessageBox');
    var bl = require('ButtonList');
    var Edit = require('Edit');
    var DatetimePicker = require('DatetimePicker');
    var itemId = MiniQuery.Url.getQueryString(window.location.href, 'id');
    var formClassId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var defaultValue;

    //检查登录

    var blConfig = {
        items: [{
            text: '新增',
            name: 'optNew',
            icon: '../../../css/main/img/add.png',
        }, {
            text: '保存',
            name: 'optSave',
            icon: '../../../css/main/img/save.png',
        }],
    };
    var ButtonList = bl.create(blConfig);

    if (!SMS.Login.check(true)) {
        return;
    }

    var dialog = Iframe.getDialog();

    if (dialog) {
        var data = dialog.getData() || {};
        defaultValue = data.defaultValue;
    }

    // 基础资料类别ID
    // 支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'optNew': function () {
            Edit.clear();
        },
        'optSave': function () {
            Edit.save();
        },
        'optRefresh': function () {
            Edit.refresh(formClassId);
        },
        'optDisable': function () {
            Edit.forbid(formClassId, 1);
        },
        'optUndisable': function () {
            Edit.forbid(formClassId, 0);
        }
    });

    Edit.on({
        'addSuccess': function () {

            Iframe.raise({
                'eventName': 'addSuccess',
                'data': Iframe.getInfos(),
            });

        },
        'editSuccess': function () {

            Iframe.raise({
                'eventName': 'editSuccess',
                'data': Iframe.getInfos(),
            });

        },
        'addNew': function () {

            Iframe.raise({
                'eventName': 'addNew',
                'data': Iframe.getInfos(),
            });

        }
    });

    DatetimePicker.render();
    ButtonList.render();
    Edit.render(formClassId, itemId, defaultValue);

})();
