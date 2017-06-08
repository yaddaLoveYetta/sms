/**
 * Created by yadda on 2017/6/8.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Iframe = SMS.require('Iframe');
    var API = SMS.require('API');

    var Upload = require('Upload');

    var classId;
    var itemId;

    var dialog = Iframe.getDialog();

    if (dialog) {

        var data = dialog.getData();

        classId = data.classId;
        itemId = data.id;

    }


    Upload.render();

})();