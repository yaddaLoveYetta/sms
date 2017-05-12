/**
 * Created by yadda on 2017/5/12.
 */

;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var MessageBox = SMS.require('MessageBox');
    var Iframe = SMS.require('Iframe');

    var Form = require('Form');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var itemId = MiniQuery.Url.getQueryString(window.location.href, 'id');

    var conditions = {};


    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    Form.render(classId, itemId);

    alert('done');
})();