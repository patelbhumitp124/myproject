(function () {

    angular.module("bitblogger", ["register", "login", "header", "comments", "product", "posts", "ui.router"]);

    angular.module("bitblogger")
        .config(["$stateProvider", function ($stateProvider) {
            console.log("Config: bitblogger");

            // var headerObj = {
            //     templateUrl: "app/header/header.tpl.html",
            //     controller: "headerCtrl as hc"
            // };
            var registerObj = {
                url: "/register",
                templateUrl: "app/register/register.tpl.html",
                controller: "registerCtrl as rc"
            };
            var loginObj = {
                url: "/login",
                templateUrl: "app/login/login.tpl.html",
                controller: "loginCtrl as lc"
            };
            var postsObj = {
                url: "/posts",
                templateUrl: "app/posts/posts.tpl.html",
                controller: "postsCtrl as pc"
            };
            var commentsObj = {
                url: "/comments",
                templateUrl: "app/comments/comments.tpl.html",
                controller: "commentsCtrl as cc"
            };
            // $stateProvider.state("header", headerObj);
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
        vm.headerTemplate2 = "app/header/header2.tpl.html";
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