(function(){

    function registerSvcFn($http,$q){

        var vm = this;
        vm.countriesArray = [];
        vm.getCountriesList = function(){

            //return $http.get("api/countries.json");
            var dfd = $q.defer();
            $http.get("api/countries.json")
            .then(function(response){
                countriesArray = response.data.countriesList;
                dfd.resolve(countriesArray);
            })
            .catch(function(response){
                dfd.reject(response);
            });
            return dfd.promise;
        };

    }

    angular.module("register")
    .service("registerSvc",["$http", "$q", registerSvcFn]);


})();