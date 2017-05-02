define('FormEdit', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var MD5 = SMS.require('MD5');
    var Validate = require('Validate');
    var DataSelector = require('DataSelector');
    var emitter = MiniQuery.Event.create();
    var addressCascadePickerArray = ["FProvince", "FCity", "FDistrict"];
    var user = SMS.Login.get();

    var div = document.getElementById("div-content");
    var samples = require("Samples")(div);

    // 控制业务是否有修改-有修改关闭时进行提示
    var billChanged = false;
    var metaData;
    var selectors = {};
    var password;
    var formClassId;
    // formClassId: 基础资料类别
    // itemID: 基础资料内码，编辑时使用，新增时传0即可
    // elements： 特殊控件，需要通过调用者传入
    // fn: 含有表体字段时，暂时通过回调给到调用者呈现
    function render(formClassId, itemId, elements, fnEntry) {

        // selectors = elements;
        formClassId = formClassId;
        fnEntry = fnEntry;
        getMetaData(formClassId, itemId, fnEntry);

    }

    function getMetaData(formClassId, itemId, fnEntry) {

        emitter.fire('getMetaData', []);

        // 获取基础资料模板
        var api = new API('template/getFormTemplate');

        api.get({
            'classId': formClassId,
        });

        api.on({
            'success': function (data, json) {
                metaData = data;
                // if (itemID) {
                show(formClassId, itemId, fnEntry);
                // }
            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');
            }
        });
    }

    //-----------F7处理逻辑Begin-------------//
    var getF7Data = function (itemId, classId, lookUpType, fn) {
        var api;

        if (lookUpType == 1) { //
            api = new API('baseitem/getBaseItemByID');

            api.get({
                'classID': classId,
                'ID': itemId
            });
            api.on({
                'success': function (data, json) {
                    fn && fn(data, json);
                },

                'fail': function (code, msg, json) {
                    SMS.Tips.error(msg);
                },

                'error': function () {
                    SMS.Tips.error('网络错误，请稍候再试');
                }
            });
        }
        if (lookUpType == 2) { //
            api = new API('assistitem/getItemByID');
            api.post({
                itemID: itemId
            });
            api.on({
                'success': function (data, json) {
                    fn && fn(data, json);
                },

                'fail': function (code, msg, json) {
                    SMS.Tips.error(msg);
                },

                'error': function () {
                    SMS.Tips.error('网络错误，请稍候再试');
                }
            });
        }
    };

    var setF7Data = function (data, key, selectors) {
        var selectorData = [{
            ID: data.FID,
            number: data.FNumber,
            name: data.FName
        }];
        selectors[key].setData(selectorData);
        emitter.fire(key + '.defaultFill', [selectorData]);

    };

    //锁定F7控件
    var lockF7 = function (key, defaultVal, classId, lookUpType, isDisabled, selectors) {

        if (defaultVal) { //是否具有默认值
            getF7Data(defaultVal, classId, lookUpType, function (data) {
                setF7Data(data, key, selectors);
            });
        }
        if (isDisabled) { // 是否锁定
            var ftype = $("#bd-" + key);
            var inpt = ftype.find("input");
            var sbtn = ftype.find('[data-role="btn"]');
            $(inpt).attr("disabled", "disabled");
            $(sbtn).attr("disabled", "disabled");
            $(ftype).undelegate('[data-role="btn"]', 'click');
        }
    };

    //-----------F7处理逻辑End-------------//

    //------是否必填校验逻辑(因多地方使用所以抽出来) BEGIN-----//
    var isMustFiled = function (isUpdate, field) {
        var mustInput = field['mustInput'] || 0;
        var mustInputMask = 0; //是否必填掩码
        if (isUpdate) {
            //FMustInput  字段显示权限-后端mustInput定义 4 编辑时平台用户必填，8编辑时候物业用户必填
            if (user.type == 1) {
                // 平台用户
                mustInputMask = 2;
            } else if (user.type == 2) {
                //物业用户
                mustInputMask = 8;
            }
        } else {
            //FMustInput  字段显示权限-后端mustInput定义 4 编辑时平台用户必填，8编辑时候物业用户必填
            if (user.type == 1) {
                // 平台用户
                mustInputMask = 1;
            } else if (user.type == 2) {
                //物业用户
                mustInputMask = 4;
            }
        }
        return !!(mustInputMask & mustInput); //是否必填
    }

    //------是否必填校验逻辑 END-----//

    function initController(itemId) {
        var isUpdate = !!itemId;
        //是否是修改
        if (!metaData || !metaData['formFields'] || !metaData['formFields'][0]) {
            SMS.Tips.error('元数据错误，请联系管理员');
            return;
        }
        var fields = metaData['formFields'][0];

        for (var item in fields) {
            var field = fields[item];
            var keyName = field['key'];
            var defaultValue = field['defaultValue'];
            var lookUpClassId = field['lookUpClassId'];
            var element = getElements(keyName);
            if (!element) {
                continue;
            }

            var mask = field["display"] || 0;
            var flockMaskDisplay = 0;
            var display = 0;
            if (isUpdate) {
                flockMaskDisplay = 2;

                //display 字段显示权限-后端FDisPlay定义 4：编辑时对于平台用户显示，8：编辑时对于供应商用户显示：12：平台供应商用户都显示
                //flockMaskDisplay  字段显示权限-后端FLock定义 4 编辑时平台用户锁定，8编辑时候供应商用户锁定
                if (user.type == 1) {
                    // 平台用户
                    display = 4;
                    flockMaskDisplay = 2;
                } else if (user.type == 2) {
                    //供应商用户
                    display = 8;
                    flockMaskDisplay = 8;
                }
            } else {
                // 字段显示权限-后端FDisPlay定义 16：新增时对于平台用户显示，32：新增时对于供应商用户显示：48：平台供应商用户都显示
                //flockMaskDisplay  字段显示权限-后端FLock定义 1 编辑时平台用户锁定，4编辑时候供应商用户锁定
                if (user.type == 1) {
                    // 平台用户
                    display = 16;
                    flockMaskDisplay = 1;
                } else if (user.type == 2) {
                    //物业用户
                    display = 32;
                    flockMaskDisplay = 4;
                }
            }
            var flockMask = field['lock'] || 0;
            var isLock = !!(flockMask & flockMaskDisplay);
            //是否锁定
            var isRemove = !(mask & display);
            //是否 移除
            //if (field['FCtrlType'] == 1) {
            //    element.autoNumeric('init');
            //}
            //默认值处理
            if (defaultValue || defaultValue == false && defaultValue == 0) {
                if (field['ctrlType'] == 1) {
                    //                  if (element.autoNumeric) {
                    //                      element.autoNumeric('set', defaultValue);
                    //                  } else {
                    element.val(defaultValue);
                    //                  }
                }
                if (field["dataType"] == 2) { //数字 文本值
                    element.val(defaultValue);
                }
                if (field["dataType"] == 3) { //日期时间
                    // element.value = defaultValue;
                }
                if (field["dataType"] == 4) { //布尔
                    element.prop("checked", defaultValue);
                }
            }
            //F7选择框处理
            if (field['ctrlType'] == 6) {
                var key1 = keyName;
                var lookUpType = field['lookUpType'];
                var classId = field['lookUpClassId'];
                lockF7(key1, defaultValue, classId, lookUpType, isLock, selectors);
            }
            //锁定处理
            if (isLock) {
                element.prop("disabled", "disabled");
            } else {
                element.prop("disabled", "");
            }
            //移除处理
            element.parents("tr").hide();
            if (isRemove) {
                element.parents("tr").remove();
            } else {
                element.parents("tr").show();
            }
            //必填处理
            if (isMustFiled(isUpdate, field)) {
                //如果是必填需要添加 红色 * 号
                var addressCascadePicker = field['key'];
                if ($.inArray(addressCascadePicker, addressCascadePickerArray) >= 0) {
                    element = $('#div-address-picker'); //地址级联必填特殊处理
                }
                if (element.parents("td").siblings().find(".must-mark").length <= 0) { //如果不存在
                    var html = element.parents("td").siblings().html();
                    element.parents("td").siblings().html("<span class=\"must-mark\">*</span>" + html);
                }
            } else {
                if (element.parents("td").siblings().find(".must-mark")) {
                    element.parents("td").siblings().find(".must-mark").remove();
                }
            }
        }

        //DOM 元素处理，后台没返回则直接移除掉
        var fildKeys = $.Object.getKeys(fields);
        //选择所有 input元素，和 F7
        $(":input[id^='bd-']:not([type='hidden']),.F7-box[id^='bd-']").each(function (index, item) {
            var domId = $(item).attr("id").replace("bd-", "");
            if ($.inArray(domId, fildKeys) < 0) {
                $(item).parents("tr").remove();
                ;
            }
        });
    }

    /**
     * 根据模板构建页面控件
     * @param metaData 模板数据
     */
    function initPage(metaData) {

        var fields = MiniQuery.Object.toArray(metaData['formFields'][0]);

        div.innerHTML = $.String.format(samples["table"], {

            trs: $.Array.keep(fields, function (item, no) {

                var sample = "";

                if (!item.display) {
                    return "";
                }

                var domType = item.ctrlType;

                if (!!domType) {
                    // 默认文本
                    domType = 10;
                }

                /*
                 1
                 3
                 5
                 6
                 7
                 8
                 9
                 10
                 98
                 99
                 */
                switch (domType) {
                    case 1: // 文本
                    case 8: // 手机号码
                    case 9://座机电话
                    case 10: // 普通文本
                        sample = samples["tr.text"];
                        break;
                    case 3: // checkbox
                        sample = samples["tr.checkbox"];
                        break;
                    case 6:
                        sample = samples["tr.f7"];
                        break;
                    case 99:
                        sample = samples["tr.password"];
                        break;
                    default:
                        sample = samples["tr.text"];
                }

                return $.String.format(sample, {
                    mustInput: item.mustInput ? $.String.format(samples["td.mustInput"], {}) : "",
                    name: item.name,
                    key: item.key
                });

            }).join(""),


        });
    }

    // 初始化选择框控件
    function initSelectors(metaData) {

        var fields = metaData['formFields'][0];

        for (var item in fields) {

            var field = fields[item];

            if (field.lookUpType > 0) {
                // 引用基础资料
                var config = {
                    targetType: 1, //跳转方案
                    classID: field.lookUpClassID,
                    hasBreadcrumbs: true,
                    container: document.getElementById('bd-' + field.key),
                    title: field.name,
                    defaults: {
                        pageSize: 8
                    }
                };

                selectors[field.key] = DataSelector.create(config);
            }
        }

    }

    function show(formClassId, itemId, fnEntry) {


        if (!metaData || !metaData['formFields'] || !metaData['formClass']) {
            SMS.Tips.error('元数据错误，请联系管理员');
            return;
        }

        emitter.fire('beforeShow', [metaData]);

        // 填充页面控件
        initPage(metaData);
        // 初始化selectors
        initSelectors(metaData);
        //控件初始化，控制显示隐藏，只读 等..
        initController(itemId);

        if (!itemId) {

            if (!!fnEntry && !MiniQuery.Object.isEmpty(metaData['formEntries'])) {
                fnEntry && fnEntry(null, metaData);
            }

            return;
        }
        var api = new API('template/getItemById');
        SMS.Tips.loading('数据加载中..');

        api.get({

            'classId': formClassId,
            'id': itemId

        });

        api.on({
            'success': function (data, json) {
                fill(data, fnEntry);
                SMS.Tips.success('数据加载成功', 1500);
            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');
            }
        });
    }

    /**
     * 保存
     * @param {} itemID 项目ID
     * @param {} fnValidate  验证错误回调函数-处理页面显示的错误提示
     * @param {} fnSuccess  成功回调函数
     * @param {} entryData //子表数据
     * @param {} customerErrData  //自定义错误消息
     * @returns {}
     */
    function save(itemID, fnValidate, fnSuccess, entryData, customerErrData) {

        // 编辑OR新增
        var isUpdate = !!itemID;

        verifyFields(isUpdate, function (successData) {

            fnValidate(successData);

            var data = {};
            data['classId'] = metaData['formClass']['classId'];
            data['data'] = successData;

            if (entryData) {
                data['data']['entry'] = entryData;
            }
            if (itemID) {
                data['itemId'] = itemID;
            }
            submitData(data, fnSuccess);

        }, function (successData, errorData) {
            // errorData.push(entryErrorData);
            fnValidate(successData, errorData);

        }, customerErrData);
    }

    /**
     * 数据校验-获取表头数据
     * @param isUpdate 编辑or新增
     * @param fnSuccess 成功回调
     * @param fnFail 失败回调
     * @param customerErrData 自定义错误
     */
    function verifyFields(isUpdate, fnSuccess, fnFail, customerErrData) {

        if (!metaData || !metaData['formFields']) {
            alert('元数据错误，请联系管理员');
            return;
        }
        // var formClass = metaData['formClass'];
        var fields = metaData['formFields'][0];
        var validate = true;
        var successData = {};
        var errorData = {};
        //合并错误提示信息
        if ($.Object.getKeys(customerErrData).length > 0) {
            $.Object.extend(errorData, customerErrData || {});
            validate = false;
        }
        for (var item in fields) {

            var field = fields[item];
            var keyName = field['key'];

            // var prefix = getPrefix(field['ctrlType']);
            var element = getValueElement(keyName);

            if (!element) {
                // todo: 按元数据未找到控件，跳过此字段
                continue;
            }
            //successData[keyName] = "";//清空元素的值，重新赋值
            if (field['ctrlType'] == 1) { // 数字
                var ne = getElements(keyName);
                var minVal = +field['minValue'];
                var maxVal = +field['maxValue'];
                var domValue = ne.val();
                var widgetData = ne.autoNumeric && ne.autoNumeric("get") || domValue;
                if (!domValue) {
                    if (isMustFiled(isUpdate, field)) {
                        var msg = field['name'] + '为必填项';
                        errorData[keyName] = msg;
                        validate = false;
                    } else {
                        //var checkNumber = validateFieldNumber(field['FName'], widgetData, maxVal, minVal);
                        successData[keyName] = widgetData;
                    }
                } else {
                    //var checkNumber = validateFieldNumber(field['FName'], widgetData, maxVal, minVal);
                    successData[keyName] = widgetData;

                }
                continue;
            }
            if (field['ctrlType'] == 3) { // 多选按钮，无需校验
                successData[keyName] = element.checked;
                continue;
            }
            if (field['ctrlType'] == 6) { // F7选择框
                var selector = selectors[keyName];
                var selectorID = selector.getData()[0]['ID'];

                if (!selectorID) {
                    if (isMustFiled(isUpdate, field)) {
                        var msg = field['name'] + '为必填项';
                        errorData[keyName] = msg;
                        validate = false;
                    } else {
                        successData[keyName] = 0;
                    }
                } else {
                    successData[keyName] = selectorID;
                }
                continue;
            }

            if (fields[keyName]['ctrlType'] == 99) {
                // 合作商家基础资料多一个密码字段特殊，蛋疼的处理-写死它，用来判断是否修改-加密
                if (!element.value && isMustFiled(isUpdate, field)) {
                    var msg = field['name'] + '为必填项';
                    errorData[keyName] = msg;
                    validate = false;
                } else {
                    if (element.value != 'XXXXXXXX') {
                        successData[keyName] = MD5.encrypt(element.value);
                    }
                }
                continue;
            }

            if (!element.value) {
                if (isMustFiled(isUpdate, field)) {
                    var msg = field['name'] + '为必填项';
                    errorData[keyName] = msg;
                    validate = false;
                } else {
                    if (fields[keyName]['dataType'] == 2 || fields[keyName]['ctrlType'] == 7) { //文本类型、级联选择器 给空字符串
                        successData[keyName] = element.value;
                    }
                }
            } else {
                var result = validateField(element.value, field['dataType'], field['length'], field['scale'], field['ctrlType']);
                if (!result) {
                    var msg = field['name'] + '输入内容不合法';
                    errorData[keyName] = msg;
                    validate = false;
                } else {
                    // 构建待提交的数据
                    successData[keyName] = element.value;
                }
            }
        }

        if (validate) {
            fnSuccess(successData);
        } else {
            fnFail(successData, errorData);
        }
    }

    function fill(data, fnEntry) {

        if (!metaData || !metaData['formFields']) {
            SMS.Tips.error('元数据错误，请联系管理员');
            return;
        }

        if (!data) {
            SMS.Tips.error('数据错误，请联系管理员');
            return;
        }

        var fields = metaData['formFields'][0];
        // var formClass = metaData['formClass'];
        // var data = data.items;
        var data = data;
        for (var item in data) {

            var keyName = item;
            var element = getValueElement(keyName);
            var value = data[keyName];

            // 保存内码，用于判断保存时，是新增还是修改
            // if (keyName == formClass['primaryKey']) {
            // itemID = value;
            // continue;
            // }

            if (!element) {
                // todo: 按数据未找到控件，跳过此字段赋值
                continue;
            }
            if (fields[keyName]['ctrlType'] == 1) { // 数字
                var ne = getElements(keyName);
                //初始化控件
                try {
                    ne.autoNumeric('set', (value || 0));
                } catch (e) {
                    ne.val((value || 0));
                }

                continue;
            }
            if (fields[keyName]['ctrlType'] == 3) { // 多选按钮
                element.checked = value;
                continue;
            }

            if (fields[keyName]['ctrlType'] == 5) { // 下拉框
                element.value = value == null ? '' : Number(value);
                continue;
            }
            if (fields[keyName]['ctrlType'] == 6) { // F7选择框

                var selectorData = [{
                    ID: value,
                    number: data[keyName + '_NmbName'],
                    name: data[keyName + '_DspName']
                }];

                selectors[keyName].setData(selectorData);
                continue;
            }

            if (fields[keyName]['ctrlType'] == 99) {
                // 用户多一个密码字段特殊，蛋疼的处理-写死它，用来判断是否修改-加密
                element.value = 'XXXXXXXX';
                continue;
            }

            element.value = value;
        }

        // 存在表体数据时，交给调用者自己处理
        if (data['entry'] && !MiniQuery.Object.isEmpty(data['entry'])) {
            if (typeof(fnEntry) == 'function') {
                fnEntry(data['entry'], metaData);
            }
        }

        emitter.fire('afterFill', [metaData, data]);

        SMS.Tips.success('数据加载成功', 2000);
    }

    // 表体数据的清空，暂未处理
    function clear() {
        if (!metaData || !metaData['formFields']) {
            SMS.Tips.error('元数据错误，请联系管理员');
            return;
        }

        var fields = metaData['formFields'][0];
        var formClass = metaData['formClass'];
        initController();
        //初始化控件
        for (var item in fields) {

            var field = fields[item];
            var keyName = field['FKey'];

            var element = getValueElement(keyName);

            if (!element) {
                // todo: 按数据未找到控件，跳过此字段赋值
                continue;
            }
            if (fields[keyName]['ctrlType'] == 1) { // 数字
                var ne = getElements(keyName);
                ne.autoNumeric('set', '');
                continue;
            }
            if (fields[keyName]['ctrlType'] == 3) { // 多选按钮
                element.checked = false;
                continue;
            }

            if (fields[keyName]['ctrlType'] == 6) { // F7选择框

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

    /**
     * 数字类型校验
     * @param {} value 当前值
     * @param {} maxValue 最大值
     * @param {} minValue 最小值
     * @returns {} 1:小于最小值  2:大于最大值 3:通过
     */
    function validateFieldNumber(fileName, value, maxValue, minValue) {
        if (minValue !== null && minValue !== undefined) {
            if (+value <= +minValue) {
                return {
                    result: false,
                    msg: fileName + "不能小于" + minValue
                };
            }
        }
        if (maxValue !== null && maxValue !== undefined) {
            if (+value >= +maxValue) {
                return {
                    result: false,
                    msg: fileName + "不能大于" + maxValue
                };
            }
        }
        return {
            result: true,
            msg: ""
        };
    }

    // value:待校验值
    // valueType:规定类型
    // length:规定长度
    // scale: 规定小数位
    //ctrlType:控件类型
    function validateField(value, valueType, length, scale, ctrlType) {
        if (valueType == "1") { // 数值
            //return Validate.integer(value);
        }
        if (valueType == "2") { // 文本
            if (ctrlType == "8") {
                //手机号码验证
                return Validate.mobilePhone(value);
            }
            if (ctrlType == "9") {
                //电话验证
                return Validate.landlinePhone(value);
            }
        }
        if (valueType == "3") { // 日期型

        }
        return true;
    }

    function submitData(data, fnSuccess) {

        var action = 'template/addItem';

        if (data['itemId']) {
            action = 'template/editItem';
        }

        var api = new API(action);

        api.post({
            classId: data.classId,
            itemId: data.itemId,
            data: data.data
        });

        api.on({
            'success': function (rData, json) {
                fnSuccess(rData);
            },

            'fail': function (code, msg, json) {
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');

            }
        });
    }

    function forbid(classId, itemId, operateType) {

        var api = new SMS.API('bos/baseitem');
        api.get({
            action: 'forbid',
            data: {
                'classID': classId,
                'itemID': itemId,
                'operateType': operateType
            }
        });

        api.on({
            'success': function (data, json) {
                SMS.Tips.success(operateType === 1 ? '禁用成功' : '反禁用成功', 2000);
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
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
                SMS.Tips.error(msg);
            },

            'error': function () {
                SMS.Tips.error('网络错误，请稍候再试');
            }
        });

    }

    function getValueElement(keyName) {
        return document.getElementById('bd-' + keyName);
    }

    function getNameElement(keyName) {
        return document.getElementById('bd-' + keyName + '-name');
    }

    function getElements(keyName) {
        return $('#bd-' + keyName);
    }

    function isBillChanged() {
        return billChanged;
    }

    return {
        render: render,
        save: save,
        clear: clear,
        forbid: forbid,
        isBillChanged: isBillChanged,
        getSelectors: function (name) {
            return selectors[name];
        },
        searchItems: searchItems,
        initController: initController,
        on: emitter.on.bind(emitter)
    };
});