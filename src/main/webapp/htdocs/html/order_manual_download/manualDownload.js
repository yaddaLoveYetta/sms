
(function (jQuery, MiniQuery, KERP, DownloadCriteriasData, Path, ShopData, Shop) {

    DownloadCriteriasData.loadPath(function (data) {
        Path.init(data);
    });

    ShopData.load(function (data) {
        Shop.render(data.items);
    });

})(jQuery, MiniQuery, KERP, DownloadCriteriasData, Path, ShopData, Shop);