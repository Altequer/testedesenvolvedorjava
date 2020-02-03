angular
    .module("index")
    .controller("updateCityCtrl", function($scope, $location, APIService, cityParam) {
        $scope.city = cityParam.data;
        $scope.loading = false;

        var update = function() {

            APIService.updateCity($scope.city).then(function(result, status) {

                $scope.loading = false;
                $location.path("#!/listCity");

            }, function() {
                $scope.error = "Não foi possível atualizar o registro";
                $scope.loading = false;
            });
        };

        $scope.action = function() {
            $scope.loading = true;
            update();
        }
    });