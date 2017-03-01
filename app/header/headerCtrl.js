(function () {


    function headerCtrlFn(){
        
        console.log("Controller: header");
        
    }

    angular.module("header")
        .controller("headerCtrl", [headerCtrlFn]);

})();