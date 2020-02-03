angular
    .module("index")
    .controller("listCityCtrl", function($scope, APIService) {
        $scope.listCitys = [];
        $scope.loading = true;
        $scope.message = "";
        $scope.title = "";

        $scope.order = function(field) {
            $scope.orderingField = field;
            $scope.changeOrder = !$scope.changeOrder;
        }

        var pad = function(value, number) {
            for (var i = (value + '').length; i < number; i++) {
                value = "0" + value;
            }

            return value;
        };

        $scope.detailForescast = function(id) {
            $scope.title = "Previsão do tempo";
            $('#htmlModel')[0].innerHTML = "";
            $scope.loading = true;

            APIService.forecastCity(id).then(function(result, status) {

                if (result.data.cod == 404) {
                    $scope.loading = false
                    $scope.message = "Nenhuma previsão encontrada.";
                } else {
                    var semana = ["Domingo", "Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado"];

                    var htmlMessageHeader = '<table class="table">' +
                        '<thead>' +
                        '    <tr>';
                    var htmlMessageBody = '<tbody>';

                    var dateOld, index;

                    for (var j = 0; j < result.data.list.length; j++) {
                        var date = new Date(result.data.list[j].dt_txt);

                        if (dateOld && dateOld.getDate() != date.getDate()) {
                            htmlMessageBody += "</td>";
                        }

                        if (!dateOld || dateOld.getDate() != date.getDate()) {
                            index = date.getDay();
                            htmlMessageHeader += '<th scope="col">' + semana[index] + '</th>';

                            if (index == 5) {
                                index = 1;
                            } else {
                                index++;
                            }

                            htmlMessageBody += "<td>";
                        }

                        htmlMessageBody += '<center><div class="card">' +
                            '<div class="card-body">' +
                            '<h5 class="card-title">' + pad(date.getHours(), 2) + ":" + pad(date.getMinutes(), 2) + '</h5>' +
                            '<p class="card-text">' + result.data.list[j].weather[0].description + '<br/>' +
                            'Temp. mín: <br>' + result.data.list[j].main.temp_min + '<br/>' +
                            'Temp. max: <br>' + result.data.list[j].main.temp_max + '</p>' +
                            '</div>' +
                            '</div></center>';

                        dateOld = date;
                    }

                    htmlMessageHeader += '    </tr>' +
                        '</thead>';

                    htmlMessageBody += '</td>' +
                        '</tbody>' +
                        '</table>';

                    $scope.loading = false

                    $('#htmlModel')[0].innerHTML = htmlMessageHeader + htmlMessageBody;
                }
                $('#messageModal').modal('show');

            }, function() {
                $scope.message = "Não foi possível obter os dados da previsão do tempo.";
                $('#messageModal').modal('show');
                $scope.loading = false
            });
        }

        $scope.delete = function(id) {
            $scope.title = "Usuário";
            $('#htmlModel')[0].innerHTML = "";

            APIService.deleteCity(id).then(function(result, status) {
                $scope.listCitys = $scope.listCitys.filter(function(city) {
                    return city.id !== id;
                });
                $scope.loading = false
                $scope.message = "Excluído com sucesso.";

                $('#messageModal').modal('show');
            }, function() {
                $scope.loading = false

                $scope.message = "Não foi possível remover o registro.";
                $('#messageModal').modal('show');
            });
        }

        var loadingList = function() {
            $scope.loading = true;

            APIService.getListCitys().then(function(result, status) {
                $scope.listCitys = result.data;

                $scope.loading = false
            }, function() {
                $scope.error = "Não foi possível carregar os registros."

                $scope.loading = false
            });
        };

        loadingList();
    });