/**
 * Created by yadda on 2017/5/6.
 */

define('ButtonListOption', function (require, module, exports) {

    var config = {'items': []};

    function get(classId, type) {

        if (!type) {
            // 查看-不需要工具栏
            return config;
        }

        if (type === 1) {
            // 新增
            switch (classId) {
                case 2019:
                    //采购订单
                    config = {
                        'items': [{
                            text: '保存',
                            name: 'optSave',
                        }, {
                            text: '刷新',
                            name: 'optRefresh'
                        }]
                    };
                    break;
                case 2020:
                    //发货单
                    config = {
                        'items': [{
                            text: '保存',
                            name: 'optSave',
                        }, {
                            text: '刷新',
                            name: 'optRefresh'
                        }]
                    };
                    break;
            }

        }

        if (type === 2) {
            // 编辑

            switch (classId) {
                case 2019:
                    //采购订单
                    config = {
                        'items': [{
                            text: '保存',
                            name: 'optSave',
                        }, {
                            text: '刷新',
                            name: 'optRefresh'
                        }]
                    };
                    break;
                case 2020:
                    //发货单
                    config = {
                        'items': [{
                            text: '保存',
                            name: 'optSave',
                        }, {
                            text: '刷新',
                            name: 'optRefresh'
                        }]
                    };
                    break;
            }

        }


        return config;

    }

    return {
        get: get,
    }
});
