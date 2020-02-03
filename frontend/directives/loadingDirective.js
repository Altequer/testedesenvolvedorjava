angular
    .module("index")
    .directive("loading", function() {
        return {
            templateUrl: "view/loading.html",
            replace: true,
            restrict: "AE" //Restrito ao atributo e elemento
        };
    });