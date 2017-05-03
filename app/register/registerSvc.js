(function () {

    function registerSvcFn($http, $q) {

        var vm = this;
        vm.countriesList = [];
        vm.getCountriesList = function () {

            //return $http.get("api/countries.json");
            var dfd = $q.defer();
            
                $http.get("api/countries.json")
                    .then(function (response) {
                        countriesList = response.data.countriesList;
                        dfd.resolve(countriesList);
                    })
                    .catch(function (response) {
                        dfd.reject(response);
                    });
           
                 

            return dfd.promise;
        };

    }

    angular.module("register")
        .service("registerSvc", ["$http", "$q", registerSvcFn]);


})();