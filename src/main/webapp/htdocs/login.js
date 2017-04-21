//控制器
;(function ($, MiniQuery, sms) {


    var Tabs = require('Tabs');
    var Login = require('Login');
    var WarnTip = require('WarnTip');

    Tabs.init(0);
    Login.init();


    $(document).on({
        'click': function () {
            WarnTip.hide();
        },

        'keydown': function (event) {
            if (event.keyCode == 13) {
                Login.login(Tabs.current() == 0 ? 2 : 1);
            }
        }
    });

    Login.on({
        'login': function () {
            Login.login(Tabs.current() == 0 ? 2 : 1);
            // 50801-供应商用户，50802-系统用户
        }
    });

    Tabs.on({
        'change': function (index) {
            console.log(index);
        }
    });

})(jQuery, MiniQuery, SMS);