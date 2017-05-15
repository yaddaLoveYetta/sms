/**
 * 单据头模块
 * Created by yadda on 2017/5/13.
 */

define('Bill/Head', function (require, module, exports) {

    var data;

    var div = document.getElementById("dd-head");
    var samples = require("/Samples")(div);


    function render(template, data) {
        data = data;

        var i = 0;
        var fields = template['0'];

        fields = $.Array.group(fields, 4);

        var innerHtml = '';
        var sample;

        var length = $.Object.getLength(fields);

        /*        for (var key in fields) {

         if (i / 4 === 0) {
         sample = samples["rows"]
         }else {
         sample = samples["row"]
         }
         var field = fields[key];

         innerHtml += $.String.format(sample, {});

         i++;
         }*/

        div.innerHTML = $.String.format(samples["rows"], {

            item: $.Array.keep(fields, function (field, no) {

                if (!field.visible) {
                    return '';
                }

                return $.String.format(samples["row"], {
                    name: field.name,
                    value: data[field.key],
                });

                return $.Array.keep(fields, function (field, no) {

                });


            }).join(""),

        });

    }

    return {
        render: render,
    };
});
