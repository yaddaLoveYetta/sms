



var Header = (function ($, MiniQuery, KERP) {
    
    var div = document.getElementById('div-header');


    function render(data) {


        KERP.Template.fill(div, {
            'name-display': 'name' in data ? '' : 'display: none;',
            'tips-display': 'tips' in data ? '' : 'display: none;',
            'name': data.name,
            'tips': data.tips,
        });
    }


    return {
        render: render,
    };



})($, MiniQuery, KERP);