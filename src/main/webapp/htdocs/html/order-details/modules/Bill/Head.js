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

            fields = $.Array.group(fields, 4); // 分组，一行填充4个字段

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

            /*            div.innerHTML = $.String.format(samples["rows"], {

             item: $.Array.keep(fields, function (group, no) {


             return $.Array.keep(group, function (field, no) {

             if (!field.visible) {
             return '';
             }
             return $.String.format(samples["row"], {
             name: group.name,
             value: data[group.key],
             })

             }).join("");


             }).join(""),

             });*/


            div.innerHTML = $.Array.keep(fields, function (group, no) {

                return $.String.format(samples["rows"], {

                    item: $.Array.keep(group, function (field, no) {

                        if (!field.visible) {
                            return '';
                        }
                        return $.String.format(samples["row"], {
                            name: field.text,
                            value: getHtml(field.type, data[field.key]),
                        })

                    }).join(""),

                });

            }).join("");
        }

        function getHtml(type, data) {
            /*
             * if ( typeof data == 'boolean') { data = data ? '是' : '否'; }
             */
            if (data == null) {
                data = "";
            }
            if (type == 4) {
                // boolean 类型元数据
                data = data ? "是" : "否";
            }
            if (type == 3) {
                // 日期时间类型
                console.log(data instanceof Date);
            }
            if (type == 98) {
                // 处理男/女显示
                data = data ? "女" : "男";
            }
            return data;
        }

        return {
            render: render,
        };
    }
);
