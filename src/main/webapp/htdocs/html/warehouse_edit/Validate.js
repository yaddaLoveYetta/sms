

define('Validate', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    function integer(value) {
        var reg = /^\d+$/;
        return reg.test(value);
    }

    return {
        integer: integer,
    };
});