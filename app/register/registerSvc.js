(function(){

    function registerSvcFn($http){

        var vm = this;
        vm.getCountriesList = function(){

            return $http.get("api/countries.json");

        };

    }

    angular.module("register")
    .service("registerSvc",["$http", registerSvcFn]);


})();