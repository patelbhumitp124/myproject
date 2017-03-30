(function () {

    function postsSvcFn($http,$q) {

        console.log("Service: posts");
        var vm = this;
        vm.getPostsList = function(){

            //return $http.get("api/posts.json");
            var dfd = $q.defer();
            $http.get("api/posts.json")
            .then(function(response){
                dfd.resolve(response);
            })
            .catch(function(response){
                dfd.reject(response);
            });
            return dfd.promise;

        };
       
    }

    angular.module("posts")
        .service("postsSvc", ["$http", "$q", postsSvcFn]);

})();