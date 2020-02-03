angular
    .module("index")
    .directive("message", function() {
        return {
            templateUrl: "view/message.html",
            replace: true,
            restrict: "AE", //Restrito ao atributo e elemento
            transclude: true,
            scope: {
                title: "@",
                message: "="
            }
        };
    });