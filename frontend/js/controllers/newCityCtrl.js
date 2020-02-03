angular
    .module("index")
    .controller("newCityCtrl", function($scope, $location, APIService) {
        $scope.city = { id: null, name: "", longitude: "", latitude: "", country: "" };

        var insert = function() {

            APIService.newCity($scope.city).then(function(result, status) {

                $scope.loading = false;
                $location.path("#!/listCity");

            }, function(result) {
                console.log(result);

                switch (result.status) {
                    case 423:
                        $scope.error = "Não foi possível inserir o registro, pois o mesmo já existe.";
                        break;
                    case 404:
                        $scope.error = "Cidade informada inválida, tente novamente.";
                        break;
                    default:
                        $scope.error = "Não foi possível inserir o registro.";
                }

                $scope.loading = false;
            });
        };

        $scope.action = function() {
            $scope.loading = true;
            insert();
        }

    });