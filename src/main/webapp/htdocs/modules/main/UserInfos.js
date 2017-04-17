


/**
* 用户信息模块
*/
define('UserInfos', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');



    var panel = document.getElementById('li-user-infos');
    var user = SMS.Login.get();


    function render() {


        //批量填充
        SMS.Template.fill({
            '#span-user-name': {
                name: user.name
            },
            '#span-message-count': {
                value: user.messageCount
            },
        });


        $(panel).hover(function () {
            show();
        }, function () {
            hide();
        });

        $('#btn-change-password').on('click', function() {
            openChangePwd();
        });

        $('#btn-logout').on('click', function () {

            var btn = this;
            btn.innerHTML = '注销中...';
            $(btn).addClass('disabled');


            SMS.Login.logout(function (user, data, json) { //成功

                location.href = 'login.html';

            }, function (code, msg, json) { //失败

                reset();
                alert(msg);

            }, function () { //错误
                reset();
                alert('网络错误，注销失败，请稍候再试');
            });
        });

    }



    function show() {

        if ($(panel).hasClass('hover')) { //避免上次的隐藏动画还没结束又开始显示动画
            return;
        }

        $(panel).addClass('hover');
        $('#div-user-list').fadeIn();
    }

    function hide() {

        $('#div-user-list').fadeOut(function () {
            $(panel).removeClass('hover');
        });
    }




    return {
        render: render
    };

});




