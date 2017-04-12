

define('Logistics', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    var ecClass = 'couriers-selected';

    var wrapper = document.getElementById('div-tab-0');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "express-company",
            begin: "#--express-company.begin--#",
            end: "#--express-company.end--#"
        }
    ]);

    var data = [
        {
            name: '顺丰速递',
            ecValue: 47
        },
        {
            name: '圆通速递',
            ecValue: 23
        },
        {
            name: '中通快递',
            ecValue: 27
        },
        {
            name: '韵达快递',
            ecValue: 24
        },
        {
            name: '申通快递',
            ecValue: 30
        },
        {
            name: '联邦快递',
            ecValue: 31
        },
        {
            name: '百世汇通',
            ecValue: 33
        },
        {
            name: '京东快递',
            ecValue: 25
        }
    ];

    function bindEvents(obj) {

        $(wrapper).delegate('li', 'click', function () {

            var args = {
                "id": obj.id,
                'ecValue': this.getAttribute('data-type')
            };

            fill(args);
        });

    }
    
    function fill(obj) {
        
        document.getElementById('div-tab-0').innerHTML = $.Array.keep(data, function (item) {
            return $.String.format(samples['express-company'], {
                'data-type': item.ecValue,
                'ec-class': obj && item.ecValue == obj.ecValue ? ecClass : '',
                'name': item.name
            });
        }).join('');
    }

    function render(obj) {

        fill(obj);

        bindEvents(obj);
    }

    return {
        render: render
    }

});