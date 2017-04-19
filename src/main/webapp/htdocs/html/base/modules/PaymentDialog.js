define("PaymentDialog", function (require, module, exports) {
    var $ = require('$');
    var API = SMS.require('API');

    var config = {
        title: '月租缴费',
        url: 'html/car-payment/index.html'
    }
    ///car/monthlyFeePayment
    function monthlyFeePayment(payData, fn) {

        var api = new API('car/monthlyFeePayment');
        api.post(payData);
        api.on({
            'success': function (data, json) {
                SMS.Tips.success('月租缴费成功！', 1500);
                fn && fn(data, json);
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function showDialog(data, cfig, fn) {
        if ($.isFunction(cfig)) {
            fn = cfig;
            cfig = {};
        }
        var nConfig = $.extend({}, config, cfig);
        YWTC.use('Dialog', function (Dialog) {
            var dialog = new Dialog({
                title: nConfig.title,
                url: config.url,
                width: 380,
//              height:150,
                data: data,
                button: [{
                    className: 'ywtc-cancel-btn',
                    value: '取消'
                },
                    {
                        value: '确认',
                        className: 'ywtc-submit-btn',
                        autofocus: true,
                        callback: function () {
                            this.isSubmit = true;
                            dialog.__dispatchEvent('get');
                            var dialogData = dialog.getData();
                            if (!dialogData.valid) {
                                return false;
                            }
                            console.log(dialogData);
                            //id	int	必须	71	车主ID
                            //amount	double	必须	310	月租缴费金额
                            //beginDate	date	必须	2015-11-01	月租开始时间
                            //endDate	date	必须	2015-11-30	月租结束时间
                            var paymentData = {};
                            paymentData.id = data.primaryValue;
                            paymentData.amount = dialogData.money;
                            paymentData.beginDate = dialogData.beginTime;
                            paymentData.endDate = dialogData.endTime;
                            //1.调用保存数据方法 2.保存成功刷新界面
                            //1.savaPayment();
                            monthlyFeePayment(paymentData, fn);
                            return true;
                        }
                    }
                ]
            });

            dialog.showModal();
        });
    }
    return {
        showDialog: showDialog
    }
})