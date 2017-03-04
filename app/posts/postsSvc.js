(function () {

    function postsSvcFn($http) {

        console.log("Service: posts");
        var vm = this;
        vm.getPostsList = function(){

            return $http.get("api/posts.json");

        };
       
    }

    angular.module("posts")
        .service("postsSvc", ["$http", postsSvcFn]);

})();