/// <reference path="jquery.js" />
/*
======================================================================================
==  Product         : jQuery Spinner Control
==  Company         : MindzGroup Technologies
==  Developer       : Anant Anand Gupta
==  Version         : 1.0.2
==  Date            : 9 May 2011
==  Description     : Convert a text input in to a Spinner Control.
==  Required Files  : jquery.spinnercontrol.css
======================================================================================
*/

(function ($) {
    $.fn.SpinnerControl = function (options) {

        // set default options
        var opt = $.extend({
            type: 'range',
            typedata: '',
            defaultVal: 0,
            width: '30px',
            backColor: '#fff',
            looping: false
        }, options);

        var otypedata;

        if (options != null && options.typedata != null) {
            otypedata = $.extend({
                min: -9999,
                max: 9999,
                interval: 1,
                decimalplaces: 0
            }, options.typedata);
        }
        else {
            otypedata = $.extend({
                min: -9999,
                max: 9999,
                interval: 1,
                decimalplaces: 0
            });
        }
        opt.typedata = otypedata;

        var inputControl = this;

        // validate if the object is a input of text type.
        if (!inputControl.is(':text'))
            return inputControl;

        if (inputControl.hasClass('SpinnerControl')) {
            return inputControl;
        }
        else {
            inputControl.addClass('SpinnerControl');
        }

        // create the Spinner Control body.
        var strContainerDiv = '';
        strContainerDiv += '<div data-type="spinner"  class="content">';
        strContainerDiv += '  <ul class="count">';
        strContainerDiv += '    <li data-type="minus" class="reduce"></li>';
        strContainerDiv += '    <li class="count-input">';
        strContainerDiv += '        <input data-type="spinner-input" type="text"  />';
        strContainerDiv += '    </li>';
        strContainerDiv += '    <li data-type="add" class="add"></li>';
        strContainerDiv += '  </ul>';
        strContainerDiv += '</div>';

        // add the above created control to page
        var objContainerDiv = $(strContainerDiv).insertAfter(inputControl);
        
        //$(inputControl).siblings('[data-type=spinner]').find('[data-type=spinner-input]').on('focus', function () {
        //    eval(this).select();
        //});

        $(inputControl).siblings('[data-type=spinner]').find('[data-type=spinner-input]').on('click', function () {
            this.select();
        });

        // hide the input control and place within the Spinner Control body
        inputControl.css('display', 'none');

        switch (opt.type) {
            case 'range':
                // set default value;
                if (opt.defaultVal < opt.typedata.min ) {
                    opt.defaultVal = opt.typedata.min;
                    $(objContainerDiv).children('ul').addClass('reduce-disabled');
                }
                if (opt.defaultVal > opt.typedata.max) {
                    opt.defaultVal = opt.typedata.max;
                    $(objContainerDiv).children('ul').addClass('add-disabled');
                }
                if (opt.defaultVal % opt.typedata.interval > 0) {
                    opt.defaultVal = parseInt((opt.defaultVal / opt.typedata.interval).toFixed(0)) * opt.typedata.interval;
                }
                inputControl.val(opt.defaultVal.toFixed(opt.typedata.decimalplaces));
                ($("[data-type=spinner-input]", objContainerDiv)).val(opt.defaultVal.toFixed(opt.typedata.decimalplaces));
                

                if ((opt.typedata.max - opt.typedata.min) > opt.typedata.interval) {
                    // attach events;
                    $("[data-type=add]", objContainerDiv).click(function () {

                        var selectedValue = +$('[data-type=spinner-input]', objContainerDiv).val();
                        if ((selectedValue + opt.typedata.interval) <= opt.typedata.max || opt.looping) {
                            if ((selectedValue + opt.typedata.interval) > opt.typedata.max) {
                                selectedValue = opt.typedata.min - opt.typedata.interval;
                            }
                            var valueData = (selectedValue + opt.typedata.interval).toFixed(opt.typedata.decimalplaces);
                            selectedValue += opt.typedata.interval;
                            ($("[data-type=spinner-input]", objContainerDiv)).val(valueData);
                            inputControl.val(valueData);

                            if (valueData < opt.typedata.max) {
                                $(this).closest('ul').removeClass('add-disabled');
                                $(this).closest('ul').removeClass('reduce-disabled');
                            }
                            else {
                                $(this).closest('ul').addClass('add-disabled');
                            }
                        }
                        else {
                            $(this).closest('ul').addClass('add-disabled');
                        }
                        return false;
                    });

                    $('[data-type=minus]', objContainerDiv).click(function () {

                        var selectedValue = +$('[data-type=spinner-input]', objContainerDiv).val();
                        if ((selectedValue - opt.typedata.interval) >= opt.typedata.min || opt.looping) {
                            if ((selectedValue - opt.typedata.interval) < opt.typedata.min) {
                                selectedValue = opt.typedata.max + opt.typedata.interval;
                            }
                            var valueData = (selectedValue - opt.typedata.interval).toFixed(opt.typedata.decimalplaces);
                            selectedValue -= opt.typedata.interval;
                            ($('[data-type=spinner-input]', objContainerDiv)).val(valueData);
                            inputControl.val(valueData);

                            if (valueData > opt.typedata.min) {
                                $(this).closest('ul').removeClass('reduce-disabled');
                                $(this).closest('ul').removeClass('add-disabled');
                            }
                            else {
                                $(this).closest('ul').addClass('reduce-disabled');
                            }

                        }
                        else {
                            $(this).closest('ul').addClass('reduce-disabled');
                        }
                        return false;
                    });
                }

                break;
        };

        // return the selected input control for the chainability
        return inputControl;
    };
})(jQuery);