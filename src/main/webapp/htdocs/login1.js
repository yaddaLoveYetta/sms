//控制器
;(function ($, MiniQuery, sms) {


    var Tabs = require('Tabs');
    var Login = require('Login1');
    var WarnTip = require('WarnTip');

    Tabs.init(1);
    Login.init();


    $(document).on({
        'click': function () {
            WarnTip.hide();
        },
        // 'B3sMo22ZLkWApjO/oEeDOxACEAI=' 业务用户 'QpXq24FxxE6c3lvHMPyYCxACEAI=' 系统用户
        'keydown': function (event) {
            if (event.keyCode == 13) {
                Login.login('QpXq24FxxE6c3lvHMPyYCxACEAI=');
            }
        }
    });

    Login.on({
        'login': function () {
            Login.login('QpXq24FxxE6c3lvHMPyYCxACEAI=');
            // 50801-供应商用户，50802-系统用户
        }
    });

    Tabs.on({
        'change': function (index) {
            console.log(index);
        }
    });

})(jQuery, MiniQuery, SMS);