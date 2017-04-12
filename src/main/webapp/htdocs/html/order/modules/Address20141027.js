








var Address = (function ($, MiniQuery, KERP) {

    var data = [
        {
            areaID: 110000, name: '北京', type: 1, children: [
              { areaID: 110100, name: '北京市', type: 2 }
            ]
        },
        {
            areaID: 440000, name: '广东省', type: 1, children: [
            {
                areaID: 440100, name: '广州市', type: 2, children: [
                    { areaID: 440103, name: '荔湾区', type: 3 },
                    { areaID: 440106, name: '天河区', type: 3 }
                ]
            },
            {
                areaID: 440300, name: '深圳市', type: 2, children: [
                    {
                        areaID: 440303, name: '罗湖区', type: 3
                    },
                    {
                        areaID: 440304, name: '福田区', type: 3
                    }
                ]
            },
            ]
        },
        {
            areaID: 430000, name: '湖南省', type: 1, children: [
                {
                    areaID: 430100, name: '长沙市', type: 2
                },
                {
                    areaID: 430300, name: '湘潭市', type: 2
                },
            ]
        }

    ];

    var wrapper = document.getElementById('order-list');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "address",
            begin: "#--address.begin--#",
            end: "#--address.end--#"
        },
        {
            name: "address-option",
            begin: "#--address-option.begin--#",
            end: "#--address-option.end--#",
            outer: ''
        }
    ]);

    function bindEvents(ids) {


        $('#' + ids.id).delegate('#' + ids.pId, 'change', function () {

            var province = this;

            load(function (AddressData) {

                var options = fill(AddressData, province.value);
                var childrenOptions = fill(options.children);

                document.getElementById(ids.id).innerHTML = $.String.format(samples['address'], {
                    'province': ids.pId,
                    'city': ids.cId,
                    'district': ids.dId,
                    'province-class': '',
                    'city-class': options.children.length > 0 ? '' : 'not-selected',
                    'district-class': 'not-selected',
                    'province-option': options.html,
                    'city-option': childrenOptions.html,
                    'district-option': ''
                });
            });
        });

        $('#' + ids.id).delegate('#' + ids.cId, 'change', function () {

            var province = document.getElementById(ids.pId);
            var city = this;

            load(function (AddressData) {
                
                var options = fill(AddressData, province.value);
                var childrenOptions = fill(options.children, city.value);
                var gcOptions = fill(childrenOptions.children);

                document.getElementById(ids.id).innerHTML = $.String.format(samples['address'], {
                    'province': ids.pId,
                    'city': ids.cId,
                    'district': ids.dId,
                    'province-class': '',
                    'city-class':  '',
                    'district-class': childrenOptions.children.length > 0 ? '' : 'not-selected',
                    'province-option': options.html,
                    'city-option': childrenOptions.html,
                    'district-option': gcOptions.html
                });
            });
        });

        $('#' + ids.id).delegate('#' + ids.dId, 'change', function () {

            var province = document.getElementById(ids.pId);
            var city = document.getElementById(ids.cId);
            var district = this;

            load(function (AddressData) {

                var options = fill(AddressData, province.value);
                var childrenOptions = fill(options.children, city.value);
                var gcOptions = fill(childrenOptions.children, district.value);

                document.getElementById(ids.id).innerHTML = $.String.format(samples['address'], {
                    'province': ids.pId,
                    'city': ids.cId,
                    'district': ids.dId,
                    'province-class': '',
                    'city-class': '',
                    'district-class': childrenOptions.children.length > 0 ? '' : 'not-selected',
                    'province-option': options.html,
                    'city-option': childrenOptions.html,
                    'district-option': gcOptions.html
                });
            });
        });
    }

    //type 0：省 1：市 2：区
    function fill(list, areaID) {

        var children = [];

        var html = $.Array.keep(list, function (item) {

            var selected = '';

            if (item.areaID == areaID) {

                
                children = item.children;
                
                selected = 'selected';
            }

            return $.String.format(samples['address-option'], {
                        value: item.areaID,
                        name: item.name,
                        selected: selected
                    });

        }).join('');

        return {
            children: children,
            html: html
        }
    }

    var addressData;


    function load(fn) {


        if (addressData) {
            fn && fn(addressData);
            return;
        }


        $.Script.load('../../data/address.array.simple.js', function () {

            var data = window['__AddressData__'];
            var list = convert(data);
            console.log(list);

            addressData = list;
            fn && fn(addressData);

        });

    }

    

    function convert(list) {

        return $.Array.keep(list, function (item) {

            return {
                "areaID": item[0],
                "name": item[1],
                "children": item.length > 2 ? convert(item[2]) : []

            }

        });
        
    }

    function create(obj) {


        load(function (AddressData) {

            var args = $.extend({}, {
                'id': null,
                'province': null,
                'city': null,
                'district': null

            }, obj);

            var provinceOptions = fill(AddressData, args.province);
            var cityOptions = fill(provinceOptions.children, args.city);
            var districtOptions = fill(cityOptions.children, args.district);

            if (args.id) {

                var pId = 'select-' + $.String.random().toLowerCase();
                var cId = 'select-' + $.String.random().toLowerCase();
                var dId = 'select-' + $.String.random().toLowerCase();

                document.getElementById(args.id).innerHTML = $.String.format(samples['address'], {
                    'province': pId,
                    'city': cId,
                    'district': dId,
                    'province-class': '',
                    'city-class': args.city ? '' : 'not-selected',
                    'district-class': args.district ? '' : 'not-selected',
                    'province-option': provinceOptions.html,
                    'city-option': cityOptions.html,
                    'district-option': districtOptions.html
                });

                var ids = {
                    id: args.id,
                    pId: pId,
                    cId: cId,
                    dId: dId
                };
                bindEvents(ids);

            }

        });


        
        

    }

    return {
        create: create
    }
    



})(jQuery, MiniQuery, KERP);