(function () {


    function postsCtrlFn(postsSvc, $state) {

        console.log("Controller: posts");
        var vm = this;
        console.log("post ctrl $state"+$state);
        postsSvc.getPostsList()
        .then(function(response){
            vm.postsList = response.data.postsList;
            console.log(response);
        })
        .catch(function(response){
            console.log(response);
        });
     
    }

    angular.module("posts")
        .controller("postsCtrl", ["postsSvc", "$state", postsCtrlFn]);

})();