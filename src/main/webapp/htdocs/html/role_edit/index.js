

; (function () {

    var $ = require('$');
    var MiniQuery = require('MiniQuery');
    var KERP = require('KERP');

    var ButtonList = require('ButtonList');
    var Edit = require('Edit');
    var Grid = require('Grid');

    var itemID = MiniQuery.Url.getQueryString(window.location.href, 'id');
    var formClassID = 10106;

    
    ButtonList.on({

        'optNew': function () {
            Edit.clear();
        },
        'optAddType': function(){
            Iframe.open(1, 6, {
                query: {

                }
            });
        },
        'optSave': function () {
            Edit.save();
        },
        'optRefresh': function () {
            Edit.refresh(formClassID, selectors);
        },
        'optDisable': function () {
            Edit.forbid(formClassID, 1);
        },
        'optUndisable': function () {
            Edit.forbid(formClassID, 0);
        },

    });


    ButtonList.render();
    Grid.render(formClassID);
    Edit.render(formClassID, itemID, function (entryData, metaData) {
        Grid.fill(entryData, metaData);
    });

})();