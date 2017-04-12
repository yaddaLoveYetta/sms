
define('ShopData', function(require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var ModuleAPI = require('ModuleAPI');

    function load(fn) {

        var args = {
        
        };

        ModuleAPI.list(args, function(data) { fn(data); });
    }

    return {
        load: load
    }
});