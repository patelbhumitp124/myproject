(function () {

    angular.module("bitblogger", ["register", "login", "header", "comments", "product", "posts", "ui.router"]);

    angular.module("bitblogger")
        .config(["$stateProvider", function ($stateProvider) {
            console.log("Config: bitblogger");

            var registerObj = {
                templateUrl: "app/register/register.tpl.html"
            };
            var loginObj = {
                templateUrl: "app/login/login.tpl.html"
            };
            var postsObj = {
                templateUrl: "app/posts/posts.tpl.html"
            };
            var commentsObj = {
                templateUrl: "app/comments/comments.tpl.html"
            };

            $stateProvider.state("register",registerObj);
            $stateProvider.state("login",loginObj);
            $stateProvider.state("posts",postsObj);
            $stateProvider.state("comments",commentsObj);

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