/**
 * Created by yadda on 2017/5/8.
 */

define('Tree', function (require, module, exports) {

    var $ = require('$');
    var SMS = require("SMS");
    var MiniQuery = require('MiniQuery');
    var API = SMS.require('API');
    var SupplierPager = require('SupplierPager');

    var emitter = MiniQuery.Event.create();
    var container = document.getElementById('tree');

    var formClassId;
    var tree = {};
    //默认配置
    var defaults = {
        pageSize: 15,
        pageNo: 1,
    };

    function load(config, fn) {

        var api = new API('template/getItems');

        var params = {
            'classId': config.classId,
            'pageNo': config.pageNo,
            'pageSize': config.pageSize,
            'condition': config.conditions.length > 0 ? conditions : '',
        };

        api.post(params);

        api.on({
            'success': function (data, json) {
                fn && fn(data, config.pageSize);
            },

            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                SMS.Tips.error(s);
            },

            'error': function () {
                SMS.Tips.error('网络繁忙，请稍候再试');
            }
        });
    };

    function render(classId) {

        formClassId = classId;

        SMS.Tips.loading("数据加载中...");
        load({
            classId: classId,
            pageNo: 1,
            pageSize: defaults.pageSize,
            conditions: [],
        }, function (data, pageSize) {
            SMS.Tips.success("数据加载成功", 1500);
            buildTree(data.list || []);
            SupplierPager.render({
                size: pageSize,
                total: data.count,
                change: function (no) {
                    load({
                        classId: classId,
                        pageNo: no,
                        pageSize: defaults.pageSize,
                        conditions: [],
                    }, function (data, pageSize) {
                        console.log(data.list);
                        buildTree(data.list || []);
                    });
                }
            });
        });
    };

    function buildTree(treeData) {

        if (formClassId === 1005) {
            // 供应商
            treeData = $.Array.keep(treeData, function (item, index) {
                return {
                    id: item.id,
                    pid: 0,
                    name: item.name || '',
                }
            });
        } else if (formClassId === 3030) {
            // 中标库
            treeData = $.Array.keep(treeData, function (item, index) {
                return {
                    id: item.item,
                    pid: 0,
                    name: item.item_DspName || '',
                }
            });

            // 中标库中可能一个物料有多个供应商，tree中只显示物料名称，可能会显示多行一样的物料（背后关联的供应商不同）
            treeData = $.Array.distinct(treeData, function (i1, i2) {
                return i1.id === i2.id && i1.name === i2.name
            });
        }

        SMS.use('ZTree', function (zTree) {

            tree = new zTree({
                selector: '#tree',
                data: treeData,
            });
            tree.on({

                onClick: function (event, treeId, treeNode) {
                    console.log(treeNode.id + ", " + treeNode.name);
                    emitter.fire("onClick", [treeNode]);
                },
            });

            if (treeData.length > 0) {

                var node = tree.getNodeByParam('id', treeData[0].id, null);//获取第一个
                tree.selectNode(node);//选择点
                tree.getTrueZTree().setting.callback.onClick(null, tree.getTrueZTree().setting.treeId, node);//调用事件

            }
        });
    }

    /**
     * 获取当前选中的节点
     */
    function getSelectedNodes() {
        return tree.getSelectedNodes();
    }

    return {
        render: render,
        getSelectedNodes: getSelectedNodes,
        on: emitter.on.bind(emitter),
    };
});
