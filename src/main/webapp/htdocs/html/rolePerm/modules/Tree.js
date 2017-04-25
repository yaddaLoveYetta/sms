﻿
define('Tree', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var YWTC = require('YWTC');

    var API = YWTC.require('API');

    var zTree = null;
    var oldPermit = [];

    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": 'ps', "N": 'ps' }
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId"
            }
        }, callback: {
            onCheck: zTreeOnCheck // checkbox / radio 被勾选 或 取消勾选的事件回调函数
        }
    };

    //节点选中事件
    function zTreeOnCheck(event, treeId, treeNode) {
        if (treeNode.isParent) {
            return;
        }
        var currAcMsk = +treeNode.faccessMask;
        var currFAccessUse = +treeNode.faccessUse;
        var parentNode = treeNode.getParentNode();
        var currSiblingsNode = parentNode.children;
        if (treeNode.checked) {
            $.Array.each(currSiblingsNode, function (node, index) {
                var nodelAcMsk = +node.faccessMask;
                if ((currFAccessUse & nodelAcMsk) == nodelAcMsk) {
                    zTree.checkNode(node, true, true);
                }
            });
        } else {
            $.Array.each(currSiblingsNode, function (node, index) {
                var nodeFAccessUse = +node.faccessUse;
                if ((currAcMsk | nodeFAccessUse) == currAcMsk) {
                    zTree.checkNode(node, false, true);
                }
            });
        }
    };

    function render(userId, userType, fn) {
        getUserPermit(userId, userType, function (data) {
            var treeData = [];
            $.Array.each(data, function (grp, index) {
                var parCheck = false;
                if (grp.FSubSys) {
                    $.Array.each(grp.FSubSys, function (item, index) {
                        var twoCheck = false;
                        if (item.FAccessTypes) {
                            $.Array.each(item.FAccessTypes, function (acc, index) {
                                if (acc.FEnable == 1) { //子节点选择，所属父节点也需要选中
                                    parCheck = true;
                                    twoCheck=true;
                                } 
                                var accData = {
                                    id: acc.FAccessIndex + 1,
                                    name: acc.FPermissionName,
                                    pId: item.FSubSysID,
                                    faccessMask: acc.FAccessMask,
                                    faccessUse: acc.FAccessUse,
                                    checked: (acc.FEnable == 1),
                                    open: true
                                };
                                treeData.push(accData);

                            });
                        }
                        var itemData = {
                            id: item.FSubSysID,
                            name: item.FSubSysName,
                            pId: grp.FTopClassID,
                            checked: twoCheck,
                            open: true
                        };
                        treeData.push(itemData);
                    });
                }
                var group = {
                    id: grp.FTopClassID,
                    name: grp.FTopClassName,
                    pId: 0,
                    checked: parCheck,
                    open: true
                };
                treeData.push(group);
            });
            $.fn.zTree.init($("#permitTree"), setting, treeData);
            zTree = $.fn.zTree.getZTreeObj("permitTree");
            fn && fn();
        });
    };


    function savePermit(user,fn) {
        if (!user) {
            return;
        }

        var roleId = user.FRoleID;
        var type=user.FUserType;
        var nodes = zTree.getCheckedNodes(true);
        var topNodes = $.Array.grep(nodes, function (item, index) {

            return item.level == 0;
        });
        var permitData = [];
        //循环顶级节点
        $.Array.each(topNodes, function (topNode, index) {
            console.log("-顶级节点-" + topNode.name);

            var accessMask = 0;
            if (topNode.children) {
                $.Array.each(topNode.children, function (tNode, index) { //二级节点
                    if (tNode.checked) {
                    	accessMask = 0;
                        var pData = {};
                        pData.FTopClassID = topNode.id; //顶级节点Id
                        pData.FSubSysID = tNode.id;//二级节点Id
                        console.log("-2级节点-" + tNode.name);
                        if (tNode.children) {
                            $.Array.each(tNode.children, function (cNode, index) { //三级节点
                                if (cNode.checked) {
                                    accessMask = accessMask | cNode.faccessMask;
                                    console.log("-3级节点-" + cNode.name);
                                }
                            });
                        }
                        pData.FAccessMask = accessMask;
                        permitData.push(pData);
                    }
                    //pData.FAccessMask = accessMask;
                    //permitData.push(pData);
                });
                //pData.FAccessMask = accessMask;
                //permitData.push(pData);
            }
        });
        //console.log(permitData);
        //保存权限成功后，重新保存当前权限
        setPermit(permitData, roleId, fn);
    }


    function getUserPermit(roleID, uType, fn) {
        var api = new API('role/getRolePermissions');
        api.get({
            roleID: roleID,
            type: uType
        });

        api.on({
            'success': function (data, json) {
                fn && fn(data);
            },
            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                YWTC.Tips.error(s);
            },
            'error': function () {
                YWTC.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    function setPermit(data, roleId, fn) {
        var api = new API('role/saveRolePerMissions');
        var postData = $.Object.toJson(data);

        api.post({
            RoleID: roleId,
            data: postData
        });

        api.on({
            'success': function (data, json) {
                fn && fn(data);
                YWTC.Tips.success('保存成功！');
            },
            'fail': function (code, msg, json) {
                var s = $.String.format('{0} (错误码: {1})', msg, code);
                YWTC.Tips.error(s);
            },
            'error': function () {
                YWTC.Tips.error('网络繁忙，请稍候再试');
            }
        });
    }

    return {
        render: render,
        savePermit: savePermit
    }

});