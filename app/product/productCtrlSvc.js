(function () {


    function productCtrlFn(productSvc, $scope){
        var vm = this;
        console.log("Controller: product");

        productSvc.getProducts()
        .then(function(response){
            console.log(response);
            //vm.products = response;
            $scope.products = response;
        })
        .catch(function(error){
            console.log("error" +error);
        });
        
        
    }

    function productSvcFn($http,$q){
        var vm = this;
        vm.products = [];
        vm.getProducts = function(){

            var dfd = $q.defer();

            $http.get("api/products.json")
            .then(function(response){
               
                dfd.resolve(response.data.products);
            })
            .catch(function(response){
                dfd.reject(response);
            });

            return dfd.promise;
        };
    }

    angular.module("product")
        .controller("productCtrl", ["productSvc", "$scope", productCtrlFn])
        .service("productSvc",["$http", "$q", productSvcFn]);

})();