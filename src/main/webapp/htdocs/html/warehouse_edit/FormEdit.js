

define('FormEdit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var Validate = require('Validate');

    var metaData;
    var itemID = 0;

    function render(formClassID) {

        getMetaData(formClassID);

    }

    function getMetaData(formClassID) {

        var api = KERP.API.get('bos/forminfo', {
            action: 'queryByClassID',
            formClassID: formClassID,
            queryType: 1
        }, function (data, json) {

            metaData = data;

        }, function (code, msg, json) {

            alert(msg);

        }, function () {

            alert('网络错误，请稍候再试');

        });
    }

    function show(number) {
        if (!number) {
            alert('查询代码不能为空');
            return;
        }

        KERP.API.get('bos/baseitem', {
            action: 'queryByNumber ',
            data: {
                classID: metaData['formClass']['formClassID'],
                number: number
            }

        }, function (data, json) {

            fill(data);

        }, function (code, msg, json) {

            alert(msg);

        }, function () {

            alert('网络错误，请稍候再试');

        });
    }

    function save(fn) {

        verifyFields(

            function (successData) {

                fn(successData);

                var data = {};
                data['classID'] = metaData['formClass']['formClassID'];
                data['data'] = successData;
                if (itemID) {
                    data['itemID'] = itemID;
                }

                submitData(data);

            }, function (successData, errorData) {

                fn(successData, errorData);

            });
    }

    function del() {
        if (!itemID) {
            return;
        }
        if (!confirm('确认要删除此数据？')) {
            return;
        }

        var items = new Array();
        var item = {};
        item['itemID'] = itemID;
        items.push(item);

        var data = {
            classID: metaData['formClass']['formClassID'],
            items: items
        };

        KERP.API.post('bos/baseitem', {
            action: 'delete',
            data: data

        }, function (data, json) {
            alert('删除数据成功');

        }, function (code, msg, json) {

            alert(msg);

        }, function () {

            alert('网络错误，请稍候再试');

        });
    }

    function verifyFields(fnSuccess, fnFail) {
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            alert('数据错误，请联系管理员');
            return;
        }

        var formClass = metaData['formClass'];
        var fields = metaData['formFields'][0];
        var validate = true;

        var successData = {};
        var errorData = {};

        for (var item in fields) {

            var field = fields[item];
            var keyName = field['fieldKey'];

            //var prefix = getPrefix(field['ctrlType']);
            var element = getValueElement(keyName);

            if (!element) {
                //todo: 按元数据未找到控件，跳过此字段
                continue;
            }

            if (field['ctrlType'] == 4) {  //单选按钮，无需校验
                successData[keyName] = element.checked;
                continue;
            }

            if (!element.value) {
                if (field['mustInput']) {
                    var msg = field['caption'] + '为必填项';
                    errorData[keyName] = msg;
                    validate = false;
                }
            }
            else {
                var result = validateField(element.value, field['valueType'], field['length'], field['scale']);
                if (!result) {
                    var msg = field['caption'] + '输入内容不合法';
                    errorData[keyName] = msg;
                    validate = false;
                }
                else {
                    //构建待提交的数据
                    successData[keyName] = element.value;
                }
            }
        }

        if (validate) {
            fnSuccess(successData);
        }
        else {
            fnFail(successData, errorData);
        }
    }

    function fill(data) {
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            alert('元数据错误，请联系管理员');
            return;
        }

        if (!data) {
            alert('数据错误，请联系管理员');
            return;
        }

        var fields = metaData['formFields'][0]
        var formClass = metaData['formClass'];

        for (var item in data) {

            var keyName = item;
            var element = getValueElement(keyName);
            var value = data[keyName];

            //保存内码，用于判断保存时，是新增还是修改
            if (keyName == formClass['primaryKey']) {
                itemID = value;
                continue;
            }

            if (!element) {
                //todo: 按数据未找到控件，跳过此字段赋值
                continue;
            }

            if (fields[keyName]['ctrlType'] == 4) {     //单选按钮
                element.checked = value;
                continue;
            }

            element.value = value;
            if (fields[keyName]['ctrlType'] == 6) {     //F7选择框
                var nameElement = getNameElement(keyName);
                var nameValue = data[keyName + '_DspName'];
                nameElement.value = nameValue;
            }
        }
    }

    //value:待校验值
    //valueType:规定类型
    //length:规定长度
    //scale: 规定小数位
    function validateField(value, valueType, length, scale) {
        if (valueType == "1") {        //整型
            return Validate.integer(value);
        }
        if (valueType == "2") {        //数值型

        }
        if (valueType == "5") {        //日期型

        }

        return true;
    }

    function submitData(data) {
        var action = 'addNew';
        if (itemID) {
            action = 'update';
        }
        KERP.API.post('bos/baseitem', {
            action: action,
            data: data

        }, function (data, json) {
            if (itemID) {
                alert('修改数据成功');
            }
            else {
                alert('新增数据成功，ID为' + data['itemID']);
            }

        }, function (code, msg, json) {

            alert(msg);

        }, function () {

            alert('网络错误，请稍候再试');

        });
    }

    function getValueElement(keyName) {
        return document.getElementById('bd-' + keyName);
    }

    function getNameElement(keyName) {
        return document.getElementById('bd-' + keyName + '-name');
    }

    return {
        render: render,
        show: show,
        save: save,
        del: del
    };
});