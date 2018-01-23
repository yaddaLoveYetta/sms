/**
 * Created by yadda on 2017/9/18.
 */
;(function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var API = SMS.require('API');
    var Iframe = SMS.require('Iframe');
    var bl = require('ButtonList');

    var Iframes = require('Iframes');
    var Header = require('Header');
    var Operation = require('Operation');

    var activedIndex = 0; // 当前显示的附件下标

    var dialog = Iframe.getDialog();

    var classId;
    var showItems = [];

    if (dialog) {

        var data = dialog.getData() || {};
        showItems = data.showItems;
        classId = data.classId;
        console.log(showItems);
    }

    showItems = arrangeShowItems(showItems);

    if (showItems.length === 0) {
        return;
    }

    /**
     * 将每个可显示的附件整理成list
     */
    function arrangeShowItems(items) {

        var list = [];
        var item;
        var entryList;
        var entryItem;
        var fileName;

        /*        var api = new API("file/download");
                var url = api.getUrl();*/

        // url参数中有完整回调地址直接被院方防火墙拦截……
        // 改为接口地址
        var url = getProjectPath() + "/file/download";

        for (var i = 0; i < items.length; i++) {
            item = items[i];
            entryList = item.entry['1'];
            for (var j = 0; j < entryList.length; j++) {
                entryItem = entryList[j];
                fileName = entryItem.url;

                url = $.Url.addQueryString(url, {
                    classId: classId,
                    itemId: item.id,
                    fileName: fileName,
                });

                if (fileName && $.String.endsWith(fileName, '.pdf', true)) {
                    // pdf
                    list.push({
                        'id': item.id || '',
                        'idNumber': item.idNumber || '',
                        'name': item.name || '',
                        'type': item.type_DspName || '',
                        'beginDate': item.beginDate || '',
                        'endDate': item.endDate || '',
                        'entryId': entryItem.entryId,
                        'parent': entryItem.parent,
                        'fileName': entryItem.url,
                        'check': entryItem.check,
                        'url': url,
                        'src': "../../../lib/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),
                    });
                } else if (fileName && ( $.String.endsWith(fileName, '.jpg', true) || $.String.endsWith(fileName, '.jpeg', true) || $.String.endsWith(fileName, '.png', true) || $.String.endsWith(fileName, '.gif', true)  )) {
                    // pic
                    list.push({
                        'id': item.id || '',
                        'idNumber': item.idNumber || '',
                        'name': item.name || '',
                        'type': item.type_DspName || '',
                        'beginDate': item.beginDate || '',
                        'endDate': item.endDate || '',
                        'entryId': entryItem.entryId,
                        'parent': entryItem.parent,
                        'fileName': entryItem.url,
                        'check': entryItem.check,
                        'url': url,
                        'src': "picture-view/index.html?file=" + encodeURIComponent(url),
                    });
                }

            }
        }

        return list;

        function getProjectPath() {
            var curWwwPath = window.document.location.href;
            // 获取主机地址之后的目录，如： htdocs/html/meun.jsp
            var pathName = window.document.location.pathname;
            var pos = curWwwPath.indexOf(pathName);
            // 获取主机地址，如： http://localhost:8080
            var localhostPaht = curWwwPath.substring(0, pos);
            // 获取带"/"的项目名，如：/sms
            return  pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        }
    }

    var blConfig = {
        'items': [
            {
                text: '通过',
                name: 'check',
                icon: '../../../css/main/img/check.png',
            },
            {
                text: '不通过',
                name: 'uncheck',
                icon: '../../../css/main/img/uncheck.png',
            },
            {
                text: '上一个',
                name: 'previous',
                icon: '../../../css/main/img/previous.png',
            },
            {
                text: '下一个',
                name: 'next',
                icon: '../../../css/main/img/next.png',
            }
        ]
    };

    var ButtonList = bl.create(blConfig);

    ButtonList.render();

    Iframes.render();

    ButtonList.on('click', {
        'previous': function () {
            var item = getItem(-1);
            Iframes.add(item);
            Header.render(item);
        },
        'next': function () {
            var item = getItem(1);
            Iframes.add(item);
            Header.render(item);
        },
        'check': function () {
            var item = getItem();
            Operation.check({
                'classId': classId,
                'id': item.id,
                'entryId': item.entryId,
                'type': 1,
            }, function () {
                item.check = 1;
                Header.render(item);
                SMS.Tips.success('操作成功！', 500);

                setTimeout(function () {
                    var item_next = getItem(1);
                    if (item_next === item) {
                        return;
                    }
                    Iframes.add(item_next);
                    Header.render(item_next);
                }, 500)
            });

        },
        'uncheck': function () {
            var item = getItem();
            Operation.check({
                'classId': classId,
                'id': item.id,
                'entryId': item.entryId,
                'type': 0,
            }, function () {
                item.check = 0;
                Header.render(item);
                SMS.Tips.success('操作成功！', 500);

                setTimeout(function () {
                    var item_next = getItem(1);
                    if (item_next === item) {
                        return;
                    }
                    Iframes.add(item_next);
                    Header.render(item_next);
                }, 500)
            });

        }
    });

    function getItem(step) {

        step = step || 0;

        activedIndex = activedIndex + step;

        if (activedIndex >= showItems.length) {
            SMS.Tips.success('我是有底线的……', 500);
            activedIndex = showItems.length - 1;
        }
        if (activedIndex < 0) {
            SMS.Tips.success('到头了,不要再顶了……', 500);
            activedIndex = 0;
        }

        return showItems[activedIndex];
    }

    Header.render(showItems[0]);
    Iframes.add(showItems[0]); // 默认显示第一个附件


})();