(function () {


    function productCtrlFn(){
        
        console.log("Controller: product");
        
    }

    angular.module("product")
        .controller("productCtrl", [productCtrlFn]);

})();