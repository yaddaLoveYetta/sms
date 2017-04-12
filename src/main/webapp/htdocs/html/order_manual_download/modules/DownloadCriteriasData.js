
define('DownloadCriteriasData', function() {

    var pathList = ['首页', '订单处理', '手工下载订单'];

    function loadPath(fn) {

        fn(pathList);
    }

    return {
        loadPath: loadPath

    }

});