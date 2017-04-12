










; (function ($, MiniQuery, KERP) {


    KERP.use('NumberField', function (NumberField) {

        var nf = new NumberField('#txt', {

            currencySign: '$',
            currencyPlace: 'left',
            padded: false,
            empty: 'zero',
            leadingZero: 'deny',
            decimalCount: 3,

        });


        $('#txt').focusout(function () {
            //debugger;
            var v = nf.get();
            console.log(v);
        });
    });


})(jQuery, MiniQuery, KERP);