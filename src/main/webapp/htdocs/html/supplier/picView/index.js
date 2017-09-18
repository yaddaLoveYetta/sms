/**
 * Created by yadda on 2017/9/18.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');

    var picUrl;
    var dialog = Iframe.getDialog();

    if (dialog) {
        var data = dialog.getData() || {};
        picUrl = data.picUrl;
    }

    if (!picUrl) {
        return;
    }

    $('#img').attr('src', picUrl);

})();