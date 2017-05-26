/**
 * Created by yadda on 2017/5/6.
 */

define('ButtonListOption', function (require, module, exports) {

    var config = {'items': []};

    function get(classId) {

        switch (parseInt(classId)) {

            case 2019:
                // 采购订单
                config = {
                    'items': [
                        {
                            text: '接单',
                            name: 'tick',
                        },
                        {
                            text: '刷新',
                            name: 'refresh',
                        },
                        {
                            text: '详情',
                            name: 'detail',
                        },
                        {
                            text: '生成发货单',
                            name: 'deliver',
                        }
                    ]
                };
                break;
            case 2020:
                // 发货单
                config = {
                    'items': [
                        {
                            text: '刷新',
                            name: 'refresh',
                        },
                        {
                            text: '详情',
                            name: 'detail',
                        },
                    ]
                };
                break;
            default:
                config = {
                    'items': []
                };
                break;
        }

        return config;
    }

    return {
        get: get,
    }
});
