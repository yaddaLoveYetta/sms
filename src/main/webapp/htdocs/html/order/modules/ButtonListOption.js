/**
 * Created by yadda on 2017/5/6.
 */

define('ButtonListOption', function (require, module, exports) {

    var config = {'items': []};

    function get(classId) {

        if (classId == 2019) {
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
        } else if (classId == 2020) {
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
        } else {

            config = {
                'items': []
            };

        }

        return config;
    }

    return {
        get: get,
    }
});
