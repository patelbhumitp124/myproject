(function () {

    function headerCtrlFn() {

        var vm = this;

        vm.navItems = [
            { "key": "home", "value": "HOME" },
            { "key": "product", "value": "PRODUCT" },
            { "key": "register", "value": "REGISTER" },
            { "key": "login", "value": "LOGIN" },
            { "key": "photo", "value": "PHOTO" }];
    }
    
    angular.module("header").controller("headerCtrl", [headerCtrlFn]);
    
})();