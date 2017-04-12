

/**
* 分类导航树的数据模块
* [0, 1, 1]
*/
define('TreeData', {
    name: '全部', // level = 0
    id: 0,
    items: [

        {
            name: '江西省',
            id: 1000,
            items: [
                {
                    name: '南昌市',
                    id: 1010,
                    items: [
                        { name: '东湖区', id: 1011, },
                        { name: '西湖区', id: 1012, },
                        { name: '青云谱区', id: 1013, },
                    ],
                },
                {
                    name: '吉安市',
                    id: 1020,
                    items: [
                        { name: '吉州区', id: 1021, },
                        { name: '青原区', id: 1022, },
                        { name: '吉安县', id: 1023, },
                        { name: '峡江县', id: 1024, },
                    ],
                },
            ]
        },
        {
            name: '广东省', // level = 1
            id: 2000,
            items: [
                {
                    name: '广州市',
                    id: 2010,
                    items: [
                        { name: '天河区', id: 2011, },
                        { name: '白云区', id: 2012, },
                        { name: '黄埔区', id: 2013, },
                        { name: '番禺区', id: 2014, },
                        { name: '花都区', id: 2015, },
                        { name: '南沙区', id: 2016, },
                        { name: '萝岗区', id: 2017, },
                    ],
                },
                {
                    name: '深圳市', // level = 2
                    id: 2020,
                    items: [
                        { name: '南山区', id: 2021, }, // level = 3
                        { name: '宝安区', id: 2022, },
                        { name: '福田区', id: 2023, },
                        { name: '罗湖区', id: 2024, },
                        { name: '龙岗区', id: 2025, },
                    ],
                },
                {
                    name: '茂名市', // level = 2
                    id: 2030,
                    items: [
                        { name: '电白县', id: 2031, }, // level = 3
                        { name: '高州市', id: 2032, },
                        { name: '茂南区', id: 2033, },
                        { name: '信宜市', id: 2034, },
                        { name: '化州市', id: 2035, },
                    ],
                },
            ]
        },
        {
            name: '湖南省',
            id: 3000,
            items: [
                {
                    name: '长沙市',
                    id: 3010,
                },
                {
                    name: '株洲市',
                    id: 3020,
                },
                {
                    name: '湘潭市',
                    id: 3030,
                },
            ]
        },
    ]
});






    