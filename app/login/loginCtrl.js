(function () {


    function loginCtrlFn(){
        
        console.log("Controller: login");
        
    }

    angular.module("login")
        .controller("loginCtrl", [loginCtrlFn]);

})();