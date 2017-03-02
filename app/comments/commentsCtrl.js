(function () {


    function commentsCtrlFn(commentsSvc) {

        console.log("Controller: comments");

        var vm = this;

        commentsSvc.getCommentsFromJson()
            .then(function (response) {

                console.log(response);
                vm.commentsList = response.data.commentsList;

            })
            .catch(function () {
                console.log(response);
            })
            .finally(function () {
                
            });

        //vm.commentsList = commentsSvc.getComments();

        // vm.commentsList = [
        //     { "blogId": 1, "commentId": 1, "commentText": "This is first comment for blog id 1." },
        //     { "blogId": 1, "commentId": 2, "commentText": "This is second comment for blog id 1." },
        //     { "blogId": 1, "commentId": 3, "commentText": "This is third comment for blog id 1." }];

    }

    angular.module("comments")
        .controller("commentsCtrl", ["commentsSvc", commentsCtrlFn]);

})();