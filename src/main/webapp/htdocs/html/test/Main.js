


define(function (require, exports, module) {

    console.log('require module: Main');

    var mod1 = require('./Mod1');
    mod1.hello();


    if (1===0) {
        var mod2 = require('./Mod2');
        mod2.hello();
    }


    return {
        hello: function () {
            console.log('hello Main');
        }
    };


});