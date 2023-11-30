var Login = function() {

    return {
        //main function to initiate the module
        init: function() {

            // init background slide images
            $('.login-bg').backstretch([
                     "assets/demo/sctreg/framex1.jpg",
                     "assets/demo/sctreg/framex2.jpg",
                     "assets/demo/sctreg/framex3.jpg",
                     "assets/demo/sctreg/framex4.jpg",
                     "assets/demo/sctreg/framex5.jpg",
                ], {
                  fade: 1000,
                  duration: 8000
                }
            );
        }

    };

}();

jQuery(document).ready(function() {
    Login.init();
});
