/**
 * 单据头模块
 * Created by yadda on 2017/5/13.
 */

define('Bill/Head', function (require, module, exports) {

        var div = document.getElementById("dd-head");
        var samples = require("/Samples")(div);


        function render(template, data) {

            var fields = template['0'];

            fields = $.Array.group(fields, 4); // 分组，一行填充4个字段

            div.innerHTML = $.Array.keep(fields, function (group, no) {

                return $.String.format(samples["rows"], {

                    items: $.Array.keep(group, function (field, no) {

                        var sample = "";
                        var key = field.key;

                        if (!field.display) {
                            return '';
                        }

                        var domType = field.ctrlType;

                        if (!!!domType) {
                            // 默认文本
                            domType = 10;
                        }

                        /*
                         1,3,5,6,7,8,9,10,12,98,99
                         */
                        switch (domType) {
                            case 1: // 小数
                            case 8: // 手机号码
                            case 9://座机电话
                            case 10: // 普通文本
                                sample = samples["row.text"];
                                break;
                            case 11://多行文本
                                sample = samples["row.textarea"];
                                break;
                            case 12:
                                sample = samples["row.datatime"];
                                break;
                            case 3: // checkbox
                                sample = samples["row.checkbox"];
                                break;
                            case 6:
                                sample = samples["row.f7"];
                                break;
                            case 99:
                                sample = samples["row.password"];
                                break;
                            default:
                                sample = samples["row.text"];
                        }

                        return $.String.format(sample, {
                            mustInput: field.mustInput ? $.String.format(samples["row.mustInput"], {}) : "",
                            name: field.name,
                            key: field.key,
                            // disabled: item.lookUpType && item.lookUpType == 3 ? "disabled" : "", // 辅助属性不可编辑
                        });

                    }).join(""),

                });

            }).join("");

            console.log(div.innerHTML);
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
