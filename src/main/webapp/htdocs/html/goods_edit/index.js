

; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var Iframe = KERP.require('Iframe');

    Iframe.on('before-close', function (infos) {
        console.log('before close');
        console.dir(infos);
        return confirm('do you confirm close this page?');
    });


})();