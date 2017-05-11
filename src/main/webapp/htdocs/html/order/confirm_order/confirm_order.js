/**
 * Created by yadda on 2017/5/8.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var List = require('List');
    var Pager = require('Pager');
    var bl = require('ButtonList');
    var ButtonListOption = require('ButtonListOption');
    var MessageBox = SMS.require('MessageBox');
    var Tree = require('Tree');
    var ClassMapping = require('ClassMapping');

    var classId = MiniQuery.Url.getQueryString(window.location.href, 'classId');
    var txtSimpleSearch = document.getElementById('txt-simple-search');
    var conditions = {};

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    //默认配置
    var defaults = {
        pageSize: 10,
        typeId: '',
        pageNo: 1,
        hasBreadcrumbs: true,
        multiSelect: true
    };

    $(txtSimpleSearch).bind('keypress', function (event) {
        if (event.keyCode == 13) {
            /*conditions['name'] = $(txtSimpleSearch).val();*/
            refresh();
        }
    });


    var blConfig = {
        'items': [
            {
                text: '接单',
                name: 'tick',
            },
            {
                text: '删除',
                name: 'del',
            }
            ]
    };


    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    //支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {

        'tick': function (item, index) {
            var item = Tree.getSelectedNodes();
            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择接单项');
                return;
            }
            if(list.length > 1){
            	SMS.Tips.error('只能选择一条订单接单');
            	return;
            }
            pageInit();
        },
        'delete': function (item, index) {

            var list = List.getSelectedItems();

            if (list.length == 0) {
                SMS.Tips.error('请选择要删除的项');
                return;
            }
            if (confirm('确定删除选择的项')) {
                List.del(classId, list, function () {
                    refresh();
                });
            }
        },
        'refresh': function (item, index) {
            refresh();
        },

    });

    function refresh(data) {

        if (data) {
            conditions['id'] = {
                'andOr': 'AND',
                'leftParenTheses': '(',
                'fieldKey': 'supplier',
                'logicOperator': '=',
                'value': data.id,
                'rightParenTheses': ')',
                'needConvert': false,
            };
        }

        List.render({
            classId: classId,
            pageNo: 1,
            pageSize: defaults.pageSize,
            conditions: conditions,
            multiSelect: defaults.multiSelect
        }, function (total, pageSize) {

            Pager.render({
                size: pageSize,
                total: total,
                change: function (no) {
                    List.render({
                        classId: classId,
                        pageNo: no,
                        pageSize: defaults.pageSize,
                        conditions: conditions,
                        multiSelect: defaults.multiSelect
                    });
                }
            });
        });
    }


    function pageInit() {
    	$("#frmac").jqGrid({
    		url:"aaa",
    		datatype:"json",
    		colNames:["物料编码","物料名称","规格型号","计量单位"],
    		colModel:[{
    			name:'',
    			index:'',
    			label:'',
    			width:60,
    			align:'center'
    		},{
    			name:'',
    			index:'',
    			label:'',
    			width:60,
    			align:'center'
    		},{
    			name:'',
    			index:'',
    			label:'',
    			width:60,
    			align:'center'
    		},{
    			name:'',
    			index:'',
    			label:'',
    			width:60,
    			align:'center'
    		}],
    		sortname:"id",
    		sortorder:"desc",
    		viewrecords: true,
    		rownumbers: true,
    		autowidth: true,
    		jsonReader:{
    			repeatitems:false
    		}
    	});
    }

})();