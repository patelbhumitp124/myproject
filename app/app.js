(function () {

    angular.module("bitblogger", ["register", "login", "header", "comments", "product", "posts"]);

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
        vm.registerTemplate = "app/register/register.tpl.html";
        vm.postsTemplate = "app/posts/posts.tpl.html";
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