/**
 * Created by yadda on 2017/6/1.
 */
;(function () {


    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var ButtonList = require('ButtonList');
    var Iframe = SMS.require('Iframe');

    var BarCode = require('BarCode');

    var code = [];// 条形码字符内容

    //检查登录
    if (!SMS.Login.check(true)) {
        return;
    }

    var dialog = Iframe.getDialog();

    if (dialog) {

        var data = dialog.getData();

        code = data.code;

    } else {
        return;
    }

    // 支持二级事件，二级事件对应 item 中的 name
    ButtonList.on('click', {
        'optPrint': function () {
            /*            $("#code").jqprint({
                            debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
                            importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
                            printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）默认是true。。
                            operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
                        });*/


            var html = $.Array.keep(document.getElementsByClassName('item'), function (item, index) {
                return item.outerHTML;
            });

            var myDoc = {
                ui: "design", // 进入设计模式
                dragDesigner: {viewSource: false},  // 必须设置,
                //fitToPage: true,   //必要时缩放打印

                //第一次打印时，注册表中没有‘mysettings2’的打印参数
                //所以使用上面指定的参数
                //打印后，控件自动将最后一次打印设置保存
                //后续打印时将用注册表中的设置，来自动配置打印机
                //上述的settings将被忽略
                //settingsID: "mySettings2",
                //print_settings
                settingsID: 'mydoc2',    // 会在注册表中记录该布局样式
                settings: {
                    // 如果想使用默认打印机,不需要设置
                    printer: 'Microsoft Print to PDF',
                    copies: 1,  	//打印份数
                    //paperName:'a4',
                    // 指定纸张的高宽以毫米为单位z,本设置实际是指定为a4大小
                    /*       pageWidth: 2100,
                           pageHeight: 2970,*/
                    paperWidth: '60mm',
                    paperHeight: '30mm',
                    orientationMixed: true,   // 此属性通知控件，存在有纵有横的打印页面
                    orientation: 2,// 指定打打印方向为横向, 1/2 = 纵向/横向
                },
                documents: {
                    //当只有一页时，可以直接以字符串指定，如：
                    //documents: {html:'<span>我是老大！</span>'},
                    html: html, // 用 html 指定每页的html，数组，每个元素表示一页
                    //style: "span#second{font-size:150%;}" // 可以用 style指定所有页面的 css
                    // style:['../../css/code-print/index.debug.css'],
                },
                importedStyle: ['../../css/code-print/index.debug.css'],

                copyrights: '杰创软件拥有版权  www.jatools.com' // 版权声明必须
            };
            myDoc.done = function (err) {
                alert('print over');
                // 关闭打印窗口
                window.close();
            }
            var jcp = getJCP();
            jcp.printPreview(myDoc); // 打印预览，显示进度条

            // 弹出对话框打
            //var jatoolsPrinter = document.getElementById("jatoolsPrinter");
            //jatoolsPrinter.print(myDoc,true);

            //getJatoolsPrinter().printPreview(myDoc,true);
        },
        'optPrintSelect': function () {
            // 选择打印机
            // 内部函数
            SMS.use('Dialog', function (Dialog) {

                var dialog = new Dialog({
                    title: '打印机设置',
                    width: 350,
                    height: 400,
                    url: $.Url.setQueryString('html/code-print/printer-select/index.html'),

                });

                //默认关闭行为为不提交
                dialog.isSubmit = false;

                dialog.showModal();

                dialog.on({
                    remove: function () {
                        //refresh();
                    }
                });

            });
        },
        'optRefresh': function () {
            alert('optRefresh');
        }
    });

    ButtonList.render();
    BarCode.render(code);

})();