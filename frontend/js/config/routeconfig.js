angular
    .module("index")
    .config(function($routeProvider) {

        $routeProvider.when("/", {
            templateUrl: "view/listCity.html",
            controller: "listCityCtrl"
        });

        $routeProvider.when("/newCity", {
            templateUrl: "view/registerCity.html",
            controller: "newCityCtrl"
        });

        $routeProvider.when("/editCity/:id", {
            templateUrl: "view/registerCity.html",
            controller: "updateCityCtrl",
            resolve: {
                cityParam: function(APIService, $route) {
                    return APIService.getCity($route.current.params.id);
                }
            }
        });

        $routeProvider.otherwise({ redirectTo: "/" });
    });