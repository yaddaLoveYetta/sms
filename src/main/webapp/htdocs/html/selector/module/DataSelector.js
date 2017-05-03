define('DataSelector', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Samples = SMS.require('Samples');
    var emitter = MiniQuery.Event.create();
    var mapper = new $.Mapper();
    var guidKey = $.Mapper.getGuidKey();

    var defaults = {
        multiSelect: false,
        width: 800,
        height: 450,
        pageSize: 10,
        targetList: {
            1: './html/base/index.html',
            2: './html/support-data/index.html',
        }
    };

    var samples = Samples.get('DataSelector', [{
        name: 'selector',
        begin: '#--selector.begin--#',
        end: '#--selector.end--#'
    }]);

    //构造函数
    function DataSelector(config) {

        this[guidKey] = 'DataSelector-' + $.String.random();
        var isFirst = false;
        var meta = {
            container: config.container,
            typeId: config.typeId,
            hasBreadcrumbs: config.hasBreadcrumbs,
            targetType: config.targetType,
            supperConditions: config.supperConditions,
            title: config.title,
            conditions: config.conditions || {},
            classID: config.classID || '',
            destClassId:config.destClassId || '',
            checkbox: config.checkbox,
            conditionF7Names: config.conditionF7Names || [], //新增查询条件集合 [{SelectorName:"FCompany",FillterKey: "FCompany", ValueRule: { 50801: 50701, 50802: 50702 } }]
            data: [{
                'ID': '',
                'name': '',
                'number': ''
            }],
            defaults: (function () {
                if (!!config.defaults) {

                    var cDefaults = $.Object.extend({}, defaults, config.defaults);
                    return cDefaults;

                } else {
                    return defaults;
                }
            })(),
        };

        mapper.set(this, meta);

        meta.bindEvents = function (selector) {

            var meta = mapper.get(selector);
            $(meta.container).delegate('[data-role="btn"]', 'click', function () {

                var url = $.Url.setQueryString(defaults.targetList[meta.targetType], 'classId', meta.classID);

                //新增关联查询条件逻辑 --------------begin--------------
                var dataSelectors = DataSelector.DataSelectors;

                //获取 对象静态变量
                for (var i = 0; i < meta.conditionF7Names.length; i++) {

                    var conditionData = meta.conditionF7Names[i];

                    var type = conditionData.type || "";  // 过滤字段类别
                    var target;
                    var filterKey;
                    if (type === 'selector') {
                        target = conditionData.target || "";
                        filterKey = conditionData.filterKey || "";
                    }

                    if (type == "selector" && $.trim(target) !== "" && $.trim(filterKey) !== "") {
                        // 关联F7控件条件
                        var value = dataSelectors[target].getData() && dataSelectors[target].getData()[0].ID || 0;

                        if (value === 0) {
                            delete meta.conditions[target];//清除没必要的或者已经清空的查询条件
                            continue;
                            //不是必须关联的 如果所关联的为空则跳过该查询条件
                        }

                        meta.conditions[conditionData.target] = {
                            'andOr': 'and',
                            'leftParenTheses': '(',
                            'fieldKey': filterKey,
                            'logicOperator': '=',
                            'value': value,
                            'rightParenTheses': ')',
                            needConvert: false
                        };
                    }

                    /*         var f7Name = conditionData.SelectorName || "";
                     var fillterKey = conditionData.FillterKey || "";
                     var isNeed = conditionData.IsNeed || false;
                     //默认为false
                     var valueRule = conditionData.ValueRule || {};
                     if ($.trim(f7Name) !== "" && $.trim(fillterKey) !== "") {
                     var id = dataSelectors[f7Name].getData() && dataSelectors[f7Name].getData()[0].ID || 0;
                     //(isNeed === true ? 0 : "");//不是必要条件默认为““：否则默认为0 用于查询区分
                     if (id === 0 && isNeed === false) {
                     delete meta.conditions[f7Name];//清除没必要的或者已经清空的查询条件
                     continue;
                     //不是必须关联的 如果所关联的为空则跳过该查询条件
                     }
                     if (id in valueRule) {//如果有值的转换规则则启用规则
                     id = valueRule[id];
                     }
                     meta.conditions[conditionData.SelectorName] = {
                     'andOr': 'and',
                     'leftParenTheses': '(',
                     'fieldKey': fillterKey,
                     'logicOperator': '=',
                     'value': id,
                     'rightParenTheses': ')',
                     needConvert: false
                     };
                     }*/
                }
                //新增关联查询逻辑 --------------end--------------

                SMS.use('Dialog', function (Dialog) {

                    var dialog = new Dialog({
                        title: meta.title,
                        url: url,
                        width: meta.defaults.width,
                        height: meta.defaults.height,
                        data: {
                            typeId: meta.typeId || '',
                            multiSelect: meta.defaults.multiSelect,
                            pageSize: meta.defaults.pageSize,
                            hasBreadcrumbs: meta.hasBreadcrumbs,
                            conditions: meta.conditions,
                            supperConditions: meta.supperConditions,
                            checkbox: meta.checkbox
                        },
                        button: [
                            {
                                value: '取消',
                                className: 'sms-cancel-btn',
                            },
                            {
                                value: '确定',
                                className: 'sms-submit-btn',
                                callback: function () {
                                    this.isSubmit = true;
                                },
                            }
                        ],
                        // ok : function() {
                        // this.isSubmit = true;
                        // },
                        // cancel : function() {
                        // 
                        // }
                    });

                    //默认关闭行为为不提交
                    dialog.isSubmit = false;

                    dialog.showModal();

                    dialog.on({
                        remove: function () {

                            var data = dialog.getData();
                            var label = $(meta.container).find('[data-role="label"]')[0];
                            //console.log(data)
                            //if (dialog.isSubmit && data[0].hasOwnProperty("ID")) {
                            if (dialog.isSubmit && data[0] && typeof data[0].ID != "undefined") {
                                if (meta.data[0].ID != data[0].ID) {
                                   // emitter.fire(meta.destClassId + '-' + meta.container.getAttribute("id") + '.DialogChange', [data]);
                                    //抛出个值改变事件
                                    emitter.fire('change', [meta.destClassId + '-' +meta.container.getAttribute("id") + '.DialogChange',data]);
                                }
                                meta.data = dialog.getData();
                                label.value = meta.data[0].number;
                                //emitter.fire(meta.destClassId +'-'+ meta.container.getAttribute("id") + '.DialogOk', [meta.data]);
                                //抛出个确认事件
                                emitter.fire('done', [meta.destClassId + '-' +meta.container.getAttribute("id") + '.DialogOk',meta.data]);
                                label.focus();
                                isFirst = true;
                            } else {
                                isFirst = false;
                            }
                        }
                    });
                });

            });

            $(meta.container).delegate('[data-role="label"]', {

                'focus': function () {
                    var self = this;
                    if (isFirst) {
                        if (meta.data[0].number) {
                            self.value = meta.data[0].number;
                        }
                    }
                },
                'blur': function () {
                    var self = this;
                    if (isFirst) {
                        if (meta.data[0].name) {
                            self.value = meta.data[0].name;
                        }
                    }
                }
            });

            //新增文本清空设置空数据
            var f7DefaultData = [{
                ID: "",
                number: "",
                name: ""
            }];
            $(meta.container).on('change input propertychange', function () {
                if ($(this).find('input[data-role="label"]').val() == "") {
                    meta.data = f7DefaultData;
                    emitter.fire(meta.container.getAttribute("id") + '.DialogEmpty', [f7DefaultData]);
                }
            });
            $(meta.container).find('input[data-role="label"]').prop("placeholder", "请选择" + meta.title);
        };

    }

    //静态变量 F7 选择框集合
    DataSelector.DataSelectors = {};

    //实例方法
    DataSelector.prototype = {
        constructor: DataSelector,
        getData: function () {

            var meta = mapper.get(this);
            return meta.data;

        },
        render: function () {

            var meta = mapper.get(this);
            $(meta.container).html(samples.selector);
            meta.bindEvents(this);

        },
        setData: function (data) {

            var meta = mapper.get(this);
            if (meta.container) {
                var label = $(meta.container).find('[data-role="label"]')[0];
                meta.data = data;
                label.value = meta.data[0].name;
            }

        },
        destroy: function () {

            var meta = mapper.get(this);

            $(meta.container).undelegate();
            meta.data = [];
        },
        clearData: function () {
            //新增文本清空设置空数据
            var f7DefaultData = [{
                ID: "",
                number: "",
                name: ""
            }];
            this.setData(f7DefaultData);
        }
    };

    //静态方法
    return $.Object.extend(DataSelector, {
        create: function (config) {
            // if (!!config.defaults) {
            // $.Object.extend(defaults, config.defaults);
            // defaults = $.Object.extend(defaults, config.defaults);
            // }
            var selector = new DataSelector(config);
            selector.render();
            return selector;

        },
        on: emitter.on.bind(emitter)
    });
});
