(function () {


    function commentsCtrlFn(){
        
        console.log("Controller: comments");
        
    }

    angular.module("comments")
        .controller("commentsCtrl", [commentsCtrlFn]);

})();