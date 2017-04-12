


var Todo = (function ($, MiniQuery, KERP) {

    var wrapper = document.getElementById('div-todo');

    var samples = $.String.getTemplates(wrapper.innerHTML, [
        {
            name: "group",
            begin: "<!--",
            end: "-->",
            outer: "{group}"
        }
    ]);

    function fillTemplate(data) {
        //因目前接口未实现此字段，在此扩展默认值供页面跳转使用
        var list = $.Array.keep(data, function (item, index) {
            return $.extend({}, item, {
                'value': 0
            });
        });

        //设定缺省数据
        list.push({
            'status': '待审核', 'sum': 0, 'value': 0
        }, {
            'status': '待配货', 'sum': 0, 'value': 1
        }, {
            'status': '待发货', 'sum': 0, 'value': 2
        }, {
            'status': '待上传', 'sum': 0, 'value': 3
        });

        wrapper.innerHTML = $.String.format(samples['group'], {
            'sum-0': list[0].sum,
            'status-0': list[0].status,
            'value-0': 0,
            'sum-1': list[1].sum,
            'status-1': list[1].status,
            'value-1': 1,
            'sum-2': list[2].sum,
            'status-2': list[2].status,
            'value-2': 2,
            'sum-3': list[3].sum,
            'status-3': list[3].status,
            'value-3': 3,
            });
    }

    function load(fn) {
        KERP.API.get('eshop/order', {//请求服务器端数据

            action: 'sumOrderStatus'

        }, function (data, json) {//成功

            var list = data.items;

            list = $.Array.keep(list, function(item, index) {

                return {
                        index: index,
                        sum: item.sum,
                        status: item.status
                    }

            });

            fn && fn(list);

        }, function (code, msg, json) {//失败

            KERP.Tips.error(msg, 2000);

        }, function () {//错误

            KERP.Tips.error('网络错误，请稍候再试', 2000);
    });
}



    function render() {


        //延迟显示。 避免数据很快回来造成的只显示瞬间
        KERP.Tips.loading({
                text: '数据加载中，请稍后...',
                delay: 500
        });

        load(function (data) {
            fillTemplate(data);
            //KERP.Template.fill(wrapper, data);
            KERP.Tips.success('数据加载成功', 1000);

        })

        bindEvent();
    }

    var emitter = MiniQuery.Event.create();

    function bindEvent() {
        $('#div-todo').delegate('[data-action=link-order]', 'click', function () {
            
            var div = this;
            var index = +div.getAttribute('data-index');
            var item = list[index]; //取得数据

            emitter.fire('item.click', [item]);
            emitter.fire('item.click', [item]);

        });
        }

    return {
        render: render
        }



        }) (jQuery, MiniQuery, KERP);