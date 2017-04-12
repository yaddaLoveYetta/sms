
;
(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    var DownloadCriteriasData = require('DownloadCriteriasData');
    //var Path = require('Path');
    var ShopData = require('ShopData');
    var Shop = require('Shop');
    var DownloadCriterias = require('DownloadCriterias');
    var ModuleAPI = require('ModuleAPI');
    var DatetimePicker = require('DatetimePicker');


    DatetimePicker.render();


    DownloadCriteriasData.loadPath(function (data) {
        //Path.init(data);
    });

    ShopData.load(function (data) {
        Shop.render(data);
        DownloadCriterias.setList(data);
    });

    DownloadCriterias.on({
        'manual-download': function () {
            var args = DownloadCriterias.getTask();
            if (!args) {
                return;
            }
            ModuleAPI.addTask(args, function (data) {
                KERP.Dialog.use(function (Dialog) {
                    var dialog = new Dialog({
                        title: '消息',
                        content: '正在同步，请稍等...',
                    });

                    dialog.showModal();

                });
            });
        }
    });
})();