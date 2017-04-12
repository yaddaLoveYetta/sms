
/**
* 
*/
define('DownloadCriterias', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var emitter = MiniQuery.Event.create();
    var listCheck = [];
    var listRadio = [];

    function bindEvents() {
        $('#div-box').delegate('#li-date', 'click', function () {
            $(this).toggleClass('selected');
            $('#li-orderno').toggleClass('selected');

            $('#div-orderno').addClass('div-hidden');
            $('#div-date').removeClass('div-hidden');

            $('[id^=spark-]').hide();
            $('#warnings').hide();

        }).delegate('#li-orderno', 'click', function () {
            $(this).toggleClass('selected');
            $('#li-date').toggleClass('selected');

            $('#div-date').addClass('div-hidden');
            $('#div-orderno').removeClass('div-hidden');

            $('[id^=spark-]').hide();
            $('#warnings').hide();

        }).delegate('[data-check-index]', 'click', function () {
            var li = this;
            $(li).toggleClass('selected');
            var index = +li.getAttribute('data-check-index');
            listCheck[index].checked = !listCheck[index].checked;

        }).delegate('[data-radio-index]', 'click', function () {
            var li = this;
            $(li).parent('li').siblings('li').children('span').removeClass('selected');

            $(li).toggleClass('selected');
            var index = +li.getAttribute('data-radio-index');

            $.Array.keep(listRadio, function (item, i) {
                if (i == index) {
                    listRadio[i].checked = !listRadio[i].checked;
                }
                else {
                    listRadio[i].checked = false;
                }
            });


        }).delegate('#btn-download', 'click', function () {
            $('[id^=spark-]').hide();
            $('#warnings').hide();
            emitter.fire('manual-download', getTask);
        });
    }

    bindEvents();

    function getTask() {
        if ($('#li-orderno').hasClass('selected')) {

            var shopids = '';
            $.Array.keep(listRadio, function (item) {

                if (item.checked) {
                    shopids += item.shopID + ',';
                }
            });

            var ordernos = $('#txtOrdernos').val();

            if (shopids.length > 0) {
                shopids = shopids.substr(0, shopids.length - 1);
            }
            else {
                $('#spark-shop-1').show();
                isValidate = false;
            }

            if (!ordernos || ordernos == '') {
                $('#spark-order').show();
                isValidate = false;
            }

            if (!isValidate) {
                $('#warnings').show();
                return;
            }

            return {
                tasktype: 12,
                shopid: shopids,
                taskparas: ordernos
            }
        } else {
            var shopids = '';
            $.Array.keep(listCheck, function (item) {

                if (item.checked) {
                    shopids += item.shopID + ',';
                }

            });

            var date = $('#startTime').val() + ',' + $('#endTime').val();

            var isValidate = true;
            if (shopids.length > 0) {
                shopids = shopids.substr(0, shopids.length - 1);
            }
            else {
                $('#spark-shop-0').show();
                isValidate = false;
            }

            if ($('#startTime').val() == '' || $('#endTime').val() == '') {
                $('#spark-time').show();
                isValidate = false;
            }

            if (!isValidate) {
                $('#warnings').show();
                return;
            }

            return {
                tasktype: 11,
                shopid: shopids,
                taskparas: date
            }
        }
    }

    function validate() {
        var isValidate = true;
        if ($('#startTime').val() == '' || $('#endTime').val() == '') {
            $('#spark-time').show();
            isValidate = false;
        }

        if (shopids.length == 0) {
            $('#spark-shop-0').show();
            isValidate = false;
        }
        return isValidate;
    }

    function setList(data) {
        listCheck = data;
        listRadio = $.Array.keep(data, function (item, index) {

            return $.Object.extendDeeply({}, item);
        });

    }

    return {
        setList: setList,
        getTask: getTask,
        on: function (name, fn) {
            emitter.on(name, fn);
        }
    }
});