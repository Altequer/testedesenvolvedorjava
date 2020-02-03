angular
    .module("index")
    .factory("APIService", function($http, config) {

        var _getListCitys = function() {
            return $http.get(config.URLAPI);
        };

        var _getCity = function(id) {
            return $http.get(config.URLAPI + "/" + id);
        };

        var _newCity = function(city) {
            return $http.post(config.URLAPI, city);
        };

        var _updateCity = function(city) {
            return $http.put(config.URLAPI, city);
        };

        var _deleteCity = function(id) {
            return $http.delete(config.URLAPI + "/" + id);
        };

        var _forecast = function(id) {
            return $http.get(config.URLAPI + "/forecast/" + id);
        };

        return {
            getListCitys: _getListCitys,
            getCity: _getCity,
            newCity: _newCity,
            updateCity: _updateCity,
            deleteCity: _deleteCity,
            forecastCity: _forecast
        };
    });