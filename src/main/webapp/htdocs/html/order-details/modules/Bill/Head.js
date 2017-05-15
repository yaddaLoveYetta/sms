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
        var innerHtml = '';
        var sample;

        var length = $.Object.getLength(fields);

        for (var key in fields) {

            if (i / 4 === 0) {
                sample = samples["rows"]
            }else {
                sample = samples["row"]
            }
            var field = fields[key];

            innerHtml += $.String.format(sample, {});

            i++;
        }

        div.innerHTML = $.String.format(samples["table"], {
            checkbox: data.checkbox ? samples["th.checkbox"] : "",
            ths: $.Array.keep(headItems, function (field, index) {
                return $.String.format(samples["th"], {
                    index: index,
                    th: field.text,
                    width: field.width
                });
            }).join(""),
            trs: $.Array.keep(bodyItems, function (item, no) {
                // 行
                return $.String.format(samples["tr"], {
                    index: no,
                    "disabled-class": item.disabled ? "disabled" : "",
                    checkbox: data.checkbox ? $.String.format(samples["td.checkbox"], {
                        index: no
                    }) : "",
                    tds: $.Array.keep(item.items, function (item, index) {
                        // 列
                        var field = headItems[index];
                        // 当前列的表头信息
                        return $.String.format(samples["td"], {
                            index: index,
                            key: field.key,
                            td: field.isEntry ? getHtml("entry", item.value) : getHtml(field.type, item.value),
                            "number-class": field.key == "number" ? "number" : ""
                        });
                    }).join("")
                });
            }).join(""),
            emptys: data.checkbox ? samples["emptytd"] : "",
            tdtotals: $.Array.keep(headItems, function (field, index) {
                return $.String.format(samples["tdtotal"], {
                    index: index,
                    key: field.key,
                    needTotal: field.isCount == "1",//(field.type == 1 && field.lookupType == 0),
                    width: field.width
                });
            }).join("")
        });

    }

    return {
        render: render,
    };
});
