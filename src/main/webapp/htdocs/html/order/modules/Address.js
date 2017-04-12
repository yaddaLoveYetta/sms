




define('Address', function (require, exports, module) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');


    var picker = null;

    function create(config) {


        picker = KERP.CascadePicker.create({
            container: '#' + config.id,
            selectedValues: [config.province, config.city, config.district],
            defaultTexts: ['--请选择省份--', '--请选择城市--', '--请选择地区--'],
            hideEmpty: true,
	    data: '/data/address.array.simple.js',
            varname: '__AddressData__',
            fields: {
                value: 0,
                text: 1,
                child: 2,
            },
            change: function (level, index) {
                console.log(level, index);
                var items = this.getSelectedItems();
                console.dir(items);
            }
        });


    }

    function getSelectedItems() {
        return picker ? picker.getSelectedItems() : [];
    }

    return {
        create: create,
        getSelectedItems: getSelectedItems
    }



});

