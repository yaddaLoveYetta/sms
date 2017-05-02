/**
 * Samples 模块
 * Created by yadda on 2017/5/2.
 */

define('Samples', function (require, module, exports) {

    var $ = require('$');

    function trim(s) {
        return s.replace(/\n/g, '').replace(/\s{2,}/g, ' ');
    }

    function get(div) {

        var samples = $.String.getTemplates(div.innerHTML, [
            {
                name: 'table',
                begin: '<!--',
                end: '-->',
                //fn: trim,
            },
            {
                name:"tr",
                begin:'#--tr.begin--#',
                end:"#--tr.end--#",
                outer:'{trs}',
            },
            {
                name: 'tr.checkbox',
                begin: '#--th.checkbox.begin--#',
                end: '#--th.checkbox.end--#',
                outer: '{checkbox}',
                //fn: trim,
            },
            {
                name: 'td.mustInput',
                begin: '#--td.mustInput.begin--#',
                end: '#--td.mustInput.end--#',
                outer: '{mustInput}',
                //fn: trim,
            },
            {
                name: 'tr.text',
                begin: '#--tr.text.begin--#',
                end: '#--tr.text.end--#',
                outer: '{text}',
                //fn: trim,
            },
            {
                name: 'tr.password',
                begin: ' #--tr.password.begin--#',
                end: ' #--tr.password.begin--#',
                outer: '{password}',
                //fn: trim,
            },
            {
                name: 'tr.f7',
                begin: '#--tr.f7.begin--#',
                end: '#--tr.f7.end--#',
                outer: '{f7}',
                //fn: trim,
            },
        ]);

        return samples;
    }



    return get;

});







