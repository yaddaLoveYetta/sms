

/**
* ClassMapping 模块
* 
*/
define('ClassMapping', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var YWTC = require('YWTC');

    function getIndex(classID) {
        var classMappings =
            {
                '10107': { first: 1, second: 3 },
                '10103': { first: 1, second: 4 },
                '10106': { first: 1, second: 5 },
                '10110': { first: 1, second: 6 },
            };

        return classMappings[classID];
    }

    return {
        getIndex: getIndex,
    };

});






