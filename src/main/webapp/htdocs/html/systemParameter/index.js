

; (function () {
    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var SMS = require('SMS');


    var div = document.getElementById('div-imgs');
    var samples = $.String.getTemplates(div.innerHTML, [{
        name: 'img',
        begin: '<!--',
        end: '-->'
    }]);

    function render() {
        var list = getQueryString();
        div.innerHTML = $.Array.keep(list, function (item, index) {
            return $.String.format(samples.img, {
                'name': getQueryStringByIndex(index)
            });
        }).join('');
        
    }

    function getQueryString() {

        var result = location.search.match(new RegExp("[\?\&][^\?\&]+=[^\?\&]+", "g"));

        if (result == null) {

            return "";

        }

        for (var i = 0; i < result.length; i++) {

            result[i] = result[i].substring(1);

        }

        return result;

    }

    function getQueryStringByIndex(index) {

        if (index == null) {

            return "";

        }

        var queryStringList = getQueryString();

        if (index >= queryStringList.length) {

            return "";

        }

        var result = queryStringList[index];

        var startIndex = result.indexOf("=") + 1;

        result = result.substring(startIndex);

        return result;

    }

    render();

    $('[data-role="img"]').each(function(){
        var img = this;
        img.width = window.innerWidth;
    });

    
})();