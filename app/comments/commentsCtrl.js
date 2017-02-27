(function () {

    function commentsCtrlFn() {
        
        console.log("comments controller");
    }

    angular.module("comments").controller("commentsCtrl", [commentsCtrlFn]);
})();