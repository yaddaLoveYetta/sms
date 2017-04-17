


//分类依据枚举数据
define('ClassList', function (require, module, exports) {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');

    var list = [
        {
            "number": "10103",
            "name": "部门"
        }, {
            "number": "10104",
            "name": "商品"
        }, {
            "number": "10107",
            "name": "仓库"
        }, {
            "number": "10108",
            "name": "货位"
        }, {
            "number": "10111",
            "name": "辅助属性"
        },
        {
            'number': '10105',
            'name': '职员'
        }
    ];

    return {
        list: list
    }

});
