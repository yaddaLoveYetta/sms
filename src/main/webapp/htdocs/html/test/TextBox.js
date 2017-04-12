





var TextBox = (function () {



    //主键盘上的 '0-9': 48-57 
    //主键盘上的 '.': 190
    //数字键盘的 '0-9': 96-105
    //数字键盘的 '.': 110


    var mapper = new $.Mapper();



    function check(value, decimal) {

        decimal = decimal || 0;

        if (decimal > 0) {
            var reg = '^[0-9]+\\.[0-9]{' + decimal + '}$';
            return new RegExp(reg).test(value);
        }

        return (/^[0-9]+$/g).test(value);

    }

    var key$code = [
        8,      //回格键
        17,     //Ctrl 键
        18,     //Alt 键
        37,     //向左键
        38,     //向上键
        39,     //向右键
        40,     //向下键
        46,     //删除键
        110,    //数字键盘的 "."
        190,    //主键盘的 "."
    ];

    key$code = key$code.concat($.Array.pad(48, 58));
    key$code = key$code.concat($.Array.pad(96, 106));
    key$code = $.Array.toObject(key$code, key$code);



    function TextBox(config) {

        var self = this;

        var emitter = new MiniQuery.Event(this);

        var value = config.value;
        value = value == null ? '' : value.toString();


        var meta = {
            'type': config.type,
            'decimal': 4,
            'selector': config.selector,
            'placeHolder': config.placeHolder,
            'value': value,
            'oldValue': '',
            'emitter': emitter,
        };

        mapper.set(this, meta);


        if (value) {
            this.setValue(value);
        }


        var events = meta.events = {

            'focusin': function (event) {
                
            },

            'focusout': function (event) {

                var value = self.getValue();
                if (!value) {
                    return;
                }

                var decimal = meta.decimal;

                if (decimal > 0) {

                    if (value.indexOf('.') > 0) {

                        value += '0';

                        var a = value.match(/\d+/)[0];
                        var b = value.match(/\.\d+/)[0];
                        
                        b = $.String.padRight(b, decimal + 1, '0');
                        value = a + b;
                    }
                    else {
                        value = value + $.String.padRight('.', decimal + 1, '0');
                    }
                }

                $(meta.selector).val(value);
            },

            'keydown': function (event) {

                var txt = this;

                var keyCode = event.keyCode;
                console.log(keyCode);

                if (keyCode == 13) {
                    $(txt).focusout();
                    return;
                }

                var value = self.getValue();
                var decimal = meta.decimal;

                

                var start = txt.selectionStart; //选中区域的开始位置
                var end = txt.selectionEnd;

                var rejected =
                    (!event.ctrlKey && !key$code[keyCode]) ||
                    (check(value, decimal) &&   //已经输入符合格式的值
                        keyCode != 8 &&         //不是回退键
                        start == end);          //没有选中

                if (rejected) {
                    event.preventDefault();
                    return;
                }

                
            },

            'keyup': function (event) {
                
            },
        };

        $(document).delegate(meta.selector,events);

    }




    TextBox.prototype = { //实例方法
        constructor: TextBox,

        on: function () {
            var meta = mapper.get(this);
            var emitter = meta.emitter;
            var args = Array.prototype.slice.call(arguments, 0);

            emitter.on.apply(emitter, args);
        },

        /**
        * 销毁本控件实例。
        */
        destroy: function () {

            var meta = mapper.get(this);

            $(document).undelegate(meta.selector, meta.events);

            meta.emitter.off();
            mapper.remove(this);

        },

        setValue: function (value) {

            var meta = mapper.get(this);
            var oldValue = $(meta.selector).val();

            if (value == oldValue) {
                return;
            }

           
            $(meta.selector).val(value);

            this.validate();
        },

        getValue: function () {
            var meta = mapper.get(this);
            return $(meta.selector).val();
        },

        validate: function () {

            var meta = mapper.get(this);
            var emitter = meta.emitter;

            var value = $(meta.selector).val();
            var oldValue = meta.oldValue;

            meta.oldValue = value;

            if (value != oldValue) {
                emitter.fire('change', [value, oldValue]);
            }

            if (!check(value, meta.decimal)) {
                emitter.fire('error', [value, oldValue]);
                return false;
            }

            return true;

        }
    };




    return $.Object.extend(TextBox, { //静态方法

    });



})();