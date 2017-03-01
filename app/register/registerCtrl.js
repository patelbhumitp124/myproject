(function () {


    function registerCtrlFn(){
        
        console.log("Controller: register");
        
    }

    angular.module("register")
        .controller("registerCtrl", [registerCtrlFn]);

})();