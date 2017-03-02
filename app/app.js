(function () {

    angular.module("bitblogger", ["register", "login", "header", "comments", "product"]);

    angular.module("bitblogger")
        .config([function () {
            console.log("Config: bitblogger");
        }]);

    function mainCtrlFn(){

        console.log("Controller: mainCtrl");
        var vm = this;
        vm.appName = "BITBLOGGER";
        vm.headerTemplate = "app/header/header.tpl.html";
        vm.commentsTemplate = "app/comments/comments.tpl.html";
        vm.show = false;
        vm.showName = function(){
            vm.show = true;
        };
        vm.hideName = function(){
            vm.show = false;
        };
    }

    angular.module("bitblogger")
        .controller("mainCtrl", [mainCtrlFn]);

})();