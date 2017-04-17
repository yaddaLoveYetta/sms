

define('DataSelector', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Samples = SMS.require('Samples');

    var mapper = new $.Mapper();
    var guidKey = $.Mapper.getGuidKey();

    var defaults = {
        multiSelect: false,
        width: 700,
        height: 450,
        pageSize: 10,
        targetList: {
            1: './html/warehouse/index.html',
            2: './html/support-data/index.html'
        }
    };

    var samples = Samples.get('DataSelector', [
        {
            name: 'selector',
            begin: '#--selector.begin--#',
            end: '#--selector.end--#'
        }
    ]);


    //构造函数
    function DataSelector(config){

        this[guidKey] = 'DataSelector-' + $.String.random();


        var meta = {
            container: config.container,
            typeID: config.typeID,
            hasBreadcrumbs: config.hasBreadcrumbs,
            targetType: config.targetType,
            title: config.title,
            classID: config.classID || '',
            data: [
            {
                'ID': '',
                'name': '',
                'number': ''
            }
            ]
        };

        mapper.set(this, meta);

        meta.bindEvents = function (selector) {
            var meta = mapper.get(selector);
            $(meta.container).delegate('[data-role="btn"]', 'click', function () {

                var url = $.Url.setQueryString(defaults.targetList[meta.targetType], 'classID', meta.classID);

                SMS.use('Dialog', function (Dialog) {
                    var dialog = new Dialog({
                        title: meta.title,
                        url: url,
                        width: defaults.width,
                        height: defaults.height,

                        data: {
                            typeID: meta.typeID || '',
                            multiSelect: defaults.multiSelect,
                            pageSize: defaults.pageSize,
                            hasBreadcrumbs: meta.hasBreadcrumbs
                        },

                        ok: function () {
                            this.isSubmit = true;
                        },
                        cancel: function () {
                             console.log('canclel');
                        }
                    });

                    //默认关闭行为为不提交
                    dialog.isSubmit = false;

                    dialog.showModal();

                    dialog.on({
                        remove: function () {
                            var data = dialog.getData();
                            var label = $(meta.container).find('[data-role="label"]')[0];
                            if ( dialog.isSubmit && data[0].hasOwnProperty('ID')) {
                                meta.data = dialog.getData();
                                label.focus();
                            }
                        }
                    });
                });

            });

            $(meta.container).delegate('[data-role="label"]', {

                'focus': function () {
                    var self = this;
                    if (meta.data[0].number) {
                        self.value = meta.data[0].number;
                    }
                },
                'blur': function () {
                    var self = this;
                    if (meta.data[0].name) {
                        self.value = meta.data[0].name;
                    }
                }
            });
        };

    }


    //实例方法
    DataSelector.prototype = {
        constructor: DataSelector,
        getData: function () {

            var meta = mapper.get(this);
            return meta.data;

        }, render: function () {

            var meta = mapper.get(this);
            $(meta.container).html(samples.selector);
            meta.bindEvents(this);

        },
        setData: function (data) {
            
            var meta = mapper.get(this);
            var label = $(meta.container).find('[data-role="label"]')[0];
            meta.data = data;
            label.value = meta.data[0].name;

        },
        destroy: function () {

            var meta = mapper.get(this);

            $(meta.container).undelegate();
            meta.data = [];

            //mapper.remove(this);
        },
    };


    //静态方法
    return $.Object.extend(DataSelector, {
        create: function (config) {

            var selector = new DataSelector(config);
            selector.render();

            return selector;

        }
    });
});