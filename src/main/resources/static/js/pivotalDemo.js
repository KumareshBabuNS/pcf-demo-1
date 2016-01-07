/**
 * Cloud Foundry application info - V1.0 01/06/2016 B.Prager
 */
/*global angular */
var pivotalDemo = angular.module('pivotalDemo', []);
/*global $scope, $http, console */
pivotalDemo.controller('appController', [ "$scope", "$http", function appController($scope, $http) {
    'use strict';
    $scope.loading = true;
    $http.get('/appInfo').
        success(function (data) {
            data.loaded = true;
            $scope.app = data;
            $scope.loading = false;
        });
}]);
