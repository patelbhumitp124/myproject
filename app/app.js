(function () {


    angular.module("bitblogger", ["register", "login", "header"]);

    angular.module("bitblogger").config([function () {
        console.log("Config: bitblogger");
    }]);
    
    angular.module("bitblogger").controller("mainCtrl", [mainCtrlFn as mc]);

    function mainCtrlFn(){
        var vm = this;
        vm.headerTemplate = "app/header/header.tpl.html";
    }
    
})();