(function () {


    function postsCtrlFn(postsSvc) {

        console.log("Controller: posts");
        var vm = this;

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
        .controller("postsCtrl", ["postsSvc", postsCtrlFn]);

})();