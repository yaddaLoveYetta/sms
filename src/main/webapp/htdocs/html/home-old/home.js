


; (function ($, MiniQuery, KERP, Todo, Iframes) {


    //检查登录 
    //if (!KERP.Login.check(true)) {
    //    return;
    //}

    Todo.render();



    $(document).on('dblclick', function () {

        

        KERP.Login.show();

 
    });




})(jQuery, MiniQuery, KERP, Todo, Iframes);
