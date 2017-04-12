

var all = {

    click: [
        {
            fn: function () { },
            isOnce: false,
        },
        {
            fn: function () { },
            isOnce: false,
        },
    ],



    click: {
        add: {
            name: [],
        },
    },





};


var emitter = MiniQuery.Event.create();

emitter.on('click', 'add', 'name', function () {

});


var json = {

    spGroupID: 2,
    spGroupID_DspName: "仓位组SP02",
    spGroupID_NmbName: "SP02",
    
};


var json = {

    spGroupID: {
        id: 2,
        name: "仓位组SP02",
        NmbName: "SP02",
    },
};

