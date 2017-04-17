//控制器
;(function ($, MiniQuery, sms) {


    var Tabs = require('Tabs');
    var Login = require('Login');
    var WarnTip = require('WarnTip');

    var type = 0;

    Tabs.init(type);
    Login.init();


    $(document).on({
        'click': function () {
            WarnTip.hide();
        },

        'keydown': function (event) {
            if (event.keyCode == 13) {
                Login.login(Tabs.current == 0 ? 50801 : 50802);
            }
        }
    });

    Login.on({
        'login': function () {
            Login.login(type + 50801);
            // 50801-供应商用户，50802-系统用户
        }
    });

    Tabs.on({
        'change': function (index) {
            type = index;
        }
    });

})(jQuery, MiniQuery, SMS);