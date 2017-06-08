/**
 * 上传图片控件模块
 *
 */
define('Upload', function (require, module, exports) {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');
    var Url = SMS.require('Url');
    var url = Url.uploadFileUrlRoot();

    var API = SMS.require("API");

    var btn = $('#sel');

    var classId;
    var itemId;

    //销毁Uploadify实例并将文件上传按钮恢复到原始状态
    function refrshUploadify() {
        btn.uploadify('destroy');
        render();
    }

    function render(opts) {

        if (opts) {
            classId = opts.classId;
            itemId = opts.id;
        }
        var api = new API("file/upload");

        btn.uploadify({

            swf: '../../../lib/uploadify/uploadify.swf',
            uploader: api.getUrl(),
            buttonClass: 'sms-btn-item',
            buttonText: '选择附件',
            multi: false,
            uploadLimit: 2,
            auto: false,
            width: 80,
            height: 25,
            fileSizeLimit: "5MB",
            fileTypeDesc: '图片文件',
            fileTypeExts: '*.gif; *.jpg; *.png',
            method: 'Post',
            removeTimeout: 1,
            queueID: "fileQueue",
            overrideEvents: ['onSelectError', 'onDialogClose'],
            onUploadStart: function (file) {
                btn.uploadify("settings", "formData", {
                    classId: classId,
                    itemId: itemId,
                });
            },
            onSelectError: function (file, errorCode, errorMsg) {
                switch (errorCode) {
                    case -100:
                        SMS.Tips.error('上传文件数量已超过系统限制的' + btn.uploadify('settings,', 'uploadLimit') + '个文件', 1500);
                        break;
                    case -110:
                        SMS.Tips.error("附件 [" + file.name + "] 大小超出系统限制的5M大小！", 1500);
                        break;
                    case -120:
                        SMS.Tips.error("附件 [" + file.name + "] 大小异常！", 1500);
                        break;
                    case -130:
                        SMS.Tips.error("附件 [" + file.name + "] 类型不正确！", 1500);
                        break;
                }
                return false;
            },
            onUploadSuccess: function (fileObj, responseData, response) {

                var data = $.Object.parseJson(responseData)

                if (data) {
                    if (data.code == 200) {
                        SMS.Tips.success(data.msg, 2000);
                    } else {
                        SMS.Tips.error(data.msg, 2000);
                    }
                }
                refrshUploadify();
            },
            onUploadError: function () {

                if (arguments[3] != "Cancelled") {
                    SMS.Tips.error("系统繁忙,请稍候再试!", 2000);
                }
                refrshUploadify();
            },
            onFallback: function () {
                // 检测flash失败
                SMS.Tips.error("您未安装FLASH控件，无法上传，请安装FLASH控件后重试!", 2000);
            }
        });

    }

    $('#upload').on('click', function () {
        btn.uploadify('upload', '*')
    });

    return {
        render: render,
    }
});