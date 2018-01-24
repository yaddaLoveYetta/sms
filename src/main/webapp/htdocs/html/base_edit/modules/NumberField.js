/**
 * 数值输入框控件
 */
define('NumberField', function (require, module, exports) {


    function render() {

        SMS.use('NumberField', function (NumberField) {

            var taxRate = new NumberField('#bd-taxRate');

        });
    }

    module.exports = {

        render: render,
    }
});