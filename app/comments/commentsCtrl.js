(function () {


    function commentsCtrlFn() {

        console.log("Controller: comments");

        var vm = this;
        vm.commentsList = [
            { "blogId": 1, "commentId": 1, "commentText": "This is first comment for blog id 1." },
            { "blogId": 1, "commentId": 2, "commentText": "This is second comment for blog id 1." },
            { "blogId": 1, "commentId": 3, "commentText": "This is third comment for blog id 1." }];

    }

    angular.module("comments")
        .controller("commentsCtrl", [commentsCtrlFn]);

})();