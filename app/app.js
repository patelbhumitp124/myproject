(function () {

    angular.module("bitblogger", ["register", "login", "header", "comments", "product"]);

    angular.module("bitblogger")
        .config([function () {
            console.log("Config: bitblogger");
        }]);

})();