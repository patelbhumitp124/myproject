(function () {


    function registerCtrlFn(registerSvc) {

        var vm = this;
        console.log("Controller: register");
        vm.user = {};

        registerSvc.getCountriesList()
            .then(function (response) {
                console.log(response);
                vm.countriesList = response.data.countriesList;
                vm.selectedCountry = vm.countriesList[15];
            })
            .catch(function (response) {
                console.log(response);
            })
            .finally(function (response) {
                console.log(response);
            });
        
        

    }

    angular.module("register")
        .controller("registerCtrl", ["registerSvc", registerCtrlFn]);

})();