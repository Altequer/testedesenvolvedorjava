angular
    .module("index")
    .directive("alertError", function() {
        return {
            templateUrl: "view/alert-error.html",
            replace: true,
            restrict: "AE", //Restrito ao atributo e elemento
            transclude: true
        };
    });