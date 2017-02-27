(function () {


    angular.module("bitblogger",["register", "login", "header", "comments"]);

    angular.module("bitblogger").config([function () {
        console.log("Config: bitblogger");
    }]);
    
    angular.module("bitblogger").controller("mainCtrl", [mainCtrlFn]);

    function mainCtrlFn(){
        var vm = this;
        vm.headerTemplate = "app/header/header.tpl.html";
        vm.appName = "BITBLOGGER";
        vm.show = false;
        vm.showAppName = function(){
            vm.show = true;
        };

        vm.hideAppName = function(){
            vm.show = false;
        };
    }
    
})();