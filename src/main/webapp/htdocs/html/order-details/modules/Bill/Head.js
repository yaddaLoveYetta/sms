/**
 * 单据头模块
 * Created by yadda on 2017/5/13.
 */

define('Bill/Head', function (require, module, exports) {

    var data;

    function render(template, data) {
        data = data;
        console.log(data);
    }

    return {
        render: render,
    };
});
