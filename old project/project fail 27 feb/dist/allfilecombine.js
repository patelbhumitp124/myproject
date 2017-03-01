(function () {


    angular.module("bitblogger",["register", "login", "header"]);

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
(function(){

    angular.module("login",[]);

    angular.module("login").config([function(){

        console.log("Config: Login.js");
        
    }])
})();
(function(){

    angular.module("login").controller("loginCtrl", [loginCtrlFn]);

    function loginCtrlFn(){
        
    }
})();
(function(){

    angular.module("register",[]);

    angular.module("register").config([function(){

        console.log("Config: register.js");
        
    }])
})();
(function(){

    angular.module("register").controller("registerCtrl", [registerCtrlFn]);

    function registerCtrlFn(){
        
    }
})();