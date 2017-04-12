

define('Edit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var FormEdit = require('FormEdit');

    var optNew = document.getElementById('optNew');
    var optAddType = document.getElementById('optAddType');
    var optSave = document.getElementById('optSave');
    var optDisable = document.getElementById('optDisable');
    var optPrint = document.getElementById('optPrint');
    var optExport = document.getElementById('optExport');
    var optMore = document.getElementById('optMore');

    var txtNumber = document.getElementById('bd-number');

    function render() {

        FormEdit.render(10103);

        $(optSave).on('click', function (event) {
            //FormEdit.submit();
            FormEdit.save(
                function (successData, errorData) {
                    for (var item in successData) {

                        var msgElement = document.getElementById('bd-' + item + '-msg');
                        if ($(msgElement).hasClass('show')) {
                            $(msgElement).toggleClass('show');
                        }
                        $(msgElement).html('');
                    }
                    if (errorData) {
                        for (var item in errorData) {

                            var msgElement = document.getElementById('bd-' + item + '-msg');
                            if (!$(msgElement).hasClass('show')) {
                                $(msgElement).toggleClass('show');
                            }
                            $(msgElement).html(errorData[item]);
                        }
                    }
                });
        });

        $('#optQuery').on('click', function () {
            FormEdit.show(txtNumber.value);
        });

        $('#optDelete').on('click', function () {
            FormEdit.del();
        });
    }

    return {
        render: render
    }

});


