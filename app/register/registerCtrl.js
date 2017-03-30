(function () {


    function registerCtrlFn(registerSvc, $state) {

        var vm = this;
        console.log("Controller: register");
        vm.user = {};
        vm.nextPage = function(){
            $state.go("login",{userInfo:vm.user});
           
        };

        registerSvc.getCountriesList()
            .then(function (response) {
                console.log(response);
                vm.countriesList = response.data.countriesList;
                vm.user.selectedCountry = vm.countriesList[229];
            })
            .catch(function (response) {
                console.log(response);
            })
            .finally(function (response) {
                console.log(response);
            });
        
        

    }

    angular.module("register")
        .controller("registerCtrl", ["registerSvc","$state", registerCtrlFn]);

})();