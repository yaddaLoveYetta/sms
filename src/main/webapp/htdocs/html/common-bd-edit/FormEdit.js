

define('FormEdit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');
    var API = KERP.require('API');

    var Validate = require('Validate');

    var metaData;
    var selectors;

    //formClassID:  基础资料类别
    //itemID:   基础资料内码，编辑时使用，新增时传0即可
    //elements： 特殊控件，需要通过调用者传入
    //fn:   含有表体字段时，暂时通过回调给到调用者呈现
    function render(formClassID, itemID, elements, fnEntry) {

        selectors = elements;
        getMetaData(formClassID, itemID, fnEntry);

    }

    function getMetaData(formClassID, itemID, fnEntry) {

        var api = new API('bos/forminfo');

        api.get({
            'action' : 'queryByClassID',
            'formClassID': formClassID,
            'queryType' : 1
        });

        api.on({
            'success': function (data, json) {
                metaData = data;
                if (itemID) {
                    show(formClassID, itemID, fnEntry);
                }
            },

            'fail': function (code, msg, json) {
                KERP.Tips.error(msg);
            },

            'error': function () {
                KERP.Tips.error('网络错误，请稍候再试');
            }
        });
    }

    function show(formClassID, itemId, fnEntry) {
        if (!itemId) {
            return;
        }
        var api = new API('bos/baseitem');

        api.get({
            'action' : 'queryByID',
            'data' : {
                'classID' : formClassID,
                'itemID' : itemId
            }
        });

        api.on({
            'success': function (data, json) {
                fill(data, fnEntry);
            },

            'fail': function (code, msg, json) {
                KERP.Tips.error(msg);
            },

            'error': function () {
                KERP.Tips.error('网络错误，请稍候再试');
            }
        });
    }

    function save(itemID, fnValidate, fnSuccess) {

        verifyFields(

            function (successData) {

                fnValidate(successData);

                var data = {};
                data['classID'] = metaData['formClass']['formClassID'];
                data['data'] = successData;
                if (itemID) {
                    data['itemID'] = itemID;
                }

                submitData(data, fnSuccess);

            }, function (successData, errorData) {

                fnValidate(successData, errorData);

            });
    }

    function verifyFields(fnSuccess, fnFail) {
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            alert('元数据错误，请联系管理员');
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

            if (field['ctrlType'] == 3) {   //多选按钮，无需校验
                successData[keyName] = element.checked;
                continue;
            }
            if (field['ctrlType'] == 6) {   //F7选择框
                var selector = selectors[keyName];
                var selectorID = selector.getData()[0]['ID'];

                if (!selectorID) {
                    if (field['mustInput']) {
                        var msg = field['caption'] + '为必填项';
                        errorData[keyName] = msg;
                        validate = false;
                    }
                }
                else {
                    successData[keyName] = selectorID;
                }
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

    function fill(data, fnEntry) {
    	/* old
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            KERP.Tips.error('元数据错误，请联系管理员');
            return;
        }
        */
        if (!metaData || !metaData['formFields']) {
            KERP.Tips.error('元数据错误，请联系管理员');
            return;
        }

        if (!data) {
            KERP.Tips.error('数据错误，请联系管理员');
            return;
        }

        var fields = metaData['formFields'];
        var formClass = metaData['formClass'];
		var data=data.items;
        for (var item in data) {

            var keyName = item;
            var element = getValueElement(keyName);
            var value = data[keyName];

            //保存内码，用于判断保存时，是新增还是修改
            //if (keyName == formClass['primaryKey']) {
            //    itemID = value;
            //    continue;
            //}

            if (!element) {
                //todo: 按数据未找到控件，跳过此字段赋值
                continue;
            }

            if (fields[keyName]['ctrlType'] == 3) {     //多选按钮
                element.checked = value;
                continue;
            }

            if (fields[keyName]['ctrlType'] == 6) {     //F7选择框
                //element.value = value;
                //var nameElement = getNameElement(keyName);
                //var nameValue = data[keyName + '_DspName'];
                //nameElement.value = nameValue;
                var selectorData = [{
                    ID: value,
                    number: data[keyName + '_NmbName'],
                    name: data[keyName + '_DspName']
                }];
                
                selectors[keyName].setData(selectorData);
                continue;
            }

            element.value = value;
        }

        //存在表体数据时，交给调用者自己处理
        if (data['entry']) {
            fnEntry(data['entry'], metaData);
        }

        KERP.Tips.success('数据加载成功', 2000);
    }

    //表体数据的清空，暂未处理
    function clear() {
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            KERP.Tips.error('元数据错误，请联系管理员');
            return;
        }

        var fields = metaData['formFields'][0]
        var formClass = metaData['formClass'];

        for (var item in fields) {

            var field = fields[item];
            var keyName = field['fieldKey'];

            var element = getValueElement(keyName);

            if (!element) {
                //todo: 按数据未找到控件，跳过此字段赋值
                continue;
            }

            if (fields[keyName]['ctrlType'] == 3) {     //多选按钮
                element.checked = false;
                continue;
            }

            if (fields[keyName]['ctrlType'] == 6) {     //F7选择框

                var selectorData = [{
                    ID: '',
                    number: '',
                    name: ''
                }];

                selectors[keyName].setData(selectorData);
                continue;
            }

            element.value = '';
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

    function submitData(data, fnSuccess) {

        var action = 'addNew';
        if (data['itemID']) {
            action = 'update';
        }

        var api = new API('bos/baseitem', {
            query: {
                'action': action
            },
        });

        api.post({
            data: data
        });
        
        api.on({
            'success': function (rData, json) {
                fnSuccess(rData);
            },

            'fail': function (code, msg, json) {
                KERP.Tips.error(msg);
            },

            'error': function () {                
                KERP.Tips.error('网络错误，请稍候再试');

            }
        });
    }

    function forbid(classID, itemID, operateType) {
        
        var api = new KERP.API('bos/baseitem');
        api.get({
            action: 'forbid',
            data: {
                'classID': classID,
                'itemID': itemID,
                'operateType': operateType
            }
        });

        api.on({
            'success': function (data, json) {
                KERP.Tips.success(operateType === 1 ? '禁用成功' : '反禁用成功', 2000);
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                KERP.Tips.error(s);
            },

            'error': function () {
                KERP.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function searchItems(formClassID, keyName, fn) {

        var api = new API('bos/baseitem');

        api.get({
            action: 'itemSearch ',
            data: {
                classID: formClassID,
                fieldKey: keyName,
                keyValue: '',
                searhType: 0,
                pageNo: 1,
                pageSize: 200,
            }
        });

        api.on({
            'success': function (data, json) {
                fn(data);
            },

            'fail': function (code, msg, json) {
                KERP.Tips.error(msg);
            },

            'error': function () {
                KERP.Tips.error('网络错误，请稍候再试');
            }
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
        save: save,
        clear: clear,
        forbid: forbid,
        searchItems: searchItems,
    };
});