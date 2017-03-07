(function () {


    function headerCtrlFn($state) {

        console.log("Controller: header");

        var vm = this;
        vm.navItems = [
            { "key": "home", "value": "HOME" },
            { "key": "register", "value": "REGISTER" },
            { "key": "login", "value": "LOGIN" },
            { "key": "posts", "value": "POSTS" },
            { "key": "comments", "value": "COMMENTS" },
            { "key": "product", "value": "PRODUCT" },
            { "key": "photo", "value": "PHOTO" }];

            vm.changeState = function(data){
                console.log(data);
                $state.go(data);
            }
    }

    angular.module("header")
        .controller("headerCtrl", ["$state", headerCtrlFn]);

})();